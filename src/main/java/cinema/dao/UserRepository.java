package cinema.dao;

import cinema.dto.user.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Tory on 14.08.2017.
 */
public interface UserRepository extends CrudRepository<User, Long>{

    User findByLogin(final String login);

    User findByKeyhash(final Integer keyhash);

}


