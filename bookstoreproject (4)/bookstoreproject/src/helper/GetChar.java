package helper;

public class GetChar {
	public static String getChar(String s) {
		String result = String.valueOf(s.toUpperCase().charAt(0));
		result = result+ String.valueOf(s.toUpperCase().charAt(1));
		return result;
	}
}
