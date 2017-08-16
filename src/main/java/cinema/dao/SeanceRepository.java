package cinema.dao;

import cinema.dto.Seance;
import org.springframework.data.jpa.repository.Temporal;
import org.springframework.data.repository.CrudRepository;

import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TemporalType;
import java.util.Date;
import java.util.List;

/**
 * Created by Tory on 12.08.2017.
 */

public interface SeanceRepository extends CrudRepository<Seance, Long> {

    List<Seance> findByDateLike(@Param("date")@Temporal(TemporalType.DATE)Date date);

    List<Seance> findByFilmIdAndDateGreaterThanEqual(final Long id, final Date date);

    List<Seance> findByHallCinemaIdAndDateGreaterThanEqual(final Long id, final Date date);

}

