package edu.ccsu.sensors;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.ccsu.error.IncompatibleSensorError;
import edu.ccsu.interfaces.Iterator;
import edu.ccsu.interfaces.Sensor;
import edu.ccsu.utility.CommonConstants;
import edu.ccsu.utility.UtilityMethods;

/**
 * Class can access light sensor and 
 * get the light intensity
 */
public class LightSensor implements Sensor {

	private Sensor nextSensor;
	private String name;
	private String portNumber;
	private List<LightSensorData> sensorData;
	/**
	 * Creates a concrete LightSensor object
	 * @param name
	 * @param portNumber
	 */
	public LightSensor(String name, String portNumber) {
		this.name = name;
		this.portNumber = portNumber;
		this.sensorData = new ArrayList<>();
	}
	/**
	 * Gets the data from the light sensor, reading for a certain number of seconds
	 * Checks the port number of sensor and operating system first
	 * @param seconds
	 */
	@Override
	public String getData(int seconds) {
		String data = "";
		if(!this.getPortNumber().contains("A")) {
			System.out.println("Must use a digital port starting with A");
		}
		else if(UtilityMethods.checkOperatingSystem()) {
			data = UtilityMethods.callPython(CommonConstants.LIGHTSENSOR, this.portNumber.substring(1) + CommonConstants.BLANK + Integer.toString(seconds));
			
			addToList(data);
		} 
		else {
			System.out.println("Cannot turn on Fan: " + this.name);
		}
		return data;	
	}
	
	/**
	 * Method that takes data string, parses it, creates LightSensorData objects,
	 * and adds them to list
	 * @param data
	 */
	private void addToList(String data) {
		String[] dataToAdd = data.split(",");
		System.out.println("Adding data to list");
		for(String str: dataToAdd) {
			System.out.println(str);
			String[] makeIntoData = str.split(" ");
			//value from output will be three numbers, first is sensorValue second is voltage, third is watts
			sensorData.add(new LightSensorData(Integer.parseInt(makeIntoData[0]), Float.parseFloat(makeIntoData[1]), Float.parseFloat(makeIntoData[2]), new Date()));
		}
	}
    /**
     * Sets the next sensor in the chain. Must be sensor type to add to chain
     * @param nextSensor
     * @param portNumber
     */
	@Override
	public void setNextSensor(Sensor nextSensor, String portNumber) throws IncompatibleSensorError  {
		if(nextSensor instanceof LightSensor) {
			this.nextSensor = nextSensor;
		} else {
			throw new IncompatibleSensorError("Sensor not compatible with sensor. Sensor chain can only be use other Sensors");
		}
	}

	/**
	 * Gets the port number of the sensor
	 * @return
	 */
	public String getPortNumber() {
		return portNumber;
	}
	
	/**
	 * Sets the port number of the sensor
	 * @param portNumber
	 */
	public void setPortNumber(String portNumber) {
		this.portNumber = portNumber;
	}

	/**
	 * Gets the next sensor in the chain
	 * @return
	 */
	public Sensor getNextSensor() {
		return nextSensor;
	}

	/**
	 * Sets the next sensor in the chain
	 * @param nextSensor
	 */
	public void setNextSensor(Sensor nextSensor) {
		if(nextSensor instanceof LightSensor) {
			this.nextSensor = nextSensor;
		} 
	}

	/**
	 * Gets the name of the sensor
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the sensor
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * Creates a sensor iterator and returns new
	 */
	@Override
	public Iterator getIterator() {
		return new SensorIterator();
	}
	
	/**
	 * Class to hold LightSensorData.
	 * @author Adrian
	 *
	 */
	private class LightSensorData{
		int sensorValue;
		float watts;
		float voltage;
		Date date;
		/**
		 * Takes in data from the light sensor to hold it
		 * @param sensor
		 * @param voltage
		 * @param watts
		 * @param date
		 */
		public LightSensorData(int sensor, float voltage, float watts, Date date) {
			this.sensorValue = sensor;
			this.voltage =  voltage;
			this.watts = watts;
			this.date = date;
		}
		/**
		 * Returns data as string
		 */
		 public String toString(){
			return "\n**********************\n" +
					"SensorValue: " + this.sensorValue + "\n" +
					"Voltage: " + this.voltage + "\n" + 
					"Watts: " + this.watts + "\n" +
					"Date: " + this.date + "\n" +
					"**********************\n";
		}
	}
	/**
	 * Class provides iteration logic 
	 * */
	private class SensorIterator implements Iterator{
		
		int index;
        /**
         * Checks if there is more data to iterate
         */
		@Override
		public boolean hasNext() {
			if(index < sensorData.size() )
				return true;
			return false;
		}
        /**
         * Checks if light sensor has more data to iterate
         */
		@Override
		public LightSensorData next() {
			if(this.hasNext())
				return sensorData.get(index++);
			return null;
		}
	}
}