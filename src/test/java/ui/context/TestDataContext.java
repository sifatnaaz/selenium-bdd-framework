package ui.context;

import java.util.HashMap;
import java.util.Map;

public class TestDataContext {
    private  String dataFile;
    private  String sheetName;
    private Map<String, Object> runtimeData; // Map to store runtime data

    
    public TestDataContext() {
    	runtimeData = new HashMap<>();
    }
    
    public String getDataFile() {
        return dataFile;
    }

    public void setDataFile(String dataFile) {
        this.dataFile = dataFile;
    }

    public String getSheetName() {
        return sheetName;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }
    
    //methods to store/retrieve runtime data
    public void setData(String key, Object value) {
    	runtimeData.put(key, value);
    }
    
    public Object getData(String key) {
		return runtimeData.get(key);
    	
    }
    public void clearData() {
    	runtimeData.clear();
    }
}