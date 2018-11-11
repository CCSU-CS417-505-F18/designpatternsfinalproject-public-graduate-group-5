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
		//port must be a digital port starting with I
		if(!this.getPortNumber().contains("I")) {
			System.out.println("Must use a digital port starting with I");
		}
		else if(UtilityMethods.checkOperatingSystem()) {
			
			UtilityMethods.callPython(CommonConstants.GROVE_LCD, buildMessage(message));			
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
	 * Print a message to the LCD RGB Backlight for
	 * a certain number of seconds
	 * @param message
	 * @param duration
	 */
	public void printMessage(String message, int duration) {
		//port must be a digital port starting with I
		if(!this.getPortNumber().contains("I")) {
			System.out.println("Must use a digital port starting with I");
		}
		else if(UtilityMethods.checkOperatingSystem()) {
			
			UtilityMethods.callPython(CommonConstants.GROVE_LCD_TIME, buildMessage(message));			
		}
		else {
			System.out.println("Cannot turn on LCD: " + this.name);
		}					
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
		//port must be a digital port starting with I
		if(!this.getPortNumber().contains("I")) {
			System.out.println("Must use a digital port starting with I");
		}
		else if(UtilityMethods.checkOperatingSystem()) {
			
            UtilityMethods.callPython(CommonConstants.GROVE_LCD_BRIGHTNESS,""+brightness);

		}
		else {
			System.out.println("Cannot turn on LCD: " + this.name);
		}									
	}
    
	@Override
	public void blink(int numberOfSeconds) {
		//port must be a digital port starting with I
		if(!this.getPortNumber().contains("I")) {
			System.out.println("Must use a digital port starting with I");
		}
		else if(UtilityMethods.checkOperatingSystem()) {
			
            UtilityMethods.callPython(CommonConstants.GROVE_LCD_BLINK,""+numberOfSeconds);

		}
		else {
			System.out.println("Cannot turn on LCD: " + this.name);
		}							
	}
	
	@Override
	public String toString() {
		return "Name: " + this.name + "\n" +
				"Port Number: " + this.portNumber;	}
    
	@Override
	public void turnOn() {
        if(!this.getPortNumber().contains("I")) {
        	System.out.println(this.getPortNumber());
            System.out.println("Must use a digital port starting with I");
       }
        else if(UtilityMethods.checkOperatingSystem()) {
            UtilityMethods.callPython(CommonConstants.GROVE_LCD_ONOFF, this.portNumber.substring(1) + CommonConstants.BLANK + CommonConstants.ON);
        }
        else {
            System.out.println("Cannot turn on LcdScreen: " + this.name);
        }
		
	}
    
	@Override
	public void turnOff() {
        if(!this.getPortNumber().contains("I")) {
            System.out.println("Must use a digital port starting with I");
        }
        else if(UtilityMethods.checkOperatingSystem()) {
            UtilityMethods.callPython(CommonConstants.GROVE_LCD_ONOFF, this.portNumber.substring(1) + CommonConstants.BLANK + CommonConstants.OFF);
        }
        else {
            System.out.println("Cannot turn on LcdScreen: " + this.name);
        }
		
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

	public int hashCode() {
        	int hash = 7;
	        hash = 23 * hash + Objects.hashCode(this.getNextDevice());
	        hash = 23 * hash + Objects.hashCode(this.name);
	        hash = 23 * hash + Objects.hashCode(this.portNumber);
	        return hash;
    }


}
