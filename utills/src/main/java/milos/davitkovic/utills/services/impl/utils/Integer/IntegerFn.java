package milos.davitkovic.utills.services.impl.utils.Integer;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;
import java.util.logging.Logger;

/**
 * 
 * @author Milos Davitkovic
 *
 */
@Service
public class IntegerFn {

	final private Logger log = Logger.getLogger(this.getClass().getName());

	public IntegerFn() {
		super();
	}
	
	
	public List<Integer> eval(List<Integer> list, Predicate<Integer> predicate) {
		List<Integer> outputList = new ArrayList<>();
	      for(Integer n: list) {
			
	         if(predicate.test(n)) {
	        	 outputList.add(n);
	         }
	      }
	      return outputList;
	   }
	
	/**
	 * 
	 * @param list
	 * @return List of Integer with even numbers only
	 */
	public List<Integer> evenNumbers(List<Integer> list) {
		return eval(list, n-> n%2 == 0 );
	}
	
	/**
	 * 
	 * @param list
	 * @return List of Integer with odd numbers only
	 */
	public List<Integer> oddNumbers(List<Integer> list) {
		return eval(list, n-> n%2 != 0 );
	}
	
	public List<Integer> greaterThenNumbers(List<Integer> list, Integer number) {
		return eval(list, n-> n > number );
	}
	
	public int getRandomNumberInRange(int min, int max) {
		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}

		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
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
