package cinema.util;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Tory on 24.04.2017.
 */
public class SeatNumberValidator {

    private static final short MIN_SEAT_NUMBER = 1;
    private static final short MAX_SEAT_NUMBER = 225;
    public static final short SUCCESSFUL_VALIDATION = -1;

    public static short isValidNumbers(List<Short> numbers){
        int numberOfNumbers = numbers.size();
        int i = 0;
        List<Short> badNumbers = numbers.stream()
                .filter(number -> number < MIN_SEAT_NUMBER || number > MAX_SEAT_NUMBER).collect(Collectors.toList());
        /*for(; i<numberOfNumbers; i++){
            if(numbers.get(i) < MIN_SEAT_NUMBER || numbers.get(i) > MAX_SEAT_NUMBER){
                break;
            }
        }
        if(i == numberOfNumbers){
            return SUCCESSFUL_VALIDATION;
        }*/
        if (badNumbers.isEmpty()) {
            return SUCCESSFUL_VALIDATION;
        }
        return badNumbers.get(0);
    }

}
