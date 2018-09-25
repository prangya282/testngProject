/**
 * 
 */
package frameworklibrary;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.safari.SafariDriver;

import frameworklibrary.Helper;
import frameworklibrary.WebDriverFactory;

// TODO: Auto-generated Javadoc
/**
 * The Class AutomationRobot.
 *
 * @author 609684083---> Kireeti Annamaraj
 */
public class AutomationRobot {
	

    /**
     * Copy paste string.
     *
     * @param text the text
     */
    public static void copyPasteString(String text) {
        WebDriver driver = WebDriverFactory.getWebDriverObject();
        Robot robot = null;
        try {
            robot = new Robot();
        }
        catch (AWTException e1) {
            e1.printStackTrace();
        }
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        StringSelection str = new StringSelection(text);
        clipboard.setContents(str, null);
        if (driver instanceof SafariDriver) {
            Helper.bringSafariFront();
            robot.keyPress(157);
            robot.keyPress(86);
            robot.keyRelease(86);
            robot.keyRelease(157);
        } else {
            robot.keyPress(17);
            robot.keyPress(86);
            robot.keyRelease(86);
            robot.keyRelease(17);
        }
    }

	    /**
    	 * Send keys.
    	 *
    	 * @param text the text
    	 */
    	public static void sendKeys(String text) {
	        int i = 0;
	        while (i < text.length()) {
	            AutomationRobot.sendKey(text.charAt(i));
	            ++i;
	        }
	    }

	    /**
    	 * Send key.
    	 *
    	 * @param character the character
    	 */
    	private static void sendKey(char character) {
	        switch (character) {
	            case 'a': {
	                AutomationRobot.typeKey(65);
	                break;
	            }
	            case 'b': {
	                AutomationRobot.typeKey(66);
	                break;
	            }
	            case 'c': {
	                AutomationRobot.typeKey(67);
	                break;
	            }
	            case 'd': {
	                AutomationRobot.typeKey(68);
	                break;
	            }
	            case 'e': {
	                AutomationRobot.typeKey(69);
	                break;
	            }
	            case 'f': {
	                AutomationRobot.typeKey(70);
	                break;
	            }
	            case 'g': {
	                AutomationRobot.typeKey(71);
	                break;
	            }
	            case 'h': {
	                AutomationRobot.typeKey(72);
	                break;
	            }
	            case 'i': {
	                AutomationRobot.typeKey(73);
	                break;
	            }
	            case 'j': {
	                AutomationRobot.typeKey(74);
	                break;
	            }
	            case 'k': {
	                AutomationRobot.typeKey(75);
	                break;
	            }
	            case 'l': {
	                AutomationRobot.typeKey(76);
	                break;
	            }
	            case 'm': {
	                AutomationRobot.typeKey(77);
	                break;
	            }
	            case 'n': {
	                AutomationRobot.typeKey(78);
	                break;
	            }
	            case 'o': {
	                AutomationRobot.typeKey(79);
	                break;
	            }
	            case 'p': {
	                AutomationRobot.typeKey(80);
	                break;
	            }
	            case 'q': {
	                AutomationRobot.typeKey(81);
	                break;
	            }
	            case 'r': {
	                AutomationRobot.typeKey(82);
	                break;
	            }
	            case 's': {
	                AutomationRobot.typeKey(83);
	                break;
	            }
	            case 't': {
	                AutomationRobot.typeKey(84);
	                break;
	            }
	            case 'u': {
	                AutomationRobot.typeKey(85);
	                break;
	            }
	            case 'v': {
	                AutomationRobot.typeKey(86);
	                break;
	            }
	            case 'w': {
	                AutomationRobot.typeKey(87);
	                break;
	            }
	            case 'x': {
	                AutomationRobot.typeKey(88);
	                break;
	            }
	            case 'y': {
	                AutomationRobot.typeKey(89);
	                break;
	            }
	            case 'z': {
	                AutomationRobot.typeKey(90);
	                break;
	            }
	            case 'A': {
	                AutomationRobot.typeKey(16, 65);
	                break;
	            }
	            case 'B': {
	                AutomationRobot.typeKey(16, 66);
	                break;
	            }
	            case 'C': {
	                AutomationRobot.typeKey(16, 67);
	                break;
	            }
	            case 'D': {
	                AutomationRobot.typeKey(16, 68);
	                break;
	            }
	            case 'E': {
	                AutomationRobot.typeKey(16, 69);
	                break;
	            }
	            case 'F': {
	                AutomationRobot.typeKey(16, 70);
	                break;
	            }
	            case 'G': {
	                AutomationRobot.typeKey(16, 71);
	                break;
	            }
	            case 'H': {
	                AutomationRobot.typeKey(16, 72);
	                break;
	            }
	            case 'I': {
	                AutomationRobot.typeKey(16, 73);
	                break;
	            }
	            case 'J': {
	                AutomationRobot.typeKey(16, 74);
	                break;
	            }
	            case 'K': {
	                AutomationRobot.typeKey(16, 75);
	                break;
	            }
	            case 'L': {
	                AutomationRobot.typeKey(16, 76);
	                break;
	            }
	            case 'M': {
	                AutomationRobot.typeKey(16, 77);
	                break;
	            }
	            case 'N': {
	                AutomationRobot.typeKey(16, 78);
	                break;
	            }
	            case 'O': {
	                AutomationRobot.typeKey(16, 79);
	                break;
	            }
	            case 'P': {
	                AutomationRobot.typeKey(16, 80);
	                break;
	            }
	            case 'Q': {
	                AutomationRobot.typeKey(16, 81);
	                break;
	            }
	            case 'R': {
	                AutomationRobot.typeKey(16, 82);
	                break;
	            }
	            case 'S': {
	                AutomationRobot.typeKey(16, 83);
	                break;
	            }
	            case 'T': {
	                AutomationRobot.typeKey(16, 84);
	                break;
	            }
	            case 'U': {
	                AutomationRobot.typeKey(16, 85);
	                break;
	            }
	            case 'V': {
	                AutomationRobot.typeKey(16, 86);
	                break;
	            }
	            case 'W': {
	                AutomationRobot.typeKey(16, 87);
	                break;
	            }
	            case 'X': {
	                AutomationRobot.typeKey(16, 88);
	                break;
	            }
	            case 'Y': {
	                AutomationRobot.typeKey(16, 89);
	                break;
	            }
	            case 'Z': {
	                AutomationRobot.typeKey(16, 90);
	                break;
	            }
	            case '`': {
	                AutomationRobot.typeKey(192);
	                break;
	            }
	            case '0': {
	                AutomationRobot.typeKey(48);
	                break;
	            }
	            case '1': {
	                AutomationRobot.typeKey(49);
	                break;
	            }
	            case '2': {
	                AutomationRobot.typeKey(50);
	                break;
	            }
	            case '3': {
	                AutomationRobot.typeKey(51);
	                break;
	            }
	            case '4': {
	                AutomationRobot.typeKey(52);
	                break;
	            }
	            case '5': {
	                AutomationRobot.typeKey(53);
	                break;
	            }
	            case '6': {
	                AutomationRobot.typeKey(54);
	                break;
	            }
	            case '7': {
	                AutomationRobot.typeKey(55);
	                break;
	            }
	            case '8': {
	                AutomationRobot.typeKey(56);
	                break;
	            }
	            case '9': {
	                AutomationRobot.typeKey(57);
	                break;
	            }
	            case '-': {
	                AutomationRobot.typeKey(45);
	                break;
	            }
	            case '=': {
	                AutomationRobot.typeKey(61);
	                break;
	            }
	            case '~': {
	                AutomationRobot.typeKey(16, 192);
	                break;
	            }
	            case '!': {
	                AutomationRobot.typeKey(517);
	                break;
	            }
	            case '@': {
	                AutomationRobot.typeKey(16, 50);
	                break;
	            }
	            case '#': {
	                AutomationRobot.typeKey(520);
	                break;
	            }
	            case '$': {
	                AutomationRobot.typeKey(515);
	                break;
	            }
	            case '%': {
	                AutomationRobot.typeKey(16, 53);
	                break;
	            }
	            case '^': {
	                AutomationRobot.typeKey(514);
	                break;
	            }
	            case '&': {
	                AutomationRobot.typeKey(150);
	                break;
	            }
	            case '*': {
	                AutomationRobot.typeKey(151);
	                break;
	            }
	            case '(': {
	                AutomationRobot.typeKey(519);
	                break;
	            }
	            case ')': {
	                AutomationRobot.typeKey(522);
	                break;
	            }
	            case '_': {
	                AutomationRobot.typeKey(523);
	                break;
	            }
	            case '+': {
	                AutomationRobot.typeKey(521);
	                break;
	            }
	            case '\t': {
	                AutomationRobot.typeKey(9);
	                break;
	            }
	            case '\n': {
	                AutomationRobot.typeKey(10);
	                break;
	            }
	            case '[': {
	                AutomationRobot.typeKey(91);
	                break;
	            }
	            case ']': {
	                AutomationRobot.typeKey(93);
	                break;
	            }
	            case '\\': {
	                AutomationRobot.typeKey(92);
	                break;
	            }
	            case '{': {
	                AutomationRobot.typeKey(16, 91);
	                break;
	            }
	            case '}': {
	                AutomationRobot.typeKey(16, 93);
	                break;
	            }
	            case '|': {
	                AutomationRobot.typeKey(16, 92);
	                break;
	            }
	            case ';': {
	                AutomationRobot.typeKey(59);
	                break;
	            }
	            case ':': {
	                AutomationRobot.typeKey(513);
	                break;
	            }
	            case '\'': {
	                AutomationRobot.typeKey(222);
	                break;
	            }
	            case '\"': {
	                AutomationRobot.typeKey(152);
	                break;
	            }
	            case ',': {
	                AutomationRobot.typeKey(44);
	                break;
	            }
	            case '<': {
	                AutomationRobot.typeKey(153);
	                break;
	            }
	            case '.': {
	                AutomationRobot.typeKey(46);
	                break;
	            }
	            case '>': {
	                AutomationRobot.typeKey(160);
	                break;
	            }
	            case '/': {
	                AutomationRobot.typeKey(16, 513);
	                break;
	            }
	            case '?': {
	                AutomationRobot.typeKey(16, 47);
	                break;
	            }
	            case ' ': {
	                AutomationRobot.typeKey(32);
	                break;
	            }
	            default: {
	                AutomationRobot.typeKey(45);
	            }
	        }
	    }

	    /**
    	 * Type key.
    	 *
    	 * @param keyCode the key code
    	 */
    	private static void typeKey(int keyCode) {
	        try {
	            Robot robot = new Robot();
	            robot.keyPress(keyCode);
	            robot.keyRelease(keyCode);
	        }
	        catch (AWTException exc) {
	            System.out.println("Error occurred in typeKey method of AutomationRobot class");
	        }
	    }

	    /**
    	 * Type key.
    	 *
    	 * @param keyCode1 the key code 1
    	 * @param keyCode2 the key code 2
    	 */
    	private static void typeKey(int keyCode1, int keyCode2) {
	        try {
	            Robot robot = new Robot();
	            robot.keyPress(keyCode1);
	            robot.keyPress(keyCode2);
	            robot.keyRelease(keyCode2);
	            robot.keyRelease(keyCode1);
	        }
	        catch (AWTException exc) {
	            System.out.println("Error occurred in typeKey method of AutomationRobot class");
	        }
	    }
	}
	
	
