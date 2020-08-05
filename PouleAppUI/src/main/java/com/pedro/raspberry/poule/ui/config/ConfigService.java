package com.pedro.raspberry.poule.ui.config;

import com.pedro.raspberry.poule.ui.audit.AuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ConfigService {

    public final static String WEBCAM_URL = "webcam.url";

    @Autowired
    private ConfigRepository repository;

    @Autowired
    private AuditService auditService;

    public String getWebcamUrl() {
        return repository.get(WEBCAM_URL);
    }

    public void save(String webcamUrl) throws IOException {

        String current = repository.get(WEBCAM_URL);
        auditService.audit("audit.config.url.modified", current, webcamUrl);

        repository.set(WEBCAM_URL, webcamUrl);
        repository.save();
    }
}
