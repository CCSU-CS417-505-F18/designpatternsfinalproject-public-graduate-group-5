package edu.ccsu.utility;

import org.python.core.PyObject;
import org.python.util.PythonInterpreter;

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
	public static PyObject callPython(String pythonFileName, String function, PyObject[] args) {
		try{
			 // Create an instance of the PythonInterpreter
	        PythonInterpreter interpreter = new PythonInterpreter();
	        //append pythonFileName to end of relative path
	        interpreter.execfile(CommonConstants.RELATIVE_PATH + pythonFileName);
	        PyObject fnct = interpreter.get(function);
	        PyObject result = null;
	        if(args == null) {
	        	 result = fnct.__call__();
	        }
	        else {
	        	 result = fnct.__call__(args);
	        }
	        String realResult = "";
	        if(result != null) {
	        	realResult = (String) result.__tojava__(String.class);
	        }
	        System.out.println(realResult);
	        interpreter.cleanup();
	        return result;
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return null;
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