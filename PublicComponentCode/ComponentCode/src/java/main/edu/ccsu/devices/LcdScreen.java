package edu.ccsu.devices;

import edu.ccsu.error.IncompatibleDeviceError;
import edu.ccsu.interfaces.Device;
import edu.ccsu.interfaces.ScreenEnabledDevice;
/**
 * Class connects to LCD RGB Backlight and allows
 * calling classes to print messages to the screen using
 * a variety of methods.
 */
public class LcdScreen implements ScreenEnabledDevice {
	
	protected String name;
	protected Device nextDevice;
	protected String portNumber;
	protected String color;
	protected boolean useNext;
	
	public LcdScreen(String name, String portNumber) {
		this.color = "Blue";
		this.name = name;
		this.portNumber = portNumber;
		this.useNext = true;
	}
	
	/**
	 * Print a message to the 
	 * @param message
	 */
	public void printMessage(String message) {
	}
	
	/**
	 * Print a message to the LCD RGB Backlight for
	 * a certain number of seconds
	 * @param message
	 * @param duration
	 */
	public void printMessage(String message, int duration) {
	}
    /**
     * Sets the next device in the chain. Must be a sensor
     * @param nextDevice
     */
	@Override
	public void setNextDevice(Device nextDevice) throws IncompatibleDeviceError {
		if(nextDevice instanceof LcdScreen) {
			this.nextDevice = nextDevice;
		} else {
			throw new IncompatibleDeviceError("Sensor not compatible with sensor. Sensor chain can only be use other Sensors");
		}		
	}
	/**
	 * Adjusts the brightness of LCD Screen
	 * @param brightness
	 */
	@Override
	public void adjustBrightness(int brightness) {
		
	}
    /**
     * Makes the LCD screen blink
     * @param numberOfSeconds
     */
	@Override
	public void blink(int numberOfSeconds) {
		
	}
	/**
	 * Displays LCD Screen as string, including name and portnumber 
	 */
	@Override
	public String toString() {
		return "Name: " + this.name + "\n" +
				"Port Number: " + this.portNumber;	}
    /**
     * Turns on the LCD
     */
	@Override
	public void turnOn() {
		
	}
    /**
     * Turns off the LCD
     */
	@Override
	public void turnOff() {
		
	}
    /**
     * Gets the name of the LCD
     */
	@Override
	public String getName() {
		return null;
	}
    /**
     * Sets the name of the LCD
     * @param name
     */
	@Override
	public void setName(String name) {
		
	}
    /**
     * Gets the port number of the LCD
     */
	@Override
	public String getPortNumber() {
		return null;
	}
    /**
     * Sets the port number of the LCD
     * @param portNumber
     */
	@Override
	public void setPortNumber(String portNumber) {
		
	}
    /**
     * Gets the color of the LCD background
     */
	@Override
	public String getColor() {
		return this.color;
	}
    /**
     * Sets the color of the LCD background
     * @param color
     */
	@Override
	public void setColor(String color) {
		this.color = color;
	}
    /**
     * Gets the next device in the chain of LCDs
     */
	@Override
	public Device getNextDevice() {
		return null;
	}
    /**
     * Determines if there is a next device to use
     */
	@Override
	public boolean isUseNext() {
		return false;
	}
    /**
     * If there is a next device, sets next device to use next
     * @param useNext
     */
	@Override
	public void setUseNext(boolean useNext) {
		
	}

}