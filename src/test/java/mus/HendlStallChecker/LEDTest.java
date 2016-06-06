package mus.HendlStallChecker;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

@RunWith(JUnit4.class)
public class LEDTest {
	@Test
	public void testExtractedImage() throws Exception {
		final GpioController gpio = GpioFactory.getInstance();

		GpioPinDigitalOutput myLed = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_00);

		// explicitly set a state on the pin object
		myLed.setState(PinState.HIGH);

		Thread.sleep(1000);
		// use convenience wrapper method to set state on the pin object
		myLed.low();
		myLed.high();

		// use toggle method to apply inverse state on the pin object
		myLed.toggle();

		// use pulse method to set the pin to the HIGH state for
		// an explicit length of time in milliseconds
		myLed.pulse(1000);
	}
}
