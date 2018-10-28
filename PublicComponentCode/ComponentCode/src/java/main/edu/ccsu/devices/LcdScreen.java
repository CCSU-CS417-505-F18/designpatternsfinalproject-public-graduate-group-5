package edu.ccsu.devices;

import edu.ccsu.interfaces.Device;
/**
 * Class connects to LCD RGB Backlight and allows
 * calling classes to print messages to the screen using
 * a variety of methods.
 */
public class LcdScreen extends Device {
	
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
		//TODO implementation 
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
	public void setNextDevice(Device nextDevice, String portNumber) {
		// TODO Auto-generated method stub
		
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
	public boolean isAvailable(Device device, String portNumber) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public String toString() {
		return "Name: " + this.name;
	}

	//TODO implement object equality and hashCode
}
