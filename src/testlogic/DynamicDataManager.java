package testlogic;

import java.util.HashMap;

public class DynamicDataManager {

	// Global 
	public static HashMap<String, String> miscellenousData;

	public static void initializeAllDynamicData(){

		initializeMiscellenousData();
	}

	public static void initializeMiscellenousData(){
		miscellenousData = new HashMap<String, String>();
	}


	
}
