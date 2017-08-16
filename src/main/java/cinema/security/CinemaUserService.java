package cinema.security;

import cinema.dao.UserRepository;
import cinema.dto.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by Tory on 14.08.2017.
 */
@Service("cinemaUsersService")
public class CinemaUserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        final User user = userRepository.findByLogin(login);
        if(user == null){
            throw new UsernameNotFoundException("User with login=" + login + " does not exists");
        }
        return new CinemaUserDetails(user);
    }
}
