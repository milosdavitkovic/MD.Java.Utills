package milos.davitkovic.utills.services.impl.utils.Number.Integer;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static org.junit.Assert.assertNotNull;

/**
 * 
 * @author Milos Davitkovic
 *
 */
@Service
public class IntegerUtils {

	/**
	 * Get Even Numbers from the List
	 *
	 * @param list
	 * @return List of Integer with even numbers only
	 */
	public List<Integer> getEvenNumbers(final List<Integer> list) {
		return eval(list, n -> n % 2 == 0 );
	}
	
	/**
	 * Get Odd Numbers from the List
	 *
	 * @param list
	 * @return List of Integer with odd numbers only
	 */
	public List<Integer> getOddNumbers(final List<Integer> list) {
		return eval(list, n -> n % 2 != 0 );
	}

	/**
	 * Get all number from List bigger then specified number
	 *
	 * @param list
	 * @param number
	 * @return
	 */
	public List<Integer> getNumbersGreaterThen(final List<Integer> list, final Integer number) {
		return eval(list, n -> n > number );
	}

	/**
	 * Get all number smaller then specified number
	 *
	 * @param list
	 * @param number
	 * @return
	 */
	public List<Integer> getNumbersSmallerThen(final List<Integer> list, final Integer number) {
		return eval(list, n -> n < number );
	}

	private <T> List<T> eval(final List<T> list, final Predicate<T> predicate) {
		final List<T> inputList = new ArrayList<>(CollectionUtils.emptyIfNull(list));
		final List<T> outputList = new ArrayList<>();
		if(inputList.isEmpty()) {
			return outputList;
		}

		for(T element : inputList) {
			if(predicate.test(element)) {
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
		assertNotNull( "Min parameter cannot be null", min);
		assertNotNull( "Max parameter cannot be null", max);

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
