package milos.davitkovic.utills.services.impl.utils.Number;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NumberUtils {

	public Integer toInteger(final Double inputValue) {
		return Optional.ofNullable(inputValue)
				.map(Double::intValue)
				.orElse(null);
	}

	public Long toLong(final Double inputValue) {
		// Math.round() handles odd duck cases, like infinity and NaN, with relative grace.
		return Optional.ofNullable(inputValue)
				.map(Math::round)
				.orElse(null);
	}

	/**
	 * // Returns the largest (closest to positive infinity) double value that is less than or equal to the
	 * 		// argument and is equal to a mathematical integer.
	 * @param number
	 * @return
	 */
	public Integer roundOnLess(final Double number) {
		return Optional.ofNullable(number)
				.map(Math::floor)
				.map(this::toInteger)
				.orElse(null);
	}

	/**
	 * Returns the largest (closest to positive infinity)
	 * value that is less than or equal to the algebraic quotient.
	 *
	 * @param low
	 * @param top
	 * @return
	 */
	public Integer averageOnLess(final Integer low, final Integer top) {
		return Math.floorDiv( (top - low), 2);
	}

}
