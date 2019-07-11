package model.entities;

import java.io.Serializable;

public class Porta implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private String port;
	private int boudRate;
	private int ParityBit;
	private int dataBit;
	private int stopBit;

	public Porta() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public int getBoudRate() {
		return boudRate;
	}

	public void setBoudRate(int boudRate) {
		this.boudRate = boudRate;
	}

	public int getParityBit() {
		return ParityBit;
	}

	public void setParityBit(int parityBit) {
		ParityBit = parityBit;
	}

	public int getDataBit() {
		return dataBit;
	}

	public void setDataBit(int dataBit) {
		this.dataBit = dataBit;
	}

	public int getStopBit() {
		return stopBit;
	}

	public void setStopBit(int stopBit) {
		this.stopBit = stopBit;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ParityBit;
		result = prime * result + boudRate;
		result = prime * result + dataBit;
		result = prime * result + id;
		result = prime * result + ((port == null) ? 0 : port.hashCode());
		result = prime * result + stopBit;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Porta other = (Porta) obj;
		if (ParityBit != other.ParityBit)
			return false;
		if (boudRate != other.boudRate)
			return false;
		if (dataBit != other.dataBit)
			return false;
		if (id != other.id)
			return false;
		if (port == null) {
			if (other.port != null)
				return false;
		} else if (!port.equals(other.port))
			return false;
		if (stopBit != other.stopBit)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Porta selecionada: " + port + " \nBoud rate: " + boudRate + " \nParity bit: " + ParityBit
				+ " \nData bit: " + dataBit + " \nStop bit: " + stopBit;
	}

}
