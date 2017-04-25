package cinema.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Tory on 24.04.2017.
 */
public class BookingCodeValidator {

    private static final String BOOKING_UID_PATTERN = "[A-Z0-9]{12,16}";

    public static boolean isValid(String inputString){
        Pattern pattern = Pattern.compile(BOOKING_UID_PATTERN);
        Matcher matcher = pattern.matcher(inputString);
        return matcher.matches();
    }
}
