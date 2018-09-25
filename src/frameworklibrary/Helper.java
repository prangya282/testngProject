/**
 * 
 */
package frameworklibrary;

import java.io.IOException;

/**
 * @author 609684083---> Kireeti Annamaraj
 *
 */
public class Helper {

	public static void snooze(long secs) {
		try {
			Thread.sleep(secs);
		}
		catch (InterruptedException e) {
			System.out.println("Sleep Interrupted (Exception Caused)");
			e.printStackTrace();
		}
	}

	public static void bringSafariFront() {
		String[] cmd = new String[]{"osascript", "-e", "tell app \"Safari\" to activate"};
		try {
			Runtime.getRuntime().exec(cmd);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}


}
