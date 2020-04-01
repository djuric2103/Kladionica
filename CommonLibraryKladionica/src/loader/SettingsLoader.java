/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loader;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author not-sure
 */
public class SettingsLoader {
    private static SettingsLoader instance;
    private Properties props;

    private SettingsLoader() {
        try {
            props = new Properties();
            props.load(new FileInputStream("settings.properties"));
        } catch (Exception ex) {
            Logger.getLogger(SettingsLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static SettingsLoader getInstance() {
        if (instance == null) {
            instance = new SettingsLoader();
        }
        return instance;
    }

    public String getProperty(String key) {
        return props.getProperty(key, "n/a");
    }
}