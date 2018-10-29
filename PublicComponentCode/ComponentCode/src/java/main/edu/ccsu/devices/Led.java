package edu.ccsu.devices;

import edu.ccsu.error.IncompatibleDeviceError;
import edu.ccsu.interfaces.Device;
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
	}

	public void turnOn() {
		//TODO implementation
	}
	
	public void turnOff() {
		//TODO implementation
	}
	
	/**
	 * 
	 * @param numberOfSeconds
	 */
	public void blink(int numberOfSeconds) {
		//TODO implementation
	}
	
	@Override
	public void setNextDevice(Device nextDevice) throws IncompatibleDeviceError {
		if(nextDevice instanceof Led) {
			this.nextDevice = nextDevice;
		}
		else {
			throw new IncompatibleDeviceError("LED not compatible with device.  LED chain can only be use other LEDs");
		}
	}

	@Override
	public boolean isAvailable(Device device, String portNumber) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public void adjustBrightness(int brightness) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public String toString() {
		return "Name: " + this.name;
	}
	//TODO implement object equality and hashCode
}