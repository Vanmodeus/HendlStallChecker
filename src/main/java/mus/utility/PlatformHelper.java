package mus.utility;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinState;

public class PlatformHelper {
	public static void touchLed(Pin gpioNr, boolean enable){
		final GpioController gpio = GpioFactory.getInstance();
		GpioPinDigitalOutput myLed = gpio.provisionDigitalOutputPin(gpioNr);
		myLed.setState(enable ? PinState.HIGH : PinState.LOW);
		gpio.shutdown();
		gpio.unprovisionPin(myLed);
	}

	public static void blink(Pin gpioNr, int delay, int durationSecs){
		final GpioController gpio = GpioFactory.getInstance();
		GpioPinDigitalOutput myLed = gpio.provisionDigitalOutputPin(gpioNr);
		myLed.blink(delay, durationSecs);
		gpio.shutdown();
		gpio.unprovisionPin(myLed);
	}
}
