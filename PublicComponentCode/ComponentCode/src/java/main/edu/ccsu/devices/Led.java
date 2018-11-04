package edu.ccsu.devices;

import edu.ccsu.error.IncompatibleDeviceError;
import edu.ccsu.interfaces.Device;
import edu.ccsu.utility.CommonConstants;
import edu.ccsu.utility.UtilityMethods;

/**
 * Class allows for operations on GrovePi LEDs.
 */
public class Led extends Device {
	
	/**
	 * 
	 * @param name
	 * @param portNumber
	 */
	public Led(String name, String portNumber) {
		this.name = name;
		this.portNumber = portNumber;
	}

	public void turnOn() {
		//port must be a digital port starting with D
		if(!this.getPortNumber().contains("D")) {
			System.out.println("Must use a digital port starting with D");
		}
		else if(UtilityMethods.checkOperatingSystem()) {
			UtilityMethods.callPython(CommonConstants.TOGGLE_LED, UtilityMethods.buildArgsString(this.getPortNumber(), CommonConstants.ON));			
		}
		else {
			System.out.println("Cannot turn on LED: " + this.name);
		}
	}
	
	public void turnOff() {
		if(!this.getPortNumber().contains("D")) {
			System.out.println("Must use a digital port starting with D");
		}
		else if(UtilityMethods.checkOperatingSystem()) {
			UtilityMethods.callPython(CommonConstants.TOGGLE_LED, UtilityMethods.buildArgsString(this.getPortNumber(),CommonConstants.OFF));
		}
		else {
			System.out.println("Cannot turn off LED: " + this.name);
		}
	}
	
	/**
	 * 
	 * @param numberOfSeconds
	 */
	public void blink(int numberOfSeconds) {
		if(UtilityMethods.checkOperatingSystem()) {
			UtilityMethods.callPython(CommonConstants.GROVE_LED_BLINK, UtilityMethods.buildArgsString(this.getPortNumber(), Integer.toString(numberOfSeconds)));
		}
		else {
			System.out.println("Cannot blink LED: " + this.name);
		}
	}
	
	@Override
	public void setNextDevice(Device nextDevice) throws IncompatibleDeviceError {
		if(nextDevice instanceof Led) {
			this.nextDevice = nextDevice;
		}
		else {
			throw new IncompatibleDeviceError("LED not compatible with device.  LED chain can only be used by other LEDs");
		}
	}

	@Override
	public void adjustBrightness(int brightness) {
		if(UtilityMethods.checkOperatingSystem()) {
			UtilityMethods.callPython(CommonConstants.ADJUST_BRIGHTNESS, UtilityMethods.buildArgsString(this.getPortNumber(), Integer.toString(brightness)));
		}
		else {
			System.out.println("Cannot adjust brightness for LED: " + this.getName() + ". Must run on raspberry pi");
		}
		
	}
	
	@Override
	protected boolean chainComparison(Device device) {
		//check chain
		Device nextDevice1 = this.getNextDevice();
		Device nextDevice2 = device.getNextDevice();
		
		if(nextDevice1 != null && nextDevice2 != null) {
			if(nextDevice1.equals(nextDevice2)) {
				return true;
			}
		}
		/*
		 * after getting to end of chain and the devices are all equal,
		 * if next in chain is null for both devices then they are equal chains
		 * */
		if(nextDevice1 == null  && nextDevice2 == null) {
			return true;
		}
		return false;
	}
}