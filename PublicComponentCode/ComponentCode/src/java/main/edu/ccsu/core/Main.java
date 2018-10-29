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
		Device ledOne = productFactory.makeDevice("LED", "MYLCD", "D3");
		Device ledTwo = productFactory.makeDevice("LED", "MYLCD2", "D4");
		try {
			ledOne.setNextDevice(ledTwo);
		} catch (IncompatibleDeviceError e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println(display);
		System.out.println(ledOne);
		
		//sample sensors
		Sensor lightSensor = productFactory.makeSensor("LightSensor", "First Light Sensor", "A0");
		Sensor tempSensor = productFactory.makeSensor("TempAndHumiditySensor", "Humidity", "D5");
		
		System.out.println(lightSensor);
		System.out.println(tempSensor);
		
		//sample code to check operating system
		System.out.println(System.getProperty("os.name"));
		
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