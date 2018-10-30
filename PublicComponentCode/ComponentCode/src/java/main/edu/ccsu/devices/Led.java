package edu.ccsu.devices;

import org.python.core.PyInteger;
import org.python.core.PyObject;
import org.python.core.PyString;

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

	//TODO - at a later date refactor turnOn()/turnOff() into one switch() method
	public void turnOn() {
		//port must be a digital port starting with D
		if(!this.getPortNumber().contains("D")) {
			System.out.println("Must use a digital port starting with D");
		}
		else if(UtilityMethods.checkOperatingSystem()) {
				//NOTE - parsing port number because we want an integer respresentation of the last character
			System.out.println("Turning on light to be implemented later");
				/*
				PyObject[] pyArray = {new PyString(CommonConstants.ON), new PyInteger(Integer.parseInt(this.getPortNumber().substring(1))) };
				PyObject response = UtilityMethods.callPython(CommonConstants.SWITCH_PY, CommonConstants.SWITCH_PY_SWITCH, pyArray);
				String result = response.asString();
				if(result.equals(CommonConstants.ERROR)) {
					System.out.println("Error occured trying to used LED " + this.name);
					Device nxtDevice = this.getNextDevice();
					if(nxtDevice!= null) {
						nxtDevice.turnOn();
					}
				}
				*/
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
			//NOTE - parsing port number because we want an integer respresentation of the last character
			System.out.println("Turning off light to be implemented in next release");
			/*
			PyObject[] pyArray = {new PyString(CommonConstants.OFF), new PyInteger(Integer.parseInt(this.getPortNumber().substring(1))) };
			PyObject response = UtilityMethods.callPython(CommonConstants.SWITCH_PY, CommonConstants.SWITCH_PY_SWITCH, pyArray);
			String result = response.asString();
			if(result.equals(CommonConstants.ERROR)) {
				System.out.println("Error occured trying to used LED " + this.name);
				Device nxtDevice = this.getNextDevice();
				if(nxtDevice!= null) {
					nxtDevice.turnOn();
				}
			}
			*/
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
			//call python script to turn on light
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
			throw new IncompatibleDeviceError("LED not compatible with device.  LED chain can only be use other LEDs");
		}
	}

	@Override
	public boolean isAvailable(Device device, String portNumber) {
		System.out.println("Will always return true until raspberry pi connection code is developed");
		return true;
	}
	
	@Override
	public void adjustBrightness(int brightness) {
		if(UtilityMethods.checkOperatingSystem()) {
			System.out.println("Call python script to adjust brightness");
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
	
	@Override
	public String toString() {
		return "Name: " + this.name + "\n" +
				"Port Number: " + this.portNumber;
	}
	//TODO implement hashCode
}