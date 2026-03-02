package api.utils;

import common.config.ConfigReader;

public class ApiConfigReader extends ConfigReader {
    public ApiConfigReader(String basePath) {
    	
    	
        super(basePath); // loads apiConfig.<env>.properties
    }
}