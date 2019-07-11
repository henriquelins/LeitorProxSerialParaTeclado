package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import gui.util.Alerts;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortList;
import model.entities.Porta;
import model.services.LeitorProxService;

public class LeitorProxFormController implements Initializable {

	private static LeitorProxService service;
	private static Porta porta;
	public static SerialPort serialPort;

	// Controls
	@FXML
	private ComboBox<String> cbPort;

	@FXML
	private ComboBox<String> cbBoudRate;

	@FXML
	private ComboBox<String> cbParityBit;

	@FXML
	private ComboBox<String> cbDataBit;

	@FXML
	private ComboBox<String> cbStopBit;

	@FXML
	private Button btLigado;

	@FXML
	private TextArea txtAreaProx;

	// Funções
	private List<String> listaPorta() {

		List<String> listaPorta = new ArrayList<>();

		String[] portNames = SerialPortList.getPortNames();

		for (String portName : portNames) {

			listaPorta.add(portName);

		}

		return listaPorta;

	}

	private List<String> listaBoudRate() {

		List<String> listaBoudRate = new ArrayList<>();

		listaBoudRate.add("300");
		listaBoudRate.add("600");
		listaBoudRate.add("1200");
		listaBoudRate.add("2400");
		listaBoudRate.add("4800");
		listaBoudRate.add("9600");
		listaBoudRate.add("19200");
		listaBoudRate.add("38400");
		listaBoudRate.add("43000");
		listaBoudRate.add("56000");
		listaBoudRate.add("57600");
		listaBoudRate.add("115200");
		listaBoudRate.add("56000");

		return listaBoudRate;

	}

	private List<String> listaParityBit() {

		List<String> listaParityBit = new ArrayList<>();

		listaParityBit.add("0");
		listaParityBit.add("1");
		listaParityBit.add("2");

		return listaParityBit;

	}

	private List<String> listaDataBit() {

		List<String> listaDataBit = new ArrayList<>();

		listaDataBit.add("8");
		listaDataBit.add("7");
		listaDataBit.add("6");

		return listaDataBit;

	}

	private List<String> listaStopBit() {

		List<String> listaStopBit = new ArrayList<>();

		listaStopBit.add("1");
		listaStopBit.add("2");

		return listaStopBit;

	}

	public void setLeitorProxService(LeitorProxService service) {

		LeitorProxFormController.service = service;

	}

	@FXML
	public void onBtLigadoAction(ActionEvent event) {

		porta.setId(1);
		porta.setPort(cbPort.getSelectionModel().getSelectedItem());
		porta.setBoudRate(Integer.valueOf(cbBoudRate.getSelectionModel().getSelectedItem()));
		porta.setParityBit(Integer.valueOf(cbParityBit.getSelectionModel().getSelectedItem()));
		porta.setDataBit(Integer.valueOf(cbDataBit.getSelectionModel().getSelectedItem()));
		porta.setStopBit(Integer.valueOf(cbStopBit.getSelectionModel().getSelectedItem()));

		txtAreaProx.appendText(porta.toString() + "\n");

		serialPort = new SerialPort(porta.getPort());
	
		if (btLigado.getText().equalsIgnoreCase("Ligar")) {

			try {

				serialPort.openPort();

			} catch (SerialPortException e1) {

				Alerts.showAlert("Erro ao abrir a porta", "SerialPortException", e1.getMessage().toString(),
						AlertType.ERROR);
			}

			if (serialPort.isOpened()) {

				txtAreaProx.appendText("\nPorta aberta: true");

				try {

					serialPort.setParams(porta.getBoudRate(), porta.getDataBit(), porta.getParityBit(),
							porta.getStopBit());

				} catch (SerialPortException e) {

					Alerts.showAlert("Erro ao setar os parâmetros da porta", "SerialPortException",
							e.getMessage().toString(), AlertType.ERROR);
				}

				try {

					txtAreaProx.appendText(
							"\nPronto para escrever na porta: " + serialPort.writeBytes(new byte[] { 0x04 }));
					txtAreaProx.appendText("\nAguardando...\n\n");

					btLigado.setText("Desligar");
				
					service.ligado(serialPort);
	

				} catch (SerialPortException e) {

					Alerts.showAlert("Erro a porta não está pronta para escrever", "SerialPortException",
							e.getMessage().toString(), AlertType.ERROR);

				}

			} else {

				txtAreaProx.appendText("\nPorta aberta: false");

			}

		} else {

			if (serialPort.isOpened()) {

				try {

					serialPort.closePort();

				} catch (SerialPortException e) {

					Alerts.showAlert("Erro ao fechar a porta", "SerialPortException", e.getMessage().toString(),
							AlertType.ERROR);
				}

				txtAreaProx.setText("");
				btLigado.setText("Ligar");
					
				System.exit(0);

			} else {

				txtAreaProx.setText("");
				txtAreaProx.appendText(porta.toString() + "\n");
				btLigado.setText("Ligar");

				System.exit(0);

			}

		}
		
		
		

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		initializeNodes();

	}

	private void initializeNodes() {

		cbPort.setItems(FXCollections.observableArrayList(listaPorta()));
		cbPort.setValue("COM3");
		cbBoudRate.setItems(FXCollections.observableArrayList(listaBoudRate()));
		cbBoudRate.setValue("9600");
		cbParityBit.setItems(FXCollections.observableArrayList(listaParityBit()));
		cbParityBit.setValue("0");
		cbDataBit.setItems(FXCollections.observableArrayList(listaDataBit()));
		cbDataBit.setValue("8");
		cbStopBit.setItems(FXCollections.observableArrayList(listaStopBit()));
		cbStopBit.setValue("1");

		service = new LeitorProxService();
		porta = new Porta();

	}

}
