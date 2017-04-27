package cinema.dao;

import cinema.dto.Booking;
import cinema.dto.Film;
import cinema.dto.Seance;
import cinema.dto.SeanceShortInfo;
import com.mysql.jdbc.Statement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SingleColumnRowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.inject.Named;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Tory on 23.04.2017.
 */
@Named
public class OnlineBookingDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public List<Seance> getSchedule() {
        String query = "SELECT F.name, F.year, F.genre, S.date, S.time" +
                " FROM cinema.seances AS S LEFT JOIN cinema.films AS F ON S.film_id=F.id ORDER BY S.date AND S.time;";
        List<Seance> raspisanie = jdbcTemplate.query(query, (rs, rowNum) -> {
            Film film = new Film();
            film.setName(rs.getString("name"));
            film.setYear((short) rs.getInt("year"));
            film.setGenre(rs.getString("genre"));
            Seance seance = new Seance();
            seance.setFilm(film);
            seance.setDate(rs.getDate("date"));
            seance.setTime(rs.getTime("time"));

            return seance;
        });

        return raspisanie;
    }


    public List<Short> getReservedSeats(int seanceId) {
        String query = "SELECT S.number FROM cinema.seances SEA LEFT JOIN cinema.reservations AS R ON SEA.id = R.seance_id " +
                "LEFT JOIN cinema.seats AS S ON R.id = S.reservation_id WHERE SEA.id = ? ;";
        List<Short> alreadyReserved = jdbcTemplate.query(query, new SingleColumnRowMapper(Short.class), seanceId);
        return alreadyReserved;
    }


    public void addReservation(String reservation_code, List<Short> seats, int seanceId) {
        String insertQuery1 = "INSERT INTO cinema.reservations(code, seance_id) VALUES(?, ?) ;";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                connection -> {
                    PreparedStatement ps = connection.prepareStatement(insertQuery1, Statement.RETURN_GENERATED_KEYS);
                    ps.setString(1, reservation_code);
                    ps.setInt(2, seanceId);
                    return ps;
                },
                keyHolder);
        int reservationId = (int) keyHolder.getKey().intValue();

        String insertQuery2 = "INSERT INTO cinema.seats (number, reservation_id) VALUES(?, ?)";
        jdbcTemplate.batchUpdate(insertQuery2, new BatchPreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setShort(1, seats.get(i));
                ps.setInt(2, reservationId);
            }

            @Override
            public int getBatchSize() {
                return seats.size();
            }

        });
    }


    public SeanceShortInfo getSeanceInfo(int seance_id) {
        String selectQuery = "SELECT film_id, date, time FROM cinema.seances WHERE id = ? ;";
        SeanceShortInfo info = jdbcTemplate.queryForObject(selectQuery, new RowMapper<SeanceShortInfo>() {
            @Override
            public SeanceShortInfo mapRow(ResultSet rs, int i) throws SQLException {
                return new SeanceShortInfo(rs.getInt("film_id"), rs.getDate("date"), rs.getTime("time"));
            }
        }, seance_id);
        return info;
    }

    public Booking getBookingInfo(String bookingCode) {
        String selectQuery1 = "SELECT F.name, F.year, F.genre, S.date, S.time, R.id " +
                "FROM cinema.seances AS S LEFT JOIN cinema.films AS F ON S.film_id=F.id " +
                "RIGHT JOIN cinema.reservations R ON R.seance_id = S.id WHERE R.code LIKE ? ;";
        Booking booking = new Booking();
        Seance seance = new Seance();
        Integer reservationId;
        try {
            reservationId = jdbcTemplate.queryForObject(selectQuery1, (rs, rowNum) -> {
                Film film = new Film();
                film.setName(rs.getString("name"));
                film.setYear((short) rs.getInt("year"));
                film.setGenre(rs.getString("genre"));
                seance.setFilm(film);
                seance.setDate(rs.getDate("date"));
                seance.setTime(rs.getTime("time"));
                return rs.getInt("id");

            }, bookingCode);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }

        System.out.println("Extracted reservation_id: " + reservationId);
        String selectQuery2 = "SELECT number FROM cinema.seats WHERE reservation_id = ? ;";
        List<Short> seats = jdbcTemplate.query(selectQuery2, new SingleColumnRowMapper(Short.class), reservationId);
        booking.setSeance(seance);
        booking.setReservedSeats(seats);
        return booking;
    }

    public boolean deleteBooking(String bookingCode) {
        String deleteQuery = "DELETE FROM cinema.reservations WHERE code LIKE ? ;";
        int affectedRowsNumber = jdbcTemplate.update(deleteQuery, bookingCode);
        if (affectedRowsNumber == 0) {
            return false;
        }
        return true;
    }
}
