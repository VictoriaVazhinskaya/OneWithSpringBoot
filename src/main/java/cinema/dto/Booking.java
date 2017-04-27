package cinema.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tory on 23.04.2017.
 */
public class Booking {
    private Seance seance;
    private List<Short> reservedSeats;

    public Booking(){
        super();
    }

    public Booking(Seance seance, short ... seats) {
        this.seance = seance;
        int numberOfSeats = seats.length;
        this.reservedSeats = new ArrayList<Short>(numberOfSeats);
        for(int i=0; i<numberOfSeats; i++){
            reservedSeats.add(seats[i]);
        }
    }

    public List<Short> getReservedSeats() {
        return reservedSeats;
    }

    /*public String getStringRepresentationOfSeats(){
        StringBuilder stringBuilder = new StringBuilder();
        int numberOfSeats = reservedSeats.size();
        for(int i=0; i<numberOfSeats-1; i++){
            stringBuilder.append(reservedSeats.get(i));
            stringBuilder.append(";");
        }
        stringBuilder.append(reservedSeats.get(numberOfSeats-1));
        return stringBuilder.toString();
    }*/

    public Seance getSeanceID() {
        return seance;
    }

    public void setReservedSeats(List<Short> reservedSeats) {
        this.reservedSeats = reservedSeats;
    }

    public void setSeance(Seance seance) {
        this.seance = seance;
    }

    public void addSeat(short seat){
        reservedSeats.add(seat);
    }
}
