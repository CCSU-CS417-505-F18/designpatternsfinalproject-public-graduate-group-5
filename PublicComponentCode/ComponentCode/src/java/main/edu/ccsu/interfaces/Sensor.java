package edu.ccsu.interfaces;

import edu.ccsu.error.IncompatibleSensorError;
import edu.ccsu.utility.CommonConstants;
import edu.ccsu.utility.UtilityMethods;

/**
 * Interface that specifies operations on Sensors
 * @author Adrian
 * @author Kim
 * @author Ga Young
 */
public abstract class Sensor {

	protected Sensor nextSensor;
	protected String portNumber;
	protected String name;
	protected String sensorFile;
	
	/**
	 * Returns a string representation of data for a sensor
	 * collected over a given number of seconds.  To get data for current moment
	 * enter 0 seconds as argument
	 * @param seconds
	 * @return
	 */
	public String getData(int seconds) {
		String data = "";
		if(checkPort(portNumber)) {
		   if(UtilityMethods.checkOperatingSystem()) {
				data = UtilityMethods.callPython(this.sensorFile, this.portNumber.substring(1) + CommonConstants.BLANK + Integer.toString(seconds));
				System.out.println("data " + data);
				if(!data.isEmpty() && !data.contains("nan values"))
					addToList(data);
			} 
			else {
				System.out.println("Cannot get data from: " + this.name);
			}
		}
		return data;
	}
	
	/**
	 * Method to check that portNumber is correct for a given sensor
	 * @param portNumber
	 * @return
	 */
	protected abstract boolean checkPort(String portNumber);
	
	/**
	 * Method used to add sensor data to list of data stored 
	 * internally by concrete sensor classes
	 * @param data
	 */
	protected abstract void addToList(String data);
	
	/**
	 * Set next sensor to use in case of
	 * current sensor throwing error or failing.
	 * @param nextSensor
	 * @param portNumber
	 * @throws IncompatibleSensorError
	 */
	public abstract void setNextSensor(Sensor nextSensor, String portNumber) throws IncompatibleSensorError;
	
	/**
	 * Returns next sensor in CoR
	 * @return Sensor
	 */
	public Sensor getNextSensor() {
		return this.nextSensor;
	}
	
	/**
	 * Returns the iterator for the sensor.
	 * @return
	 */
	public abstract Iterator getIterator();

	/**
	 * Returns port number of Sensor
	 * @return
	 */
	public String getPortNumber() {
		return portNumber;
	}
    
	/**
	 * Set port number of Sensor
	 * @param portNumber
	 */
	public void setPortNumber(String portNumber) {
		this.portNumber = portNumber;
	}
	
	//TODO - implement hash and equals
}