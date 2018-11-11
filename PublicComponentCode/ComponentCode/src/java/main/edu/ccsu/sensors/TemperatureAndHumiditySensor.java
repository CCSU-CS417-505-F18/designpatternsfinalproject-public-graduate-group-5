package edu.ccsu.sensors;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.ccsu.error.IncompatibleSensorError;
import edu.ccsu.interfaces.Iterator;
import edu.ccsu.interfaces.Sensor;
import edu.ccsu.utility.CommonConstants;
import edu.ccsu.utility.UtilityMethods;

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
	/**
	 * Creates a new Temperature and Humidity Sensor
	 * @param name
	 * @param portNumber
	 */
	public TemperatureAndHumiditySensor(String name, String portNumber) {
		this.name = name;
		this.portNumber = portNumber;
		this.sensorData = new ArrayList<>();
	}
    
	@Override
	public String getData(int seconds) {
		String data = "";
		if(!this.getPortNumber().contains("D")) {
			System.out.println("Must use a digital port starting with D");
		}
		else if(UtilityMethods.checkOperatingSystem()) {
			data = UtilityMethods.callPython(CommonConstants.TEMPHUMIDITY, this.portNumber.substring(1) + CommonConstants.BLANK + Integer.toString(seconds));
			System.out.println("data " + data);
			if(!data.isEmpty() && !data.contains("nan values"))
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
			//value from output will be three numbers, first is fahrenheit, second is celcius, third is humidity percentage
			sensorData.add(new TempAndHumidityData(Float.parseFloat(makeIntoData[0]), Float.parseFloat(makeIntoData[1]), Float.parseFloat(makeIntoData[2]), new Date()));
		}
	}
	
	@Override
	public void setNextSensor(Sensor nextSensor, String portNumber) throws IncompatibleSensorError {
		if(nextSensor instanceof TemperatureAndHumiditySensor) {
			this.nextSensor = nextSensor;
		} else {
			throw new IncompatibleSensorError("Sensor not compatible with sensor. Sensor chain can only be use other Sensors");
		}
	}

	
	@Override
	public Sensor getNextSensor() {
		return this.nextSensor;
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
		return new TempAndHumidityIterator();
	}
	
	/**
	 * Inner class to store data 
	 * @author Adrian
	 *
	 */
	private class TempAndHumidityData{
		 float degreesFahrenheit;
		 float celcius;
		 float humidityValue;
		 Date date;
		 /**
		  * Takes in data from temperature and humidity sensor and stores it
		  * @param degreesFahrenheit
		  * @param celcius
		  * @param humidityValue
		  * @param date
		  */
		 public TempAndHumidityData(float degreesFahrenheit, float celcius, float humidityValue, Date date) {
			 this.degreesFahrenheit = degreesFahrenheit;
			 this.celcius = celcius;
			 this.humidityValue = humidityValue;
			 this.date = date;
		 }
		 /**
		  * Returns data as string
		  */
		 public String toString() {
			 return "\n******************\n" + 
					 "DegressFahrenheit: " + this.degreesFahrenheit + "\n" +
					 "DegressCelcius: " + this.celcius + "\n" +
					 "Humidity Percentage: " + this.humidityValue + "\n" +
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
	}
}