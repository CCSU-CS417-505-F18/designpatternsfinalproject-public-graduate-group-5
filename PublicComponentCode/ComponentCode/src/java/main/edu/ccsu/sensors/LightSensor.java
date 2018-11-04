package edu.ccsu.sensors;

import edu.ccsu.error.IncompatibleSensorError;
import edu.ccsu.interfaces.Sensor;

/**
 * Class can access light sensor and 
 * get the light intensity
 */
public class LightSensor implements Sensor {

	private Sensor nextSensor;
	private String name;
	private String portNumber;
	private double lightIntensity;
	
	public LightSensor(String name) {
		this.name = name;
	}
	
	@Override
	public String getData(String desiredData) {
		//if user just wants name only get that
		//if user just wants lightIntensity just get that
		//if user wants both grab both
		String returnValue = "";
		if(desiredData.equals("name")) returnValue = name;
		else if(desiredData.equals("intensity")) returnValue = Double.toString(lightIntensity);
		else if(desiredData.equals("b")) returnValue = name+":"+Double.toString(lightIntensity);
		return returnValue;	
	}

	@Override
	public String getData(int seconds) {
		// TODO Auto-generated method stub
		
		return null;
	}

	@Override

	public void setNextSensor(Sensor nextSensor, String portNumber) throws IncompatibleSensorError  {
		if(nextSensor instanceof LightSensor) {
			this.nextSensor = nextSensor;
		} else {
			throw new IncompatibleSensorError("Sensor not compatible with sensor. Sensor chain can only be use other Sensors");
		}
	}
	
	@Override
	public boolean isAvailable(Sensor sensor, String portNumber) {
		// TODO  connect with python file 
		return false;
	}

	/**
	 * 
	 * @return
	 */
	public String getPortNumber() {
		return portNumber;
	}
	
	/**
	 * 
	 * @param portNumber
	 */
	public void setPortNumber(String portNumber) {
		this.portNumber = portNumber;
	}

	/**
	 * 
	 * @return
	 */
	public Sensor getNextSensor() {
		return nextSensor;
	}

	/**
	 * 
	 * @param nextSensor
	 */
	public void setNextSensor(Sensor nextSensor) {
		if(nextSensor instanceof LightSensor) {
			this.nextSensor = nextSensor;
		} 
	}

	/**
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
}