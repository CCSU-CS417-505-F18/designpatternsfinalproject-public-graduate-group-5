package edu.ccsu.interfaces;

import java.util.List;

/**
 * Iterator used by classes that realize sensor interface.
 * Used to iterate through the data these classes hold
 * @author Adrian
 *
 */
public interface Iterator {
	/***
	 * Checks to see if next element in collection exists 
	 * @return
	 */
	public boolean hasNext();
	/**
	 * Returns next element in collection
	 * @return
	 */
	public Object next();
	
	/**
	 * Given a filter string will return just that data
	 * @param filter
	 * @return
	 */
	public List<String> filter(String filter);
}