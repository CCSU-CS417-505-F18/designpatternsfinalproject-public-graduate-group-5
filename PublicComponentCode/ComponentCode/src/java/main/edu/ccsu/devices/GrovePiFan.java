package edu.ccsu.devices;

import edu.ccsu.error.IncompatibleDeviceError;
import edu.ccsu.interfaces.Device;
import edu.ccsu.interfaces.Fan;
import edu.ccsu.utility.CommonConstants;
import edu.ccsu.utility.UtilityMethods;
/**
 * Class that has implementations to interact with Grove Minifan v1.1
 * @author Adrian
 *
 */
public class GrovePiFan implements Fan {

	private String portNumber;
	private String name;
	private boolean useNext;
	private Device nextDevice;
	
	/**
	 * Constructs the grovepi fan
	 * @param name
	 * @param portNumber
	 */
	public GrovePiFan(String name, String portNumber) {
		this.portNumber = portNumber;
		this.name = name;
		this.useNext = true;
	}
	/**
	 * Sets the next device of type GrovePiFan, throws error if not
	 * @param nextDevice
	 */
	@Override
	public void setNextDevice(Device nextDevice) throws IncompatibleDeviceError {
		if(nextDevice instanceof GrovePiFan)
			this.nextDevice = nextDevice;
		else 
			throw new IncompatibleDeviceError(nextDevice.getName() + " is not compatible with the GrovePiFan");
	}
    /**
     * Gets the next device and returns
     */
	@Override
	public Device getNextDevice() {
		return this.nextDevice;
	}
    /**
     * Turns on the GrovePi fan while first checking correct ports are being used and checking operating system
     */
	@Override
	public void turnOn() {
		if(!this.getPortNumber().contains("D")) {
			System.out.println("Must use a digital port starting with D");
		}
		else if(UtilityMethods.checkOperatingSystem()) {
			UtilityMethods.callPython(CommonConstants.MINIFAN, this.portNumber.substring(1) + CommonConstants.BLANK + CommonConstants.ON);			
		}
		else {
			System.out.println("Cannot turn on Fan: " + this.name);
		}
	}
    /**
     * Turns off the GrovePi fan, again checking correct port number and operating system
     */
	@Override
	public void turnOff() {
		if(!this.getPortNumber().contains("D")) {
			System.out.println("Must use a digital port starting with D");
		}
		else if(UtilityMethods.checkOperatingSystem()) {
			UtilityMethods.callPython(CommonConstants.MINIFAN, this.portNumber.substring(1) + CommonConstants.BLANK + CommonConstants.OFF);			
		}
		else {
			System.out.println("Cannot turn off Fan: " + this.name);
		}
	}
	/**
	 * Adjusts the speed of the fan after checking correct port use and operating system
	 * @param speed
	 */
	@Override
	public void adjustSpeed(int speed) {
		if(!this.getPortNumber().contains("D")) {
			System.out.println("Must use a digital port starting with D");
		}
		else if(UtilityMethods.checkOperatingSystem()) {
			UtilityMethods.callPython(CommonConstants.MINIFAN, this.portNumber.substring(1) + CommonConstants.BLANK + CommonConstants.ADJUST_FAN_SPEED
										+ CommonConstants.BLANK + Integer.toString(speed));			
		}
		else {
			System.out.println("Cannot adjust speed of Fan: " + this.name);
		}
	}
	/**
	 * Gets the name of the fan
	 */
	@Override
	public String getName() {
		return this.name;
	}
    /**
     * Sets the name of the fan
     * @param name
     */
	@Override
	public void setName(String name) {
		this.name = name;
	}
    /**
     * Gets the port number of the fan
     */
	@Override
	public String getPortNumber() {
		return this.portNumber;
	}
    /**
     * Sets the port number for the fan
     * @param portNumber
     */
	@Override
	public void setPortNumber(String portNumber) {
		this.portNumber = portNumber;
	}
    /**
     * Returns true if possible to use another device
     */
	@Override
	public boolean isUseNext() {
		return this.useNext;
	}
    /**
     * Sets the next device to use if isUseNext is true
     * @param useNext
     */
	@Override
	public void setUseNext(boolean useNext) {
		this.useNext = useNext;
	}
}