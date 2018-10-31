package edu.ccsu.utility;

/**
 * Class contains common methods used throughout project
 * @author Adrian
 * @author Kim
 * @author Ga Young
 */
public class UtilityMethods {

	private UtilityMethods() {
		//prevent instantiation
	}
	
	/**
	 * Give method a pythonFileName stored in pythonScripts.  Will run the script.  
	 * Returns a PyObject. If it's null the script failed or if the script throws an error
	 * you can check what went wrong in PyObject
	 * @param pythonFileName
	 * @param function
	 * @param args
	 * @returns PyObject
	 */
	public static void callPython(String pythonFileName, String function) {
		
	}
	
	
	/**
	 * Returns true if you run code on raspberry pi
	 * @return
	 */
	public static boolean checkOperatingSystem() {
		String os = System.getProperty("os.name").trim().toLowerCase();
		if(!os.contains(CommonConstants.LINUX)){
			return false;
		}
		return true;
	}
}