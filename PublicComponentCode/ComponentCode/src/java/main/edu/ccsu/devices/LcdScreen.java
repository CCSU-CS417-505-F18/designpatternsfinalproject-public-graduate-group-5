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
		
	}
    
	@Override
	public void blink(int numberOfSeconds) {
		
	}
	
	@Override
	public String toString() {
		return "Name: " + this.name + "\n" +
				"Port Number: " + this.portNumber;	}
    
	@Override
	public void turnOn() {
		
	}
    
	@Override
	public void turnOff() {
		
	}
    
	@Override
	public String getName() {
		return null;
	}
   
	@Override
	public void setName(String name) {
		
	}
    
	@Override
	public String getPortNumber() {
		return null;
	}
    
	@Override
	public void setPortNumber(String portNumber) {
		
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
		return null;
	}
    
	@Override
	public boolean isUseNext() {
		return false;
	}
    
	@Override
	public void setUseNext(boolean useNext) {
		
	}

}