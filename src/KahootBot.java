import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Random;

public class KahootBot {
	private int number, count;
	public KahootBot(int n, String name, String code) throws Exception {
		number = n;
		count = 0;
		for(int i = 0; i < number; i++) {
			createBot(name, code);
		}
	}
	public void createBot(String prefix, String code) throws Exception {
		String name = prefix + " " + count;
		count++;
		Robot mouse = null;
		try {
			java.awt.Desktop.getDesktop().browse(new URI("www.kahoot.it"));
			mouse = new Robot();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		mouse.mouseMove(1200, 800);
		Thread.sleep(1000);
		clickMouse(mouse);
		enterString(code);
		for(int i = 0; i < 5; i++) {
			enterKey(KeyEvent.VK_ENTER, mouse);
		}
		Thread.sleep(1000);
		clickMouse(mouse);
		name = encodeName(name, prefix.length());
		enterString(name);
		enterKey(KeyEvent.VK_ENTER, mouse);
		//closeTab(mouse);
		//Thread.sleep(100);
		//enterKey(KeyEvent.VK_ENTER, mouse);
	}
	public void enterString(String s) throws Exception {
		Robot r = new Robot();
		for(char c : s.toCharArray()) {
			enterKey((int)c, r);
		}
	}
	public String encodeName(String s, int nameLen) {
		String c = s.substring(nameLen);
		s = s.substring(0, nameLen);
		String toBe = "";
		for(char letter : s.toCharArray()) {
			letter = (char)((int)letter - 32);
			toBe += letter;
		}
		toBe += c;
		return toBe;
	}
	public void clickMouse(Robot mouse) {
		mouse.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		mouse.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
	}
	public void enterKey(int m, Robot mouse) throws Exception {
		mouse.keyPress(m);
		mouse.keyRelease(m);
		Thread.sleep(100);
	}
	public void closeTab(Robot r) {
		r.keyPress(KeyEvent.VK_CONTROL);
		r.keyPress(KeyEvent.VK_W);
		r.keyRelease(KeyEvent.VK_CONTROL);
		r.keyRelease(KeyEvent.VK_W);
	}
	public static void main(String[] args) throws Exception {
		KahootBot k = new KahootBot(10, "lol", "602701");
	}
}
