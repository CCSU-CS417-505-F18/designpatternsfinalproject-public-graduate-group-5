package edu.ccsu.devices;

import edu.ccsu.error.IncompatibleDeviceError;
import edu.ccsu.interfaces.Device;
import edu.ccsu.interfaces.ScreenEnabledDevice;
import edu.ccsu.utility.*;

/**
 * Class connects to LCD RGB Backlight and allows calling classes to print messages 
 * to the screen using a variety of methods.
 * @author Adrian
 * @author Kim
 * @author Ga Young
 */
public class LcdScreen implements ScreenEnabledDevice {
	
	protected String name;
	protected Device nextDevice;
	protected String portNumber;
	protected String color;
	protected boolean useNext;
	
	public LcdScreen(String name, String portNumber) {
		this.color 		= "Blue";
		this.name 		= name;
		this.portNumber = portNumber;
		this.useNext 	= false;
	}
	
	/**
	 * Print a message to the LcdScreen
	 * @param message
	 */
	public void printMessage(String message) {
		//port must be a digital port starting with D
		if(!this.getPortNumber().contains("I")) {
			System.out.println("Must use a digital port starting with I");
		}
		else if(UtilityMethods.checkOperatingSystem()) {
//			UtilityMethods.callPython(CommonConstants.Test_LCD, UtilityMethods.buildArgsString(this.getPortNumber(), CommonConstants.ON));			
			UtilityMethods.callPython(CommonConstants.Test_LCD, buildMessage(message));			

		}
		else {
			System.out.println("Cannot turn on LCD: " + this.name);
		}			
	}
	

	/**
	 * Change space in msg to underscore "_" 
	 * @param msg
	 * @return String	returns msg with space changed to "_"
	 */
	private String buildMessage(String msg) {
		StringBuilder buildMsg = new StringBuilder();
		String[] concatMsg = msg.split("\\s+");
		for(String str: concatMsg) {
			buildMsg.append(str);
			buildMsg.append(CommonConstants.UNDERSCORE);
		}
		return buildMsg.toString();	
	}
	
	/**
	 * Print a message to the LCD RGB Backlight for a certain number of seconds
	 * @param message
	 * @param duration	number of seconds to print message
	 */
	public void printMessage(String message, int duration) {
		//TODO implementation
	}

	@Override
	public void setNextDevice(Device nextDevice) throws IncompatibleDeviceError {
		if(nextDevice instanceof LcdScreen) {
			this.nextDevice = nextDevice;
			this.setUseNext(true);
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
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
		
	}

	@Override
	public String getPortNumber() {
		return portNumber;
	}

	@Override
	public void setPortNumber(String portNumber) {
		this.portNumber = portNumber;
		
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
		return nextDevice;
	}

	@Override
	public boolean isUseNext() {
		return useNext;
	}

	@Override
	public void setUseNext(boolean useNext) {
		this.useNext = useNext;
		
	}

	//TODO implement object equality and hashCode
}