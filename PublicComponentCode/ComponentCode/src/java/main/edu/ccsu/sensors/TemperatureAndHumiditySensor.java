package edu.ccsu.sensors;

import edu.ccsu.error.IncompatibleSensorError;
import edu.ccsu.devices.Led;
import edu.ccsu.interfaces.Device;
import edu.ccsu.interfaces.Sensor;

/*
 * import edu.ccsu.error.IncompatibleDeviceError;
import edu.ccsu.interfaces.Device;
import edu.ccsu.utility.CommonConstants;
import edu.ccsu.utility.UtilityMethods;*/

/***
 * Temperature and humidity sensor that reads data from
 * the GrovePi temp/humidity sensor and returns that data
 *
 */
public class TemperatureAndHumiditySensor implements Sensor {

	private Sensor nextSensor;
	private double degreesFahrenheit;
	private double humidityValue;
	private String portNumber;
	private String name;

	public TemperatureAndHumiditySensor(String name) {
		this.name = name;
	}

	@Override
	public String getData(String desiredData) {
		//if user just wants humidity only get that
		//if user just wants temp just get that
		//if user wants both grab both
		String returnValue = "";
		if(desiredData.equals("d")) returnValue = Double.toString(degreesFahrenheit);
		else if(desiredData.equals("h")) returnValue = Double.toString(humidityValue);
		else if(desiredData.equals("b")) returnValue = Double.toString(degreesFahrenheit)+":"+Double.toString(humidityValue);
		return returnValue;
	}

	@Override
	public String getData(int seconds) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setNextSensor(Sensor nextSensor, String portNumber) {
		if(nextSensor instanceof TemperatureAndHumiditySensor) {
			this.nextSensor = nextSensor;
		} else {
			throw new IncompatibleSensorError("Sensor not compatible with sensor. Sensor chain can only be use other Sensors")
		}

	}




	/**
	 * Returns the next sensor in the chain
	 * @return
	 */
	private Sensor getNextSensor() {
		return nextSensor;
	}

	@Override
	public boolean isAvailable(Sensor sensor, String portNumber) {
		// TODO : connect with python file 
		return false;
	}

	/**
	 *
	 * @return
	 */
	@Override
	public String getPortNumber() {
		return portNumber;
	}

	/**
	 *
	 * @param portNumber
	 */
	@Override
	public void setPortNumber(String portNumber) {
		this.portNumber = portNumber;
	}

	@Override
	public String toString() {
		return "Name: " + this.name;
	}
	//TODO implement object equality and hashCode
}
