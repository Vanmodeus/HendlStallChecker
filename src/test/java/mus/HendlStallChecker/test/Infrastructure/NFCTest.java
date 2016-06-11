package mus.HendlStallChecker.test.Infrastructure;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import mus.periphery.NFCController;

@RunWith(JUnit4.class)
public class NFCTest {
	@Test
	public void testExtractedImage() throws Exception {
		NFCController controller = new NFCController();
		controller.checkForCards(true);
	}
}
