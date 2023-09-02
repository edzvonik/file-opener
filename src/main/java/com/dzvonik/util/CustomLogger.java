package com.dzvonik.util;

import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class CustomLogger {

    private static final Logger LOGGER = Logger.getLogger(CustomLogger.class.getName());

    private static CustomLogger instance;

    private CustomLogger() {
        try {
            FileHandler fileHandler = new FileHandler("main.log");
            SimpleFormatter formatter = new SimpleFormatter();
            fileHandler.setFormatter(formatter);
            LOGGER.addHandler(fileHandler);
        } catch (Exception e) {
            throw new RuntimeException("Error configure logger", e);
        }
    }

    public static synchronized CustomLogger getInstance() {
        if (instance == null) {
            instance = new CustomLogger();
        }
        return instance;
    }

    public void logError(String message) {
        LOGGER.severe(message);
    }

}
