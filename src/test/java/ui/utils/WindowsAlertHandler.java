package ui.utils;

import java.awt.*;
import java.awt.event.KeyEvent;

public class WindowsAlertHandler {
	public void clickOK () throws AWTException {
		Robot rb = new Robot();
		
		// Small delay to ensure alert has appeared
        rb.delay(1000); // 1 second

        // Press Enter (equivalent to clicking OK)
        rb.keyPress(KeyEvent.VK_ENTER);
        rb.keyRelease(KeyEvent.VK_ENTER);
	}
}
