package com.pedro.raspberry.poule.ui.config;

import com.pedro.raspberry.poule.ui.audit.AuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ConfigService {

    public final static String WEBCAM_URL = "webcam.url";
    public final static String API_SUPERVISION_URL = "api.supervision.url";
    public final static String API_DOOR_URL = "api.door.url";

    @Autowired
    private ConfigRepository repository;

    @Autowired
    private AuditService auditService;

    public String getWebcamUrl() {
        return repository.get(WEBCAM_URL);
    }

    public String getSupervisionUrl() { return repository.get(API_SUPERVISION_URL); }

    public String getDoorUrl() { return repository.get(API_DOOR_URL); }

    public void save(Config config) throws IOException {

        String current = repository.get(WEBCAM_URL);
        auditService.audit("audit.config.url.modified", current, config.getWebcamUrl());
        current = repository.get(API_SUPERVISION_URL);
        auditService.audit("audit.config.url.modified", current, config.getApiSupervisionUrl());
        current = repository.get(API_DOOR_URL);
        auditService.audit("audit.config.url.modified", current, config.getApiDoorUrl());

        repository.set(WEBCAM_URL, config.getWebcamUrl());
        repository.set(API_SUPERVISION_URL, config.getApiSupervisionUrl());
        repository.set(API_DOOR_URL, config.getApiDoorUrl());
        repository.save();
    }
}
