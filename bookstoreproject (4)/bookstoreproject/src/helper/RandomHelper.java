package helper;

import java.util.UUID;

public class RandomHelper {
	public static String randomFromUUID() {
		String result = UUID.randomUUID().toString();
		return result.replace("-", "");
	}
}
