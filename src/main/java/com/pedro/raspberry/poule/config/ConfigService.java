package com.pedro.raspberry.poule.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ConfigService {

    public final static String WEBCAM_URL = "webcam.url";

    @Autowired
    private ConfigRepository repository;

    public String getWebcamUrl() {
        return repository.get(WEBCAM_URL);
    }

    public void save(String webcamUrl) throws IOException {
        repository.set(WEBCAM_URL, webcamUrl);
        repository.save();
    }
}
