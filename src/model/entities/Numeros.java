package model.entities;

import java.awt.event.KeyEvent;

public class Numeros {

	public Numeros() {
		super();
	}

	public int returnIndex(char toIndex, char... args) {
		for (int i = 0; i < args.length; i++) {
			if (toIndex == (args[i]))
				return i;
		}
		return -1;
	}

	public int getKeyEvt(char caracter) {
		int keyInput = -1;

		switch (returnIndex(caracter, '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '\n')) {
		case 0:
			keyInput = KeyEvent.VK_0;
			break;
		case 1:
			keyInput = KeyEvent.VK_1;
			break;
		case 2:
			keyInput = KeyEvent.VK_2;
			break;
		case 3:
			keyInput = KeyEvent.VK_3;
			break;
		case 4:
			keyInput = KeyEvent.VK_4;
			break;
		case 5:
			keyInput = KeyEvent.VK_5;
			break;
		case 6:
			keyInput = KeyEvent.VK_6;
			break;
		case 7:
			keyInput = KeyEvent.VK_7;
			break;
		case 8:
			keyInput = KeyEvent.VK_8;
			break;
		case 9:
			keyInput = KeyEvent.VK_9;
			break;
		case 10:
			keyInput = KeyEvent.VK_ENTER;
			break;
		}
		return keyInput;
	}

}
