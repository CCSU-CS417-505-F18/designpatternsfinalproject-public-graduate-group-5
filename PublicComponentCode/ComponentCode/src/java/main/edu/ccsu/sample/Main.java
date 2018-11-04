package edu.ccsu.sample;

import edu.ccsu.error.IncompatibleDeviceError;
import edu.ccsu.factory.DeviceAndSensorFactory;
import edu.ccsu.interfaces.Device;
import edu.ccsu.utility.CommonConstants;
import edu.ccsu.utility.UtilityMethods;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		//instantiate factory to create objects 
		DeviceAndSensorFactory productFactory = new DeviceAndSensorFactory();
		
		//sample devices
		Device display = productFactory.makeDevice("LCD", "MYLCD", "A1");
		
		Device ledOne = productFactory.makeDevice("LED", "LED", "D3");
		Device ledTwo = productFactory.makeDevice("LED", "LED2", "D4");
		Device ledThree = productFactory.makeDevice("LED", "LED3", "D5");
		
		Device ledFour = productFactory.makeDevice("LED", "LED", "D3");
		Device ledSix = productFactory.makeDevice("LED", "LED2", "D4");
		Device ledNine = productFactory.makeDevice("LED", "LED3", "D5");
		
		Device ledTen = productFactory.makeDevice("LED", "LED10", "D7");
		//sample CoR with LEDs
		try {
			//set ledOne chain
			ledOne.setNextDevice(ledTwo);
			ledTwo.setNextDevice(ledThree);
			
			//set ledFour chain
			ledFour.setNextDevice(ledSix);
			ledSix.setNextDevice(ledNine);
//			ledNine.setNextDevice(productFactory.makeDevice("LED", "BOB", "A0"));
			
			//doing this will throw an error 
//			ledThree.setNextDevice(display);
		} catch (IncompatibleDeviceError e1) {
			e1.printStackTrace();
		}
		
		//check that CoR was set properly 
		System.out.println(ledOne.getNextDevice().getNextDevice().getName());
		
		//use equals method on device
		System.out.println(ledOne.equals(ledFour));
		//methods to turn on and off LEDs...NOTE will print message that it failed if not using on Raspbian
		System.out.println("LED");
		ledOne.turnOn();
		Thread.sleep(1800L);
		ledOne.turnOff();
		Thread.sleep(1800);
		ledOne.blink(3);
		//System.out.println(UtilityMethods.callPython(CommonConstants.TEST_PY, null));
	}
}