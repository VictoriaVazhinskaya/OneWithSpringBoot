package cinema;

import cinema.dto.user.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.savedrequest.RequestCacheAwareFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

/**
 * Created by Tory on 14.08.2017.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
       auth.userDetailsService(userDetailsService).passwordEncoder(passwordencoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .addFilterAfter(new RequestCacheAwareFilter(), RequestCacheAwareFilter.class)
                .authorizeRequests()
                .antMatchers("/user/story/*", "/user/rebooking", "/user/cancel").hasAnyRole(Role.ROLE_ADMIN.getValue(), Role.ROLE_MANAGER.getValue(), Role.ROLE_USER.getValue())
                .antMatchers("/adm/**").hasAnyRole(Role.ROLE_ADMIN.getValue(), Role.ROLE_MANAGER.getValue())
                .antMatchers("/film/seances","/place/seances", "/day", "/home", "/", "/seance/booking", "/seance/occupancy", "/logout").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout().logoutUrl("/logout").invalidateHttpSession(true)
                .permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/home")
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                .maximumSessions(1);
    }

    @Bean(name = "passwordencoder")
    public PasswordEncoder passwordencoder(){
        return new BCryptPasswordEncoder(4);
    }
}
