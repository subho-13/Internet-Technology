package util;

import java.util.Random;

public class Token {
	public static String generate(int size) {
		final int leftLimit = 97;
		final int rightLimit = 122;
		final Random random = new Random();

		final String generatedString = random.ints(leftLimit, rightLimit + 1).limit(size)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();

		return generatedString;
	}
}
