package edu.ccsu.interfaces;

import edu.ccsu.error.IncompatibleSensorError;

/**
 * Interface that specifies operations on Sensors
 */
public interface Sensor {

	/**
	 * Returns a string representation of sensor data
	 * for the current moment
	 * @param desiredData
	 * @return
	 */
	public String getData(String desiredData);
	
	/**
	 * Returns a string representation of data for a sensor
	 * collected over a given number of seconds
	 * @param seconds
	 * @return
	 */
	public String getData(int seconds);
	
	/**
	 * Set next sensor to use in case of
	 * current sensor throwing error or failing.
	 * @param nextSensor
	 * @param portNumber
	 * @throws IncompatibleSensorError
	 */
	public void setNextSensor(Sensor nextSensor, String portNumber) throws IncompatibleSensorError;
	
	/**
	 * Returns next sensor in CoR
	 * @return Sensor
	 */
	public Sensor getNextSensor();
	
	/**
	 * Returns the iterator for the sensor.
	 * @return
	 */
	public Iterator getIterator();
	
	/**
	 * Check if sensor is available
	 * @param sensor
	 * @param portNumber
	 * @return
	 */
	public boolean isAvailable(Sensor sensor, String portNumber);

	/**
	 * Returns port number of Sensor
	 * @return
	 */
	public String getPortNumber();

	/**
	 * Set port number of Sensor
	 * @param portNumber
	 */
	public void setPortNumber(String portNumber);
	
}
