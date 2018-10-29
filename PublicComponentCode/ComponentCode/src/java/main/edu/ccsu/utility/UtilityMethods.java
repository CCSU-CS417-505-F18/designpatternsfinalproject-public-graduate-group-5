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
	 * @param pythonFileName
	 * @param function
	 * @param args
	 */
	public static void callPython(String pythonFileName, String function, PyObject[] args) {
		try{
			 // Create an instance of the PythonInterpreter
	        PythonInterpreter interpreter = new PythonInterpreter();
	        //append pythonFileName to end of relative path
	        interpreter.execfile(CommonConstants.RELATIVE_PATH + pythonFileName);
	        PyObject helloFunction = interpreter.get(function);
	        PyObject result = helloFunction.__call__(args);			
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
}
