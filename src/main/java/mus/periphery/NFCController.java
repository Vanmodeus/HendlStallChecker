package mus.periphery;

import java.util.List;

import javax.smartcardio.Card;
import javax.smartcardio.CardTerminal;
import javax.smartcardio.TerminalFactory;

/**
 * Connection to the NFC-Reader, waits for a signal and reads the ID of the
 * NFC-chip
 * 
 * @author Vanmodeus
 *
 */
public class NFCController {
	private CardTerminal terminal;

	public NFCController() {
		terminal = getActiveTerminal();
	}

	private static CardTerminal getActiveTerminal() {
		TerminalFactory factory;
		try {
			factory = TerminalFactory.getInstance("PC/SC", null);
			List<CardTerminal> terminals = factory.terminals().list();
			return terminals.get(0);
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	@SuppressWarnings("restriction")
	public void checkForCards(boolean onlyOnce) throws Exception {
		if (terminal == null) {
			throw new Exception("Kein NFC-Reader vorhanden");
		}
		while (true) {
			terminal.waitForCardPresent(0);
			try {
				Card card = terminal.connect("*");
				String uid = NFCCommand.readUID(card.getBasicChannel());
				System.out.println(uid);
				if (onlyOnce) {
					break;
				}
				Thread.sleep(500);
				while (terminal.isCardPresent()) {
					Thread.sleep(100);
				}
			} catch (Exception e) {
			}
		}
	}

	@SuppressWarnings("restriction")
	public String waitForNfcCard() throws Exception {
		if (terminal == null) {
			throw new Exception("Kein NFC-Reader vorhanden");
		}

		terminal.waitForCardPresent(0);
		Card card = terminal.connect("*");
		String uid = NFCCommand.readUID(card.getBasicChannel());

		Thread.sleep(500);
		while (terminal.isCardPresent()) {
			Thread.sleep(100);
		}

		return uid;
	}
}
