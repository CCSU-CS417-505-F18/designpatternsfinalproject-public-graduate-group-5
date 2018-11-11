package edu.ccsu.factory;

import edu.ccsu.devices.GrovePiFan;
import edu.ccsu.devices.LcdScreen;
import edu.ccsu.devices.Led;
import edu.ccsu.interfaces.Device;
import edu.ccsu.interfaces.Fan;
import edu.ccsu.interfaces.LightEnabledDevice;
import edu.ccsu.interfaces.ProductFactory;
import edu.ccsu.interfaces.ScreenEnabledDevice;
import edu.ccsu.interfaces.Sensor;
import edu.ccsu.sensors.LightSensor;
import edu.ccsu.sensors.TemperatureAndHumiditySensor;
import edu.ccsu.utility.CommonConstants;
/**
 * Class used to dynamically create Device and Sensor objects
 * to be used by client code.
 */
public class DeviceAndSensorFactory implements ProductFactory{

	public DeviceAndSensorFactory() {
		
	}
    /**
     * Makes devices, checking LED, LCD, or Minifan is being created
     * @param device
     * @param name
     * @param portNumber
     */
	@Override
	public Device makeDevice(String device, String name, String portNumber) {
		if(CommonConstants.LED.equalsIgnoreCase(device)) {
			 return new Led(name, portNumber);
		}
		else if(CommonConstants.LCD.equalsIgnoreCase(device)) {
			return new LcdScreen(name, portNumber);
		}
		else if(CommonConstants.MINIFAN.equals(device)) {
			return new GrovePiFan(name, portNumber);
		}
		return null;			
	}
    /**
     * Creates a sensor, checking if Light Sensor or Temperature and Humidity sensor is being created
     * @param sensor
     * @param name
     * @param portNumber
     */
	@Override
	public Sensor makeSensor(String sensor, String name, String portNumber) {
		if(CommonConstants.LIGHT_SENSOR.equalsIgnoreCase(sensor)) {
			return new LightSensor(name, portNumber);
		}
		else if(CommonConstants.TEMP_HUMIDITY_SENSOR.equalsIgnoreCase(sensor)) {
			return new TemperatureAndHumiditySensor(name, portNumber);
		}
		return null;
	}
    /**
     * Creates a light enabled device, checking if LED or LCD
     * @param device
     * @param name
     * @param portNumber
     */
	@Override
	public LightEnabledDevice makeLightEnabledDevice(String device, String name, String portNumber) {
		if(CommonConstants.LED.equalsIgnoreCase(device)) {
			return new Led(name, portNumber);
		}
		else if(CommonConstants.LCD.equalsIgnoreCase(device)) {
			return new LcdScreen(name, portNumber);
		}
		return null;
	}
    /**
     * Creates a screen-enabled device, checks if device being created is LCD
     * @param device
     * @param name
     * @param portNumber
     */
	@Override
	public ScreenEnabledDevice makeScreenEnabledDevice(String device, String name, String portNumber) {
		if(CommonConstants.LCD.equalsIgnoreCase(device)) {
			return new LcdScreen(name, portNumber);
		}
		return null;
	}
    /**
     * Creates a fan device, checking if device being created is Grove mini fan
     * @param device
     * @param name
     * @param portNumber
     */
	@Override
	public Fan makeFan(String device, String name, String portNumber) {
		if(CommonConstants.GROVEMINIFAN.equalsIgnoreCase(device)) {
			return new GrovePiFan(name, portNumber);
		}
		return null;
	}	
}