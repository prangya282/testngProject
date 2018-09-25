
package frameworklibrary;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;

/**
 * @author 609684083
 *
 */
public class StaticDataManager {
	public static HashMap<String, String> staticData;

	public static void readConfigFile() {
		Properties prop = new Properties();
		FileInputStream input = null;

		try {
			input = new FileInputStream("StaticData.prop");
			prop.load(input);
			Enumeration ex = prop.keys();
			staticData = new HashMap();

			while (ex.hasMoreElements()) {
				String e = (String) ex.nextElement();
				String value = prop.getProperty(e);
				staticData.put(e, value);
			}
		} catch (IOException arg14) {
			arg14.printStackTrace();
			if (input != null) {
				try {
					input.close();
				} catch (IOException arg13) {
					arg13.printStackTrace();
				}
			}
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException arg12) {
					arg12.printStackTrace();
				}
			}

		}

	}
}