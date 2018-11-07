package edu.ccsu.sensors;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.ccsu.error.IncompatibleSensorError;
import edu.ccsu.interfaces.Iterator;
import edu.ccsu.interfaces.Sensor;

/***
 * Temperature and humidity sensor that reads data from
 * the GrovePi temp/humidity sensor and returns that data
 *
 */
public class TemperatureAndHumiditySensor implements Sensor {

	private Sensor nextSensor;
	private String portNumber;
	private String name;
	private List<TempAndHumidityData> sensorData;
	
	public TemperatureAndHumiditySensor(String name, String portNumber) {
		this.name = name;
		this.portNumber = portNumber;
		this.sensorData = new ArrayList<>();
		sensorData.add(new TempAndHumidityData(56, 45, new Date()));
	}

	//NOTE - will call python, parse string and store TempAndHumidityData objects 
	//		 in the sensorData list 
	@Override
	public String getData(String desiredData) {
		//if user just wants humidity only get that
		//if user just wants temp just get that
		//if user wants both grab both
		String returnValue = "";
//		if(desiredData.equals("d")) returnValue = Double.toString(degreesFahrenheit);
//		else if(desiredData.equals("h")) returnValue = Double.toString(humidityValue);
//		else if(desiredData.equals("b")) returnValue = Double.toString(degreesFahrenheit)+":"+Double.toString(humidityValue);
		return returnValue;
	}

	@Override
	public String getData(int seconds) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setNextSensor(Sensor nextSensor, String portNumber) throws IncompatibleSensorError {
		if(nextSensor instanceof TemperatureAndHumiditySensor) {
			this.nextSensor = nextSensor;
		} else {
			throw new IncompatibleSensorError("Sensor not compatible with sensor. Sensor chain can only be use other Sensors");
		}
	}

	/**
	 * Returns the next sensor in the chain
	 * @return
	 */
	@Override
	public Sensor getNextSensor() {
		return this.nextSensor;
	}

	@Override
	public boolean isAvailable(Sensor sensor, String portNumber) {
		// TODO : connect with python file 
		return false;
	}

	@Override
	public String getPortNumber() {
		return portNumber;
	}

	@Override
	public void setPortNumber(String portNumber) {
		this.portNumber = portNumber;
	}

	@Override
	public Iterator getIterator() {
		// TODO Auto-generated method stub
		return new TempAndHumidityIterator();
	}
	
	/**
	 * Inner class to store data 
	 * @author Adrian
	 *
	 */
	private class TempAndHumidityData{
		 double degreesFahrenheit;
		 double humidityValue;
		 Date date;
		 
		 public TempAndHumidityData(double degreesFahrenheit, double humidityValue, Date date) {
			 this.degreesFahrenheit = degreesFahrenheit;
			 this.humidityValue = humidityValue;
			 this.date = date;
		 }
		 
		 public double getDegress() {
			 return this.degreesFahrenheit;
		 }
		 
		 public double getHumidityValue() {
			 return this.humidityValue;
		 }
		 
		 public Date getDate() {
			 return this.date;
		 }
		 
		 public String toString() {
			 return "\n******************\n" + 
					 "DegressFahrenheit: " + this.degreesFahrenheit + "\n" +
					 "Humidity Value: " + this.humidityValue + "\n" +
					 "Date: " + this.date + 
					 "\n******************\n";
		 }
		
	}
	/**
	 * Inner Class for iterating over tempAndHumidity data
	 * */
	private class TempAndHumidityIterator implements Iterator{

		int index;

		@Override
		public boolean hasNext() {
			if(index < sensorData.size() )
				return true;
			return false;
		}

		@Override
		public TempAndHumidityData next() {
			if(this.hasNext())
				return sensorData.get(index++);
			return null;
		}

		public List<String> filter(String filter) {
			List<String> filteredData = new ArrayList<>();
			for(TempAndHumidityData data: sensorData) {
				
			}
			return filteredData;
		}
	}
}