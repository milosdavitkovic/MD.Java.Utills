package milos.davitkovic.utills.services.impl.utils.Number;

import org.springframework.stereotype.Service;

@Service
public class NumbersFn {
	
	public NumbersFn() {
		super();
	}

	public Integer fromDoubleToInteger(Double inputValue) {
		// a null-safe solution:
//		return (inputValue == null)? null : Integer.valueOf(inputValue.intValue());
		// Math.round() handles odd duck cases, like infinity and NaN, with relative grace.
		return inputValue == null ? null : Integer.valueOf((int) Math.round(inputValue));
	}

	public Integer roundOnLess(Double number) {
		// Returns the largest (closest to positive infinity) double value that is less than or equal to the
		// argument and is equal to a mathematical integer.
		Double less =  Math.floor(number);
		return fromDoubleToInteger(less);
	}

	public Integer averageOnLess(Integer low, Integer top) {
		Integer average = Math.floorDiv( (top - low), 2);
		return average;
	}

}
