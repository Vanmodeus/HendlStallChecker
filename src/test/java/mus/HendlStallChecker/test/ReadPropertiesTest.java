package mus.HendlStallChecker.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import mus.utility.HendlStallUtility;

@RunWith(JUnit4.class)
public class ReadPropertiesTest {

	@Test
	public void test() {
		try {
			System.out.println(HendlStallUtility.getWebcamIP());
		} catch (Exception ex) {
			System.err.println(ex);
		}
	}
}
