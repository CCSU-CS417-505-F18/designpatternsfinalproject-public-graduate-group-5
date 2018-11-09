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
			//get output from process and return it to the caller
			BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
           
			response = input.readLine();
			input.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
			return response;			
	}
	
	/**
	 * TODO- delete this method completely unneccessary 
	 * Builds the string needed in calling python process
	 * @param portNumber
	 * @param params
	 * @return
	 */
	public static String buildArgsString(String portNumber, String params) {
		StringBuilder args = new StringBuilder();
		//NOTE - our custom Python code always will have portNumber as first entry in params string
		args.append(portNumber.substring(1));
		String[] strToAppend = params.split("\\s+");
		for(String str: strToAppend) {
			args.append(CommonConstants.BLANK);
			args.append(str);			
		}
		return args.toString();
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