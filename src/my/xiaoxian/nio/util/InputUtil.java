package my.xiaoxian.nio.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputUtil {
	private static final BufferedReader KEYBOARD_INPUT = new BufferedReader(new InputStreamReader(System.in));

	private InputUtil() {
	}

	public static String getString(String prompt) throws IOException {
		boolean flag = true;
		String result = null;
		while (flag) {
			System.out.println(prompt);
			result = KEYBOARD_INPUT.readLine();
			if (result == null || "".equals(result)) {
				System.out.println("数据输入错误！");
			} else {
				flag = false;
			}
		}
		return result;
	}

}
