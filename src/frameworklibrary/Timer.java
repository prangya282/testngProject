/**
 * 
 */
package frameworklibrary;

import java.util.concurrent.TimeUnit;

/**
 * @author 609684083---> Kireeti Annamaraj
 *
 */
public class Timer {

	private long startTime = 0;
    private long endTime = 0;

    public void start() {
        this.startTime = System.currentTimeMillis();
    }

    public void end() {
        this.endTime = System.currentTimeMillis();
    }

    public long getStartTime() {
        return this.startTime;
    }

    public long getEndTime() {
        return this.endTime;
    }

    public long getTotalTime() {
        return this.endTime - this.startTime;
    }

    public static String getDurationBreakdown(long millis) {
        if (millis < 0) {
            throw new IllegalArgumentException("Duration must be greater than zero!");
        }
        long hours = TimeUnit.MILLISECONDS.toHours(millis);
        long minutes = TimeUnit.MILLISECONDS.toMinutes(millis -= TimeUnit.HOURS.toMillis(hours));
        long seconds = TimeUnit.MILLISECONDS.toSeconds(millis -= TimeUnit.MINUTES.toMillis(minutes));
        StringBuilder sb = new StringBuilder(64);
        sb.append(hours);
        sb.append(" Hrs ");
        sb.append(minutes);
        sb.append(" Mins ");
        sb.append(seconds);
        sb.append(" Secs");
        return sb.toString();
    }
	
	
}
