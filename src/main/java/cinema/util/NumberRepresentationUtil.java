package cinema.util;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
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

    public static List<Short> getNumberListFromString(final String s){
        final String[] numbersAsStrings = s.split("\\s+");
        return new ArrayList<Short>(numbersAsStrings.length){
            {
                for(String numberAsString : numbersAsStrings){
                    add(new Short(numberAsString));
                }
            }
        };

    }
}
