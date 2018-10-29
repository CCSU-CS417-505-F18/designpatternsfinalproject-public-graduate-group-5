package edu.ccsu.core;

import org.python.core.PyObject;
import org.python.core.PyString;
import org.python.util.PythonInterpreter;

import edu.ccsu.error.IncompatibleDeviceError;
import edu.ccsu.factory.DeviceAndSensorFactory;
import edu.ccsu.interfaces.Device;
import edu.ccsu.interfaces.Sensor;

public class Main {

	public static void main(String[] args) {
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
//			ledSix.setNextDevice(ledNine);
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
		ledOne.turnOn();
		ledTwo.turnOff();
		
		
		//sample sensors NOTE-will be implemented in future sprint
		Sensor lightSensor = productFactory.makeSensor("LightSensor", "First Light Sensor", "A0");
		Sensor tempSensor = productFactory.makeSensor("TempAndHumiditySensor", "Humidity", "D5");
		
		System.out.println(lightSensor);
		System.out.println(tempSensor);
		
		
		//TODO - refactor into utility class
		try{
			String path2 = "C:\\Users\\Adrian\\Documents\\CCSU\\Fall 2018\\CS505\\designpatternsfinalproject-public-graduate-group-5\\PublicComponentCode\\ComponentCode\\pythonScripts\\test.py";
			System.out.println(path2);
			 // Create an instance of the PythonInterpreter
	        PythonInterpreter interpreter = new PythonInterpreter();
	        interpreter.execfile(path2);
	        PyObject helloFunction = interpreter.get("message");
	        PyObject result = helloFunction.__call__(new PyString("TESTING"), new PyString("hello"));			
			
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
}