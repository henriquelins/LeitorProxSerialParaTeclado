package model.services;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.IOException;

import gui.util.Alerts;
import javafx.scene.control.Alert.AlertType;
import jssc.SerialPort;
import jssc.SerialPortException;
import model.entities.Numeros;

public class LeitorProxService {

	
	public void ligado(SerialPort serialPort) {

		new Thread() {

			@Override
			public void run() {

				byte[] buffer = null;

				try {
					
					while (serialPort.isOpened()) {
						
						buffer = serialPort.readBytes(16);
						String prox = new String(buffer).substring(1, 15) + "\n"; // retira o caractere inicial
						
						imprimir(prox);
						
						serialPort.closePort(); // Fecha a porta serial para seter os valores
						serialPort.openPort(); // Abre a porta para iniciar uma nova leitura
						
					}
				
					

				} catch (IOException | AWTException | SerialPortException e) {

					e.printStackTrace();

					Alerts.showAlert("Erro ao imprimir", "SerialPortException", e.getMessage().toString(),
							AlertType.ERROR);

				}

				try {

					serialPort.closePort();

				} catch (SerialPortException e) {

					e.printStackTrace();

				}

			}

		}.start();

	}
	
	public static void imprimir(String prox) throws IOException, AWTException {

		int keyEvt;
		Robot robot = null;
		try {
			robot = new Robot();
		} catch (AWTException e) {
			
			e.printStackTrace();
		}

		// instancia a classe numeros para getKeyEvt
		Numeros num = new Numeros();

		robot.delay(10);

		// quebra a string e manda para o teclado.
		for (int i = 0; i < prox.length(); i++) {
			
			keyEvt = num.getKeyEvt(prox.charAt(i));

			robot.keyPress(keyEvt);
			robot.keyRelease(keyEvt);
			robot.delay(10);
		}

	}

}
