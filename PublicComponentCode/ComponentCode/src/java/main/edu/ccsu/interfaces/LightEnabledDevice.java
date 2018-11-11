package edu.ccsu.interfaces;

/**
 * Interface to be implemented by GrovePi devices that
 * have lights
 * @author Adrian
 *
 */
public interface LightEnabledDevice extends Device{
	/**
	 * Given a brightness level between 0-1023 will adjust brightness of 
	 * light enabled devices
	 * @param brightness
	 * @return
	 */
	public void adjustBrightness(int brightness);
	
	/**
	 * Will blink for specified number of blinks
	 * @param numberOfBlinks
	 */
	public void blink(int numberOfBlinks);
}