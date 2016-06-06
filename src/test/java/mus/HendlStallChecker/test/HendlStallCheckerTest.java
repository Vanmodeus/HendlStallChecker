package mus.HendlStallChecker.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import junit.framework.TestCase;
import mus.periphery.OnvifExtractor;

@RunWith(JUnit4.class)
public class HendlStallCheckerTest extends TestCase {

	@Test
	public void TestAll() {
		new OnvifExtractor().extractPicture();
	}
}
