package edu.ccsu.interfaces;
/**
 * Interface that specifies methods that can be used to create 
 * different devices and sensors 
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
	 * Generates a light enabled device (Currently LEDs are supported)
	 * @param device
	 * @param name
	 * @param portNumber
	 * @return
	 */
	public LightEnabledDevice makeLightEnabledDevice(String device, String name, String portNumber);
	
	/**
	 * Used to create a screen enabled device (currently supports the LCD display)
	 * @param device
	 * @param name
	 * @param portNumber
	 * @return
	 */
	public ScreenEnabledDevice makeScreenEnabledDevice(String device, String name, String portNumber);
	
	/**
	 * Creates a sensor based on the name and returns it
	 * @param sensor
	 * @param name
	 * @param portNumber
	 * @return
	 */
	public Sensor makeSensor(String sensor, String name, String portNumber);
}