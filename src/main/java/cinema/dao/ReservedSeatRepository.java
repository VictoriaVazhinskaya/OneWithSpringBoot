package cinema.dao;

import cinema.dto.Seat;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Tory on 14.08.2017.
 */
public interface ReservedSeatRepository extends CrudRepository<Seat, Long> {

    List<Seat> findByBookingid(final Long bookingid);

}
