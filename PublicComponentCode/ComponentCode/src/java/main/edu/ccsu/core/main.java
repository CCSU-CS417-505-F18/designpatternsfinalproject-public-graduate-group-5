package edu.ccsu.core;

import edu.ccsu.factory.DeviceAndSensorFactory;
import edu.ccsu.interfaces.Device;
import edu.ccsu.interfaces.Sensor;

public class main {

	public static void main(String[] args) {
		//instatiate factory to create objects 
		DeviceAndSensorFactory productFactory = new DeviceAndSensorFactory();
		
		//sample devices
		Device display = productFactory.makeDevice("LCD", "MYLCD", "A1");
		Device ledOne = productFactory.makeDevice("LED", "MYLCD", "D3");
		
		System.out.println(display);
		System.out.println(ledOne);
		
		//sample sensors
		Sensor lightSensor = productFactory.makeSensor("LightSensor", "First Light Sensor", "A0");
		Sensor tempSensor = productFactory.makeSensor("TempAndHumiditySensor", "Humidity", "D5");
		
		System.out.println(lightSensor);
		System.out.println(tempSensor);
	}
}