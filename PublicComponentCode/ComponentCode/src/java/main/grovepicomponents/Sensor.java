package grovepicomponents;

import edu.ccsu.error.IncompatibleSensorError;
import edu.ccsu.error.PortInUseException;
import edu.ccsu.interfaces.Iterator;
import edu.ccsu.utility.CommonConstants;
import edu.ccsu.utility.PortManagement;

/**
 * Interface that specifies operations on Sensors
 * @author Adrian
 * @author Kim
 * @author Ga Young
 */
public abstract class Sensor {

	protected Sensor nextSensor;
	protected String portNumber;
	protected String name;
	protected String sensorFile;
	private static PortManagement portManagement = PortManagement.getInstance();
	
	/**
	 * Returns a string representation of data for a sensor
	 * collected over a given number of seconds.  To get data for current moment
	 * enter 0 seconds as argument
	 * @param seconds
	 * @return
	 */
	public String getData(int seconds) {
		String data = "";
		if(checkPort(portNumber)) {
		   if(GrovePiUtilities.checkOperatingSystem()) {
				data = GrovePiUtilities.callPython(this.sensorFile, this.portNumber.substring(1) + CommonConstants.BLANK + Integer.toString(seconds));
				if(!data.isEmpty() && !data.contains("nan values"))
					addToList(data);
			} 
			else {
				System.out.println("Cannot get data from: " + this.name);
			}
		}
		return data;
	}
	
	/**
	 * Method to check that portNumber is correct for a given sensor
	 * @param portNumber
	 * @return
	 */
	protected abstract boolean checkPort(String portNumber);
	
	/**
	 * Method used to add sensor data to list of data stored 
	 * internally by concrete sensor classes
	 * @param data
	 */
	protected abstract void addToList(String data);
	
	/**
	 * Set next sensor to use in case of
	 * current sensor throwing error or failing.
	 * @param nextSensor
	 * @param portNumber
	 * @throws IncompatibleSensorError
	 */
	public abstract void setNextSensor(Sensor nextSensor) throws IncompatibleSensorError;
	
	/**
	 * Returns next sensor in CoR
	 * @return Sensor
	 */
	public Sensor getNextSensor() {
		return this.nextSensor;
	}
	
	/**
	 * Returns the iterator for the sensor.
	 * @return
	 */
	public abstract Iterator getIterator();

	/**
	 * Returns port number of Sensor
	 * @return
	 */
	public String getPortNumber() {
		return portNumber;
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
	 * Set port number of Sensor
	 * @param portNumber
	 * @throws PortInUseException
	 */
	public void setPortNumber(String portNumber) throws PortInUseException {
		if(portManagement.add(portNumber) != false) {
			portManagement.remove(this.portNumber);
			this.portNumber = portNumber;
		}
		else
			throw new PortInUseException(portNumber + " already in use");
	}
	
	//TODO - implement hash and equals
}