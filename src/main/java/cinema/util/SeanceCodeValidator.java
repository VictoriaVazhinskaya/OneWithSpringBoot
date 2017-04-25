package cinema.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Tory on 24.04.2017.
 */
public class SeanceCodeValidator {

    private static final String SEANCE_CODE_REGEX = "[A-Z0-9]{11}";

    public static boolean isValid(String input){
        Pattern pattern = Pattern.compile(SEANCE_CODE_REGEX);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }
}
