package com.tarashor.siteparser.utils;

import com.tarashor.siteparser.models.DBConfig;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Taras on 03.02.2017.
 */
public class PropertiesUtil {
    public static DBConfig getConfig(String fileName) {
        DBConfig config = new DBConfig();
        Properties prop = new Properties();
        InputStream input = null;

        try {
            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            input = classloader.getResourceAsStream(fileName);

            // load a properties file
            prop.load(input);

            // get the property value and print it out
            config.setServer(prop.getProperty("dbserver"));
            config.setUser(prop.getProperty("dbuser"));
            config.setPassword(prop.getProperty("dbpassword"));

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return config;
    }
}

