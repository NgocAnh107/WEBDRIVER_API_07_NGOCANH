package selenium_api;

import java.util.Random;

public class Common {
	public static int randomEmail() {
		Random random = new Random();
		int number = random.nextInt(99999);
		return number;
	}

}
