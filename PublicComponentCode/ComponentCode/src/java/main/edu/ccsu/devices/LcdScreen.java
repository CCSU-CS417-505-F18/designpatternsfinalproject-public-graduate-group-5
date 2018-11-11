package edu.ccsu.devices;

import java.util.Objects;
import edu.ccsu.error.IncompatibleDeviceError;
import edu.ccsu.interfaces.Device;
import edu.ccsu.interfaces.ScreenEnabledDevice;
import edu.ccsu.utility.CommonConstants;
import edu.ccsu.utility.UtilityMethods;

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
		this.useNext = false;
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
			this.setUseNext(true);
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
<<<<<<< HEAD
        if(!this.getPortNumber().contains("I")) {
            System.out.println("Must use a digital port starting with I");
        }
        else if(UtilityMethods.checkOperatingSystem()) {
            UtilityMethods.callPython(CommonConstants.GROVE_LCD, this.portNumber.substring(1) + CommonConstants.BLANK + CommonConstants.ON);
        }
        else {
            System.out.println("Cannot turn on LcdScreen: " + this.name);
        }
=======
>>>>>>> f2275b2bd21f4461ac5ae3d9e5e1e199252cae5c
		
	}
    
	@Override
	public void turnOff() {
<<<<<<< HEAD
        if(!this.getPortNumber().contains("I")) {
            System.out.println("Must use a digital port starting with I");
        }
        else if(UtilityMethods.checkOperatingSystem()) {
            UtilityMethods.callPython(CommonConstants.GROVE_LCD, this.portNumber.substring(1) + CommonConstants.BLANK + CommonConstants.OFF);
        }
        else {
            System.out.println("Cannot turn on LcdScreen: " + this.name);
        }
=======
>>>>>>> f2275b2bd21f4461ac5ae3d9e5e1e199252cae5c
		
	}
    
	@Override
	public String getName() {
<<<<<<< HEAD
		return name;
=======
		return null;
>>>>>>> f2275b2bd21f4461ac5ae3d9e5e1e199252cae5c
	}
   
	@Override
	public void setName(String name) {
<<<<<<< HEAD
		this.name = name;	
=======
		
>>>>>>> f2275b2bd21f4461ac5ae3d9e5e1e199252cae5c
	}
    
	@Override
	public String getPortNumber() {
<<<<<<< HEAD
		return portNumber;
=======
		return null;
>>>>>>> f2275b2bd21f4461ac5ae3d9e5e1e199252cae5c
	}
    
	@Override
	public void setPortNumber(String portNumber) {
<<<<<<< HEAD
		this.portNumber = portNumber;		
=======
		
>>>>>>> f2275b2bd21f4461ac5ae3d9e5e1e199252cae5c
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
<<<<<<< HEAD
		return nextDevice;
=======
		return null;
>>>>>>> f2275b2bd21f4461ac5ae3d9e5e1e199252cae5c
	}
    
	@Override
	public boolean isUseNext() {
<<<<<<< HEAD
		return useNext;
=======
		return false;
>>>>>>> f2275b2bd21f4461ac5ae3d9e5e1e199252cae5c
	}
    
	@Override
	public void setUseNext(boolean useNext) {
<<<<<<< HEAD
		this.useNext = useNext;		
	}

    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.getNextDevice());
        hash = 23 * hash + Objects.hashCode(this.name);
        hash = 23 * hash + Objects.hashCode(this.portNumber);
        return hash;
    }

=======
		
	}

>>>>>>> f2275b2bd21f4461ac5ae3d9e5e1e199252cae5c
}