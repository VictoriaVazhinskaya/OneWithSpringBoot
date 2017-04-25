package cinema.config;

import cinema.dao.OnlineBookingDao;
import cinema.dao.OnlineBookingDaoImpl;
import cinema.manager.MessageManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Tory on 23.04.2017.
 */

@Configuration
public class ContextConfiguration {

    @Bean
    public OnlineBookingDao getOnlineBooking(){
        return new OnlineBookingDaoImpl();
    }

    @Bean
    public MessageManager getMessageManager() {
        return new MessageManager();
    }

}
