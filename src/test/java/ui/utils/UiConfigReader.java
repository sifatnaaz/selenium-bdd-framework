package ui.utils;

import common.config.ConfigReader;

public class UiConfigReader extends ConfigReader {
    public UiConfigReader() {
        super("uiConfig"); // loads apiConfig.<env>.properties
    }
}