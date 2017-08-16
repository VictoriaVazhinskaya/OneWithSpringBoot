package cinema.security;

import cinema.dto.user.Role;
import org.springframework.stereotype.Service;

/**
 * Created by Tory on 15.08.2017.
 */

public class UserActionManager {

    public static boolean allowsToBook(final Long bookOnUserId,
                                       final Long currentUserId,
                                       final Role currentUserRole){
        if(currentUserId != null && (bookOnUserId == null || currentUserId == bookOnUserId
                || Role.ROLE_ADMIN.equals(currentUserRole))){
            return true;
        }
        return false;
    }

    public static boolean allowsToRebook(final long rebookOnUserId,
                                       final long authorizedUserId,
                                       final Role authorizedUserRole){
        if(rebookOnUserId == authorizedUserId || Role.ROLE_ADMIN.equals(authorizedUserRole)){
            return true;
        }
        return false;
    }

    public static boolean allowsToCancelBooking(final long bookOnUserId,
                                         final long authorizedUserId,
                                         final Role authorizedUserRole){
        if(authorizedUserId == bookOnUserId || Role.ROLE_ADMIN.equals(authorizedUserRole)
                || Role.ROLE_MANAGER.equals(authorizedUserRole)){
            return true;
        }
        return false;
    }
}
