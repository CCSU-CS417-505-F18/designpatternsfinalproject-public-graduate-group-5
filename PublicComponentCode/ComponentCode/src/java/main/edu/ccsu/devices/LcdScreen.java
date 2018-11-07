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
	}
	
	/**
	 * Print a message to the 
	 * @param message
	 */
	public void printMessage(String message) {
		//TODO implementation - 
	}
	
	/**
	 * Print a message to the LCD RGB Backlight for
	 * a certain number of seconds
	 * @param message
	 * @param duration
	 */
	public void printMessage(String message, int duration) {
		//TODO implementation
	}

	@Override
	public void setNextDevice(Device nextDevice) throws IncompatibleDeviceError {
		if(nextDevice instanceof LcdScreen) {
			this.nextDevice = nextDevice;
		} else {
			throw new IncompatibleDeviceError("Sensor not compatible with sensor. Sensor chain can only be use other Sensors");
		}		
	}
	
	@Override
	public void adjustBrightness(int brightness) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void blink(int numberOfSeconds) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public String toString() {
		return "Name: " + this.name + "\n" +
				"Port Number: " + this.portNumber;	}

	@Override
	public void turnOn() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void turnOff() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setName(String name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getPortNumber() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setPortNumber(String portNumber) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getColor() {
		return this.color;
	}

	@Override
	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public Device getNextDevice() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isUseNext() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setUseNext(boolean useNext) {
		// TODO Auto-generated method stub
		
	}

	//TODO implement object equality and hashCode
}