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
	private double lightIntensity;

	/**
	 * @param name
	 * @param portNumber
	 */
	public LightSensor(String name, String portNumber) {
		this.name = name;
		this.portNumber = portNumber;
		this.sensorData = new ArrayList<>();
		//TODO- delete this later
		sensorData.add(new LightSensorData(100, 200, new Date()));
		sensorData.add(new LightSensorData(200, 500, new Date()));
	}

	/***
	 * Returns String. 		
	 * if user just wants name, then returns name of Light Sensor
	 * if user just wants lightIntensity, then returns lightintensity 
	 * if user wants both grab both
	 * @param desiredData 	name, lightIntensity, both
	 */
	public String getData(String desiredData) {
		String returnValue = "";
		if(desiredData.equals("name")) returnValue = name;
		else if(desiredData.equals("intensity")) returnValue = Double.toString(lightIntensity);
		else if(desiredData.equals("b")) returnValue = name+":"+Double.toString(lightIntensity);
		return returnValue;
	}

	/**
	 * Returns data from python for the time of 'seconds' seconds
	 * if python returns error try next sensor 
	 * if it exist parse output string and add to sensorData
	 * return that original string and maintain data in list...can be accessed again later
	 * @param seconds	seconds getting data from python
	 * @return returns array form string ex) [419, 417, 420 ]
	 */
	public String getData(int seconds) {
		String returnValue = "";
		String desired = "";
		
		if(UtilityMethods.checkOperatingSystem()) {
			returnValue = UtilityMethods.callPython(CommonConstants.GROVE_LIGHT_SENSOR, UtilityMethods.buildArgsString(this.getPortNumber(), seconds+""));
		} else {
			if(this.nextSensor != null) nextSensor.getData(seconds);
			else {
				System.out.println("Cannot use the light sensor: " + this.getName() + ". Must run on raspberry pi");
			}
		}		
		return returnValue;
	}

	@Override

	public void setNextSensor(Sensor nextSensor, String portNumber) throws IncompatibleSensorError  {
		if(nextSensor instanceof LightSensor) {
			this.nextSensor = nextSensor;
		} else {
			throw new IncompatibleSensorError("Sensor not compatible with sensor. Sensor chain can only be use other Sensors");
		}
	}
	
	@Override
	public boolean isAvailable(Sensor sensor, String portNumber) {
		// TODO  connect with python file 
		return false;
	}

	/**
	 * 
	 * @return
	 */
	public String getPortNumber() {
		return portNumber;
	}
	
	/**
	 * 
	 * @param portNumber
	 */
	public void setPortNumber(String portNumber) {
		this.portNumber = portNumber;
	}

	/**
	 * 
	 * @return
	 */
	public Sensor getNextSensor() {
		return nextSensor;
	}

	/**
	 * 
	 * @param nextSensor
	 */
	public void setNextSensor(Sensor nextSensor) {
		if(nextSensor instanceof LightSensor) {
			this.nextSensor = nextSensor;
		} 
	}

	/**
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
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
	 * @author Adrian, GaYoung, Kim
	 *
	 */
	private class LightSensorData{
		int lumens;
		int voltage;
		Date date;
		
		public LightSensorData(int lumens, int voltage, Date date) {
			this.lumens = lumens;
			this.voltage =  voltage;
			this.date = date;
		}
		
		public int getLumens() {
			return this.lumens;
		}
		
		public int getVoltage() {
			return this.voltage;
		}
		
		public Date getDate() {
			return this.date;
		}
		
		 public String toString(){
			return "\n**********************\n" +
					"Lumens: " + this.lumens + "\n" +
					"Voltage: " + this.voltage + "\n" + 
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

		public List<String> filter(String filter) {
			List<String> filteredData = new ArrayList<>();
			for(LightSensorData data: sensorData) {
				if("lumens".equalsIgnoreCase(filter))
					filteredData.add(Integer.toString(data.getLumens()));
				else
					filteredData.add(Integer.toString(data.getVoltage()));
			}
			return filteredData;
		}
	}
}