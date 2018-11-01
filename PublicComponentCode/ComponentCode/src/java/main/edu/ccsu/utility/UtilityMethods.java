package edu.ccsu.utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
	 * If an error occurred with return a String equaling "Error"
	 * @param pythonFileName
	 * @param function
	 * @returns String
	 */
	public static String callPython(String pythonFileName, String function) {
		 String response  = "";
		 
		try {
			Process p = null;
			if(function == null || function.isEmpty()) {
				p = Runtime.getRuntime().exec(CommonConstants.PYTHON + CommonConstants.BLANK + CommonConstants.RELATIVE_PATH +  pythonFileName);
			}
			else {
				p = Runtime.getRuntime().exec(CommonConstants.PYTHON + CommonConstants.BLANK + 
												CommonConstants.RELATIVE_PATH +  pythonFileName + CommonConstants.BLANK +
												function);
			}
			BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
           
			response = input.readLine();
			input.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
           return response;
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