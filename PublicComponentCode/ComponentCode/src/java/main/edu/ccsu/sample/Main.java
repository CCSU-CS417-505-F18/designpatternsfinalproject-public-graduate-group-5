package edu.ccsu.sample;

import java.util.List;

import edu.ccsu.error.IncompatibleDeviceError;
import edu.ccsu.error.PortInUseException;
import edu.ccsu.factory.DeviceAndSensorFactory;
import edu.ccsu.interfaces.Fan;
import edu.ccsu.interfaces.Iterator;
import edu.ccsu.interfaces.LightEnabledDevice;
import edu.ccsu.interfaces.ProductFactory;
import edu.ccsu.interfaces.ScreenEnabledDevice;
import edu.ccsu.utility.PortManagement;
import grovepicomponents.Sensor;
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
		
		Fan fan = productFactory.makeFan("minifan", "myFan", "D6");
		System.out.println("************************");
		System.out.println("Testing Fan");
		fan.turnOn();
		Thread.sleep(1800l);
		fan.adjustSpeed(40);
		Thread.sleep(1800l);
		fan.turnOff();
		System.out.println("************************");
		/*
		 * Example of how to use iterator for sensors
		 * */
		Sensor lightSensor = productFactory.makeSensor("LightSensor", "test", "A0");
		System.out.println("************************");
		System.out.println("Testing LightSensor Iterator");
		lightSensor.getData(2);
		
		Iterator itr = lightSensor.getIterator();
		while(itr.hasNext())
			System.out.println(itr.next());
		System.out.println("************************");
		System.out.println("Testing Temp and Humidity Iterator");
		Sensor tempAndHumid = productFactory.makeSensor("TempAndHumiditySensor", "test", "D2");
		tempAndHumid.getData(3);

		Iterator itrTemp = tempAndHumid.getIterator();
		while(itrTemp.hasNext()) {
			System.out.println("TempAndHumidity Iterator: " + itrTemp.next());
		}
		
		//sample devices
		/*
		 * NOTE that we have a hierarchy of interfaces extending Device interface.  If you just 
		 * need the basic functionality of Device interface use productFactory.makeDevice(...)
		 * If you need specific methods associated with different devices, use the specified methods in DeviceAndSensorFactory
		 *  */
		ScreenEnabledDevice display = productFactory.makeScreenEnabledDevice("LCD", "MYLCD", "I2C-1");
		System.out.println("Turning on Lcd Screen");
		display.turnOn();
		Thread.sleep(1800l);
		System.out.println("Turning off Lcd Screen");
		display.turnOff();
		
		display.printMessage("Hello World");
		System.out.println("Setting color to Cyan");
		display.printMessageColor("Hello, Team","Cyan");
		Thread.sleep(1800l);
		System.out.println("Setting color to Blue");
		display.printMessageColor("Hello, Team","Blue");
		Thread.sleep(1800l);
		System.out.println("Setting color to Red");
		display.printMessageColor("Hello, Team","Red");		
		Thread.sleep(1800l);
		System.out.println("Adjust brightness of " + display.getName()+"to 3");
		display.adjustBrightness(3);
		Thread.sleep(1800l);
		System.out.println("Adjust brightness of " + display.getName()+"to 10");
		display.adjustBrightness(10);
		Thread.sleep(1800l);
		System.out.println("Adjust brightness of " + display.getName()+"to 1");
		display.adjustBrightness(1);
		Thread.sleep(1800l);
		System.out.println("Blink 3 times");
		display.blink(3);

		
		LightEnabledDevice ledOne =  productFactory.makeLightEnabledDevice("LED", "LED", "D3");
		LightEnabledDevice ledTwo =  productFactory.makeLightEnabledDevice("LED", "LED2", "D4");
		LightEnabledDevice ledThree = productFactory.makeLightEnabledDevice("LED", "LED3", "D5");
		try {
			lightSensor.setPortNumber("D3");
		}
		catch(PortInUseException p) {
			//p.printStackTrace();
			List<String>pInUse = PortManagement.getPortsInUse();
			for(String port: pInUse)
				System.out.println(port);
			//NOTE: logic to handle situation goes here
			//if doing UI you may offer user list of used ports and ask them to choose an open one
		}
		
		//If you try to create an object on a port already in use the return value will be null
		LightEnabledDevice ledFour =  productFactory.makeLightEnabledDevice("LED", "LED", "D3");
		System.out.println(ledFour == null);
	
		/*
		 * Sample of building CoR with LEDs
		 * */
		try {
			//set ledOne chain
			ledOne.setNextDevice(ledTwo);
			ledTwo.setNextDevice(ledThree);
			
			/*
			 * Setting LED equal to device not of type LED will throw an error
			 * */
			//ledThree.setNextDevice(display);
		} catch (IncompatibleDeviceError e1) {
			//e1.printStackTrace();
			System.out.println("Incompatible Device Error!!!");
		}

		/*
		 * Simple demo of how to use turnOn and turnOff
		 * Note that Thread.sleep is simply here to allow you so see the LEDs being
		 * toggled and off otherwise the program would run so fast you'd miss it!
		 * If you are not running this code on Rapsberry Pi it methods will print message
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