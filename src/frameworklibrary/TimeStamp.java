/**
 * 
 */
package frameworklibrary;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 609684083---> Subhodeep Ganguly
 *
 */
public class TimeStamp {

	public static String getTimeStamp(String formatString){
		
		SimpleDateFormat dateFormat = new SimpleDateFormat(formatString);
		String timeStamp = dateFormat.format(new Date());
		
		return timeStamp;
		
		
	}
	
	
	
	
}
