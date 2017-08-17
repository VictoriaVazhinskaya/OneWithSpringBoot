package cinema.service.booking;

import cinema.dao.ReservedSeatRepository;
import cinema.dao.BookingRepository;
import cinema.dto.Booking;
import cinema.dto.Seance;
import cinema.dto.Seat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

/**
 * Created by Tory on 13.08.2017.
 */

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private ReservedSeatRepository reservedSeatRepository;

    private Lock lock = new ReentrantLock();

    public List<Short> save(Booking booking, final List<Short> seatsForBooking){
        final Seance seance = booking.getSeance();
        final String code = generateBookingCode(seance, booking.getUserid());
        booking.setCode(code);
        List<Short> unavailableSeats;
        lock.lock();
        try{
            final List<Booking> bookings = seance.getBookings();
            final Set<Short> alreadyBookedSeats = new HashSet<>();
            bookings.stream().forEach(b -> alreadyBookedSeats.addAll(b.getReservedSeatNumbers()));
            unavailableSeats = seatsForBooking.stream()
                    .filter(seat -> alreadyBookedSeats.contains(seat))
                    .collect(Collectors.toList());
            if(unavailableSeats.isEmpty()){
                final Booking sameBooking = bookingRepository.findBySeanceIdAndUserid(seance.getId(), booking.getUserid());
                if(sameBooking != null){
                    booking = sameBooking;
                    reservedSeatRepository.delete(sameBooking.getReservedSeats());
                }else{
                    bookingRepository.save(booking);
                    unavailableSeats = null;
                }
                final List<Seat> seats = new ArrayList<>();
                final long bookingId = booking.getId();
                seatsForBooking.stream().forEach(n -> seats.add(new Seat(bookingId, n)));
                reservedSeatRepository.save(seats);
                booking.setReservedSeats(seats);
            }
        }finally {
            lock.unlock();
        }
        return unavailableSeats;
    }

    public List<Short> update(final Booking booking, List<Short> newSeatNumbers){
        final Set<Booking> bookings = new HashSet<>(booking.getSeance().getBookings());
        bookings.remove(booking);
        List<Short> unavailableSeats = new ArrayList<>();
        lock.lock();
        try{
            final Set<Short> alreadyBookedSeats = new HashSet<>();
            bookings.stream().forEach(b -> alreadyBookedSeats.addAll(b.getReservedSeatNumbers()));
            unavailableSeats = newSeatNumbers.stream()
                    .filter(seat -> alreadyBookedSeats.contains(seat))
                    .collect(Collectors.toList());
            if(unavailableSeats.isEmpty()){
                final List<Seat> newSeats = new ArrayList<>();
                newSeatNumbers.stream().forEach(n -> newSeats.add(new Seat(booking.getId(), n)));
                reservedSeatRepository.delete(booking.getReservedSeats());
                reservedSeatRepository.save(newSeats);
                booking.setReservedSeats(newSeats);
            }
        }finally{
            lock.unlock();
        }
        return unavailableSeats;
    }

    public void cancel(final Long id){
        final List<Seat> seats = reservedSeatRepository.findByBookingid(id);
        reservedSeatRepository.delete(seats);
        bookingRepository.delete(id);
    }


    private String generateBookingCode(final Seance seance, final long userId) {
        final long filmId = seance.getFilm().getId();
        final String date = seance.getDate().toString().replaceAll("-", "");
        final String time = seance.getTime().toString().substring(0, 6).replaceAll(":", "");
        return String.format("%1$d%2$s%3$s%4$d", filmId, date, time, Objects.hash(userId));
    }
}
