package edu.ccsu.interfaces;

import edu.ccsu.error.IncompatibleDeviceError;

/**
 * Abstract class that specifies operations on Devices
 */
public abstract class Device {

	protected String name;
	protected Device nextDevice;
	protected String portNumber;
	protected String color;
	protected int brightness;
	
	/**
	 * Sets another devices to be used in Chain of Responsiblity.
	 * If accessing current device fails and there is another device in the chain,
	 * device will try to use that next device to perform operations.
	 * @param device
	 * @throws IncompatibleDeviceError
	 * @return 
	 */
	public abstract void setNextDevice(Device nextDevice) throws IncompatibleDeviceError;
	
	/**
	 * Given a device and port number, checks to see if devices
	 * is available for use.
	 * @param device
	 * @param portNumber
	 * @return
	 */
	public abstract boolean isAvailable(Device device, String portNumber);
	
	/**
	 *
	 * @param brightness
	 * @return
	 */
	public abstract void adjustBrightness(int brightness);
	
	/**
	 * 
	 * @param numberOfSeconds
	 */
	public abstract void blink(int numberOfSeconds);
	
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

	public String getPortNumber() {
		return portNumber;
	}

	public void setPortNumber(String portNumber) {
		this.portNumber = portNumber;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getColor() {
		return color;
	}

	/**
	 * 
	 * @param color
	 */
	public void setColor(String color) {
		this.color = color;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getBrightness() {
		return brightness;
	}

	/**
	 * 
	 * @param brightness
	 */
	public void setBrightness(int brightness) {
		this.brightness = brightness;
	}
	
	/**
	 * 
	 * @return
	 */
	public Device getNextDevice() {
		return nextDevice;
	}
}