package edu.ccsu.sample;

import edu.ccsu.error.IncompatibleDeviceError;
import edu.ccsu.factory.DeviceAndSensorFactory;
import edu.ccsu.interfaces.Iterator;
import edu.ccsu.interfaces.LightEnabledDevice;
import edu.ccsu.interfaces.ProductFactory;
import edu.ccsu.interfaces.ScreenEnabledDevice;
import edu.ccsu.interfaces.Sensor;
/**
 * This main class is simply here to demonstrate how to use our code.
 * @author Adrian
 * @author Kim
 * @author Ga Young
 *
 */
public class Main {

	public static void main(String[] args) throws InterruptedException {
		//instantiate factory to create objects 
		ProductFactory productFactory = new DeviceAndSensorFactory();
		
		/*
		 * Example of how to use iterator for sensors
		 * */
		Sensor lightSensor = productFactory.makeSensor("LightSensor", "test", "A0");
		Iterator itr = lightSensor.getIterator();
		while(itr.hasNext())
			System.out.println("Iterator Test " + itr.next());
		Sensor tempAndHumid = productFactory.makeSensor("TempAndHumiditySensor", "test", "A1");
		Iterator itrTemp = tempAndHumid.getIterator();
		while(itrTemp.hasNext()) {
			System.out.println("TempAndHumidity Iterator: " + itrTemp.next());
		}
		
		
		// lightSensor test - getting data for 10 seconds
		String str = lightSensor.getData(10);
		if(str == null) str = "NONE";
		System.out.println("DATA from light sensor :"+str);

		// lightSensor test - getting data with string argument
		String lightstr = lightSensor.getData("name");
		System.out.println("DATA without python :"+lightstr);
		
		
		//sample devices
		/*
		 * NOTE that we have a hierarchy of interfaces extending Device interface.  If you just 
		 * need the basic functionality of Device interface use productFactory.makeDevice(...)
		 * If you need specific methods associated with different devices, use the specified methods in DeviceAndSensorFactory
		 *  */
		ScreenEnabledDevice display = productFactory.makeScreenEnabledDevice("LCD", "MYLCD", "I2C-1");
		display.printMessage("Hello World");
		
		LightEnabledDevice ledOne =  productFactory.makeLightEnabledDevice("LED", "LED", "D3");
		LightEnabledDevice ledTwo =  productFactory.makeLightEnabledDevice("LED", "LED2", "D5");
		LightEnabledDevice ledThree = productFactory.makeLightEnabledDevice("LED", "LED3", "D5");
		
		LightEnabledDevice ledFour =  productFactory.makeLightEnabledDevice("LED", "LED", "D3");
		LightEnabledDevice ledSix =   productFactory.makeLightEnabledDevice("LED", "LED2", "D5");
		LightEnabledDevice ledNine =  productFactory.makeLightEnabledDevice("LED", "LED3", "D5");
		
		/*
		 * Sample of building CoR with LEDs
		 * */
		try {
			//set ledOne chain
			ledOne.setNextDevice(ledTwo);
			ledTwo.setNextDevice(ledThree);
			
			//set ledFour chain
			ledFour.setNextDevice(ledSix);
			ledSix.setNextDevice(ledNine);
			
			/*
			 * Setting LED equal to device not of type LED will throw an error
			 * */
			//ledThree.setNextDevice(display);
		} catch (IncompatibleDeviceError e1) {
			e1.printStackTrace();
		}
		
		
		
		
		/*
		 * No need to do this but just checking that chain was properly created
		 * */ 
		//System.out.println(ledOne.getNextDevice().getNextDevice().getName());
		
		/*
		 * Demonstration of the equals method
		 * */
		System.out.println("***************");
		System.out.println("Check that ledOne equals ledeFour");
		System.out.println(ledOne.equals(ledFour));
		System.out.println("***************");
		/*
		 * Printing hash codes because why not
		 * */
		System.out.println("***************");
		System.out.println(ledOne.hashCode());
		System.out.println(ledFour.hashCode());
		System.out.println("***************");
		/*
		 * Simple demo of how to use turnOn and turnOff
		 * Note that Thread.sleep is simply here to allow you so see the LEDs being
		 * toggled and off otherwise the program would run so fast you'd miss it!
		 * If you are not running this code on Raspberry Pi it methods will print message
		 * telling you to run it on Raspberry Pi
		 * */
		System.out.println("Toggle " + ledOne.getName() + " On and Off");
		ledOne.turnOn();
		Thread.sleep(1800);
		ledOne.turnOff();
		Thread.sleep(1800);
		
		
		
		
		//at the moment blink speed cannot be adjusted
		System.out.println("Blinking led " + ledOne.getName());
		ledOne.blink(2);
		Thread.sleep(1800);
		
		/*
		 * Demo of adjusting brightness.  Note that by default LEDs will attempt to use next led in chain
		 * Use the setUseNext() method to adjust this behavior
		 * */
		System.out.println("Adjusting brightness of " + ledOne.getName());
		ledOne.adjustBrightness(500);
		Thread.sleep(1800);
		ledOne.adjustBrightness(1023);
		Thread.sleep(1800);
		ledOne.adjustBrightness(0);
		/*
		 * Try using adjustBrightness on ledTwo it's not set to port that can handle 
		 * pulse wave modulation. ledTwo is set to use ledThree in this situation...run it to 
		 * confirm that it works.  Using CoR it will use the next LED in the chain
		 * */
		System.out.println("Adjust brightness of " + ledTwo.getName());
		ledTwo.adjustBrightness(1023);
		Thread.sleep(1800);
		System.out.println("Again, Adjust brightness of " + ledTwo.getName());
		ledTwo.adjustBrightness(0);	
		System.out.println("Deactivate CoR");
		ledTwo.setUseNext(false);
		ledTwo.adjustBrightness(0);
		

		
	}
}
