package cinema.dao;

import cinema.dto.Booking;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

/**
 * Created by Tory on 13.08.2017.
 */
public interface BookingRepository extends CrudRepository<Booking, Long> {

    List<Booking> findBySeanceId(final Long id);

    Booking findByCode(final String code);


    List<Booking> findByUserid(final Long userId);

    Booking findBySeanceIdAndUserid(final Long seanceId, final Long userId);
}
