package edu.ccsu.interfaces;
/**
 * 
 * @author Adrian
 * @author Kim
 * @author GaYoung
 */
public interface ProductFactory {

	/**
	 * Creates a device based on the name and returns it
	 * @param device
	 * @param name
	 * @param portNumber
	 * @return
	 */
	public Device makeDevice(String device, String name, String portNumber);
	
	/**
	 * Creates a sensor based on the name and returns it
	 * @param sensor
	 * @param name
	 * @param portNumber
	 * @return
	 */
	public Sensor makeSensor(String sensor, String name, String portNumber);
}