package mus.HendlStallChecker;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class NFCTest {
	@Test
	public void testExtractedImage() throws Exception {
		NFCController controller = new NFCController();
		controller.checkForCards(true);
	}
}
