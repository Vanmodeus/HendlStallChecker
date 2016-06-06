package mus.HendlStallChecker;

import javax.smartcardio.CardChannel;
import javax.smartcardio.CommandAPDU;
import javax.smartcardio.ResponseAPDU;

public class NFCCommand {
	private final static char[] hexArray = "0123456789ABCDEF".toCharArray();

	private final static byte[] COMMAND_BUZZER_ON = { (byte) 0xFF, (byte) 0x00, (byte) 0x52, (byte) 0xFF, (byte) 0x00 };
	private final static byte[] COMMAND_BUZZER_OFF = { (byte) 0xFF, (byte) 0x00, (byte) 0x52, (byte) 0x00, (byte) 0x00 };
	private final static byte[] COMMAND_READ_UID = { (byte) 0xFF, (byte) 0xCA, (byte) 0x00, (byte) 0x00, (byte) 0x04 };
	// private final static byte[] COMMAND_GET_FIRMWARE_VERSION = { (byte) 0xFF, (byte) 0x00, (byte) 0x48, (byte) 0x00, (byte) 0x00 };

	public static boolean setBuzzerON(CardChannel channel) {
		try {
			CommandAPDU command = new CommandAPDU(COMMAND_BUZZER_ON);
			ResponseAPDU response = channel.transmit(command);
			byte[] byteArray = response.getBytes();
			if (bytesToHex(byteArray).equals("90FF")) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
		}
		return true;
	}

	public static boolean setBuzzerOFF(CardChannel channel) {
		try {
			CommandAPDU command = new CommandAPDU(COMMAND_BUZZER_OFF);
			ResponseAPDU response = channel.transmit(command);
			byte[] byteArray = response.getBytes();
			if (bytesToHex(byteArray).equals("90FF")) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
		}
		return true;
	}

	public static String readUID(CardChannel channel) {
		try {
			CommandAPDU command = new CommandAPDU(COMMAND_READ_UID);
			ResponseAPDU response = channel.transmit(command);
			byte[] byteArray = response.getBytes();
			String ret = bytesToHex(byteArray);
			if (ret.equals("6300")) {
				return null;
			}
			return ret;
		} catch (Exception e) {
		}
		return null;
	}

	private static String bytesToHex(byte[] bytes) {
		char[] hexChars = new char[bytes.length * 2];
		int v;
		for (int j = 0; j < bytes.length; j++) {
			v = bytes[j] & 0xFF;
			hexChars[j * 2] = hexArray[v >>> 4];
			hexChars[j * 2 + 1] = hexArray[v & 0x0F];
		}
		return new String(hexChars);
	}
}
