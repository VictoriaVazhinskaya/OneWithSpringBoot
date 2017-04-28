package cinema.util;

import java.util.List;

/**
 * Created by Tory on 24.04.2017.
 */
public class NumberRepresentationUtil {

    private static final String SPACE = " ";
    private static final String DELIMITER_AND_SPACE = ", ";

    public static String getStringRepresentation(List<Short> numbers){
        StringBuilder stringBuilder = new StringBuilder();
        int numberOfNumbers = numbers.size();
        stringBuilder.append(SPACE);
        for(int i=0; i<numberOfNumbers-1; i++){
            stringBuilder.append(numbers.get(i));
            stringBuilder.append(DELIMITER_AND_SPACE);
        }
        stringBuilder.append(numbers.get(numberOfNumbers-1));
        stringBuilder.append(SPACE);
        return  stringBuilder.toString();
    }
}
