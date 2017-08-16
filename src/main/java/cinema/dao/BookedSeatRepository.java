package cinema.dao;

import cinema.dto.Seat;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Tory on 14.08.2017.
 */
public interface BookedSeatRepository extends CrudRepository<Seat, Long> {

}
