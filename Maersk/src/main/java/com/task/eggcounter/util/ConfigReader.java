package com.task.eggcounter.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * @author swarooppattanaik
 * @project timecounter
 */
public class ConfigReader {

    private Properties prop;

    public Properties init_prop() {
        prop = new Properties();
        try {
            FileInputStream fis =
                    new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/config/config.properties");
            prop.load(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }
}
