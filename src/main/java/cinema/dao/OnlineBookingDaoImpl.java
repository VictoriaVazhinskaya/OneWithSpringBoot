package cinema.dao;

import cinema.model.Booking;
import cinema.model.Film;
import cinema.model.Seance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SingleColumnRowMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by Tory on 23.04.2017.
 */

public class OnlineBookingDaoImpl implements OnlineBookingDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public List<Seance> getRaspisanie() {
        String query = "SELECT F.name, F.year, F.genre, S.code, S.date, S.time" +
                " FROM cinema.seances AS S LEFT JOIN cinema.films AS F ON S.film_id=F.id ORDER BY S.date AND S.time;";
        List<Seance> raspisanie = jdbcTemplate.query(query, new RowMapper<Seance>() {

            @Override
            public Seance mapRow(ResultSet rs, int rowNum) throws SQLException {
                Film film = new Film();
                film.setName(rs.getString("name"));
                film.setYear((short) rs.getInt("year"));
                film.setGenre(rs.getString("genre"));
                Seance seance = new Seance();
                seance.setCode(rs.getString("code"));
                seance.setFilm(film);
                seance.setDate(rs.getDate("date"));
                seance.setTime(rs.getTime("time"));

                return seance;
            }

        });

        return raspisanie;
    }

    @Override
    public List<Short> getReservedSeats(String seanceCode) {
        String query = "SELECT R.seat FROM cinema.reservations R RIGHT JOIN cinema.seances S ON R.seance_id = S.id WHERE" +
                " S.code LIKE ? ;";
        List<Short> alreadyReserved = jdbcTemplate.query(query, new Object[]{seanceCode},
                new SingleColumnRowMapper(Short.class));
        return alreadyReserved;
    }

    @Override
    public void addReservation(String reservation_code, List<Short> seats, String seance_code) {
        String insertQuery = "INSERT INTO cinema.reservations(code, seat, seance_id) " +
                "SELECT ?, ?, id FROM cinema.seances WHERE code LIKE ?;";
        jdbcTemplate.batchUpdate(insertQuery, new BatchPreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setString(1, reservation_code);
                ps.setShort(2, seats.get(i));
                ps.setString(3, seance_code);
            }

            @Override
            public int getBatchSize() {
                return seats.size();
            }

        });
    }

    @Override
    public Booking getBookingInfo(String bookingCode) {
        String selectQuery = "SELECT F.name, F.year, F.genre, S.date, S.time, S.code, R.seat " +
                "FROM cinema.seances AS S LEFT JOIN cinema.films AS F ON S.film_id=F.id " +
                "RIGHT JOIN cinema.reservations R ON R.seance_id = S.id WHERE R.code LIKE ? ;";
        Booking booking = new Booking();
        List<Short> seats = jdbcTemplate.query(selectQuery, new Object[]{bookingCode}, new RowMapper<Short>() {

            @Override
            public Short mapRow(ResultSet rs, int rowNum) throws SQLException {
                if(rowNum == 1) {
                    Film film = new Film();
                    film.setName(rs.getString("name"));
                    film.setYear((short) rs.getInt("year"));
                    film.setGenre(rs.getString("genre"));
                    Seance seance = new Seance();
                    seance.setFilm(film);
                    seance.setCode(rs.getString("code"));
                    seance.setDate(rs.getDate("date"));
                    seance.setTime(rs.getTime("time"));
                    booking.setSeance(seance);
                }
                return rs.getShort("seat");

            }
        });

        if(seats.isEmpty()){
            return null;
        }
        booking.setReservedSeats(seats);
        return booking;
    }

    @Override
    public boolean deleteBooking(String bookingCode) {
        String deleteQuery = "DELETE FROM cinema.reservations WHERE code LIKE ? ;";
        int affectedRowsNumber = jdbcTemplate.update(deleteQuery, bookingCode);
        if(affectedRowsNumber == 0){
            return false;
        }
        return true;
    }
}
