package mus.HendlStallChecker;

import java.util.List;

import javax.smartcardio.Card;
import javax.smartcardio.CardTerminal;
import javax.smartcardio.TerminalFactory;

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
		}
		return null;
	}

	public void checkForCards() throws Exception {
		while (true) {
			terminal.waitForCardPresent(0);
			try {
				Card card = terminal.connect("*");
				String uid = NFCCommand.readUID(card.getBasicChannel());
				System.out.println(uid);

				Thread.sleep(500);
				while (terminal.isCardPresent()) {
					Thread.sleep(100);
				}
			} catch (Exception e) {
			}
		}
	}
}
