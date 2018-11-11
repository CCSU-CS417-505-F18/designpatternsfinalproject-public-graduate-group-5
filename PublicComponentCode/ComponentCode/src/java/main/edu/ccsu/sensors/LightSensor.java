package edu.ccsu.sensors;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.ccsu.error.IncompatibleSensorError;
import edu.ccsu.interfaces.Iterator;
import edu.ccsu.interfaces.Sensor;
import edu.ccsu.utility.CommonConstants;

/**
 * Class can access light sensor and 
 * get the light intensity
 */
public class LightSensor extends Sensor {

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
		this.sensorFile = CommonConstants.LIGHTSENSOR;
	}
	
	/**
	 * Method that takes data string, parses it, creates LightSensorData objects,
	 * and adds them to list
	 * @param data
	 */
	@Override
	protected void addToList(String data) {
		String[] dataToAdd = data.split(",");
		for(String str: dataToAdd) {
			String[] makeIntoData = str.split(" ");
			//value from output will be three numbers, first is sensorValue second is voltage, third is watts
			sensorData.add(new LightSensorData(Integer.parseInt(makeIntoData[0]), Float.parseFloat(makeIntoData[1]), Float.parseFloat(makeIntoData[2]), new Date()));
		}
	}
    
	@Override
	protected boolean checkPort(String portNumber) {
		if(!this.getPortNumber().contains("A")) {
			System.out.println("Must use a digital port starting with A");
			return false;
		}
		return true;
	}
	
	@Override
	public void setNextSensor(Sensor nextSensor, String portNumber) throws IncompatibleSensorError  {
		if(nextSensor instanceof LightSensor) {
			this.nextSensor = nextSensor;
		} else {
			throw new IncompatibleSensorError("Sensor not compatible with sensor. Sensor chain can only be use other Sensors");
		}
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
        
		@Override
		public boolean hasNext() {
			if(index < sensorData.size() )
				return true;
			return false;
		}
        
		@Override
		public LightSensorData next() {
			if(this.hasNext())
				return sensorData.get(index++);
			return null;
		}
	}
}