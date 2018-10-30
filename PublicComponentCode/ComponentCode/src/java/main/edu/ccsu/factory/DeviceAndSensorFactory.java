package edu.ccsu.factory;

import edu.ccsu.devices.LcdScreen;
import edu.ccsu.devices.Led;
import edu.ccsu.interfaces.Device;
import edu.ccsu.interfaces.ProductFactory;
import edu.ccsu.interfaces.Sensor;
import edu.ccsu.sensors.LightSensor;
import edu.ccsu.sensors.TemperatureAndHumiditySensor;
/**
 * Class used to dynamically create Device and Sensor objects
 * to be used by client code.
 */
public class DeviceAndSensorFactory implements ProductFactory{

	public DeviceAndSensorFactory() {
		
	}

	@Override
	public Device makeDevice(String device, String name, String portNumber) {
		if("LED".equalsIgnoreCase(device)) {
			return new Led(name, portNumber);
		}
		else if("LCD".equalsIgnoreCase(device)) {
			return new LcdScreen(name, portNumber);
		}
		else {
			return null;			
		}
	}

	@Override
	public Sensor makeSensor(String sensor, String name, String portNumber) {
		if("LightSensor".equalsIgnoreCase(sensor)) {
			return new LightSensor(name);
		}
		else if("TempAndHumiditySensor".equalsIgnoreCase(sensor)) {
			return new TemperatureAndHumiditySensor(name);
		}
		return null;
	}	
}