package com.selenium.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.Properties;

public class Config {
    public static final Logger log= LoggerFactory.getLogger(Config.class);
    public static final String DEFAULT_PROPERTIES_FILE_ROOT="config/configuration.properties";
    public static Properties properties;
    //Method that load default properties file
    public static void initializeProperties(){
    //load default properties
        properties=readProperties();
    //check for overrides (properties)
        for(String key: properties.stringPropertyNames()){
            if(System.getProperties().containsKey(key)){
                properties.setProperty(key,System.getProperty(key));
            }
        }

    // print on console log
        log.info("***************************************");
        for(String key: properties.stringPropertyNames()){
            log.info("Propiedad: {} = Valor: {}",key,properties.getProperty(key));
        }

    }
    //Method that read properties from configuration file
    private static Properties readProperties(){
    Properties properties=new Properties();
        try(InputStream inputStream= ResourceLoader.getResources(DEFAULT_PROPERTIES_FILE_ROOT)) {
           properties.load(inputStream);

        } catch (Exception e) {
            log.error("Se produjo una excepcion al leer las propiedades, {}",e.getMessage());
            throw new RuntimeException(e);

        }
        return properties;
    }
    //Method that returns a specific key value configured previously
    public static String getConfigurationProperty(String key){
        return properties.getProperty(key);
    }

}
