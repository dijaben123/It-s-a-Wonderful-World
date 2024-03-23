package iww;

import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Utility class for configuring logging settings.
 */
public class ConfigLog {
    
	/**
	 * Constructs a new instance of the ConfigLog class.
	 * This class seems to be related to configuring logging settings.
	 * The constructor initializes logging configurations, including setting up file handlers.
	 * If an exception occurs during file handler setup, a stack trace is printed.
	 */
	public ConfigLog(){}

    /**
     * Configures the logging setup for regular game sessions, directing logs to a file named "logsParties.txt".
     */
    public static void setup() {
    	Logger copieLOGGER = Logger.getLogger(""); 
    	FileHandler fileHandler;
		try {
			fileHandler = new FileHandler( "logsParties.txt" );
	    	fileHandler.setFormatter( new SimpleFormatter() );
	    	copieLOGGER.addHandler( fileHandler );
		} catch (SecurityException | IOException e) {
			e.printStackTrace();
		}
		if (copieLOGGER.getHandlers()[0] instanceof ConsoleHandler) {
		    copieLOGGER.removeHandler(copieLOGGER.getHandlers()[0]); 
		}
    	
    }

	/**
    * Configures the logging setup for test scenarios, directing logs to a file named "logsPartiesTest.txt".
    */
	public static void setupTest() {
    	Logger copieLOGGER = Logger.getLogger(""); 
    	FileHandler fileHandler;
		try {
			fileHandler = new FileHandler( "logsPartiesTest.txt" );
	    	fileHandler.setFormatter( new SimpleFormatter() );
	    	copieLOGGER.addHandler( fileHandler );
		} catch (SecurityException | IOException e) {
			e.printStackTrace();
		}
		if (copieLOGGER.getHandlers()[0] instanceof ConsoleHandler) {
		    copieLOGGER.removeHandler(copieLOGGER.getHandlers()[0]); 
		}
    	
    }

}