package com.pedro.raspberry.poule.config;

import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.io.*;
import java.util.Date;
import java.util.Properties;

@Repository
public class ConfigRepository {

    private Properties properties;

    private File file;

    @PostConstruct
    public void init() throws IOException {

        properties = new Properties();
        String userHome = System.getProperty("user.home");
        file = new File(userHome+"/poule.conf");
        if (file.exists()) {
            properties.loadFromXML( new FileInputStream(file.getPath()));
        }
    }

    public String get(String key) {
        return properties.getProperty(key);
    }

    public void set(String key, String value) {
        properties.setProperty(key, value);
    }

    public void save() throws IOException {
        properties.storeToXML( new FileOutputStream(file.getPath()), "Saved on " + new Date());
    }
}

