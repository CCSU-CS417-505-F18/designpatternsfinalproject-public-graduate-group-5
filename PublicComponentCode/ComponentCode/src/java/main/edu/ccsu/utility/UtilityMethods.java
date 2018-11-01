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
	 * Returns a PyObject. If it's null the script failed or if the script throws an error
	 * you can check what went wrong in PyObject
	 * @param pythonFileName
	 * @param function
	 * @returns String
	 */
	public static String callPython(String pythonFileName, String function) {
		 String response  = "";
		 
		try {
			System.out.println(CommonConstants.PYTHON + CommonConstants.BLANK +  pythonFileName);
			Process p = Runtime.getRuntime().exec(CommonConstants.PYTHON + CommonConstants.BLANK + CommonConstants.RELATIVE_PATH +  pythonFileName);
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