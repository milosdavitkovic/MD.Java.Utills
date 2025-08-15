package milos.davitkovic.javautil.utills.services.impl.utils.Number.Integer;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import milos.davitkovic.javautil.utills.annotations.UtilClass;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;


/**
 *
 * @author Milos Davitkovic
 *
 */
@Slf4j
@UtilClass
@NoArgsConstructor
public class IntegerUtils {

    /**
     * Get Even Numbers from the List
     *
     * @param list
     * @return List of Integer with even numbers only
     */
    public List<Integer> getEvenNumbers(final List<Integer> list) {
        return eval(list, n -> n % 2 == 0);
    }

    /**
     * Get Odd Numbers from the List
     *
     * @param list
     * @return List of Integer with odd numbers only
     */
    public List<Integer> getOddNumbers(final List<Integer> list) {
        return eval(list, n -> n % 2 != 0);
    }

    /**
     * Get all number from List bigger then specified number
     *
     * @param list
     * @param number
     * @return
     */
    public List<Integer> getNumbersGreaterThen(final List<Integer> list, final Integer number) {
        return eval(list, n -> n > number);
    }

    /**
     * Get all number smaller then specified number
     *
     * @param list
     * @param number
     * @return
     */
    public List<Integer> getNumbersSmallerThen(final List<Integer> list, final Integer number) {
        return eval(list, n -> n < number);
    }

    private <T> List<T> eval(final List<T> list, final Predicate<T> predicate) {
        final List<T> inputList = new ArrayList<>(CollectionUtils.emptyIfNull(list));
        final List<T> outputList = new ArrayList<>();
        if (inputList.isEmpty()) {
            return outputList;
        }

        for (T element : inputList) {
            if (predicate.test(element)) {
                outputList.add(element);
            }
        }

        return outputList;
    }

    /**
     * Get random Integer number between a range.
     *
     * @param min
     * @param max
     * @return
     */
    public Integer getRandomNumberInRange(final Integer min, final Integer max) {

        if (min >= max) {
            throw new IllegalArgumentException("Max parameter must be greater than Min parameter!");
        }

        final Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
    }

    public boolean isFirstGreatherThenSecond(Integer int1, Integer int2) {
        try {
            if (int1 != null && int2 != null) {
                if (int1.compareTo(int2) > 0) {
                    return true;
                }
            } else {
//				log.("One or both integers are null!");
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean isFirstLessThenSecond(Integer int1, Integer int2) {
        try {
            if (int1 != null && int2 != null) {
                if (int1.compareTo(int2) < 0) {
                    return true;
                }
            } else {
//				log.error("One or both integers are null!");
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean isIntegersEqual(Integer int1, Integer int2) {
        try {
            if (int1 != null && int2 != null) {
                if (int1.compareTo(int2) == 0) {
                    return true;
                }
            } else {
//				log.error("One or both integers are null!");
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
