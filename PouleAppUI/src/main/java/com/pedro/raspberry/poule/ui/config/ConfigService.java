package com.pedro.raspberry.poule.ui.config;

import com.pedro.raspberry.poule.ui.audit.AuditService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ConfigService {

    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    public final static String WEBCAM_URL = "webcam.url";
    public final static String API_SUPERVISION_URL = "api.supervision.url";
    public final static String API_DOOR_URL = "api.door.url";
    public final static String DOOR_CLOSE_STEP_TIME = "door.close.step.time";
    public final static String DOOR_CLOSE_TIME = "door.close.time";
    public final static String DOOR_OPEN_STEP_TIME = "door.open.step.time";
    public final static String DOOR_OPEN_TIME = "door.open.time";
    public final static String DOOR_OPEN_HOUR = "door.open.hour";
    public final static String DOOR_OPEN_MINUTES = "door.open.minutes";
    public final static String DOOR_CLOSE_HOUR = "door.close.hour";
    public final static String DOOR_CLOSE_MINUTES = "door.close.minutes";


    @Autowired
    private ConfigRepository repository;

    @Autowired
    private AuditService auditService;

    public Config getConfig() {
        Config config = new Config();
        config.setWebcamUrl(repository.get(WEBCAM_URL));
        config.setApiSupervisionUrl(repository.get(API_SUPERVISION_URL));
        config.setApiDoorUrl(repository.get(API_DOOR_URL));
        config.setCloseStepTime(Long.parseLong(repository.get(DOOR_CLOSE_STEP_TIME)));
        config.setCloseTime(Long.parseLong(repository.get(DOOR_CLOSE_TIME)));
        config.setOpenStepTime(Long.parseLong(repository.get(DOOR_OPEN_STEP_TIME)));
        config.setOpenTime(Long.parseLong(repository.get(DOOR_OPEN_TIME)));
        config.setOpenHour(repository.get(DOOR_OPEN_HOUR));
        config.setOpenMinutes(repository.get(DOOR_OPEN_MINUTES));
        config.setCloseHour(repository.get(DOOR_CLOSE_HOUR));
        config.setCloseMinutes(repository.get(DOOR_CLOSE_MINUTES));

        return config;
    }

    public void save(Config config) throws IOException {

        saveKeyIfChanged(WEBCAM_URL, () -> { return config.getWebcamUrl();});
        saveKeyIfChanged(API_SUPERVISION_URL, () -> { return config.getApiSupervisionUrl();});
        saveKeyIfChanged(API_DOOR_URL, () -> { return config.getApiDoorUrl();});
        saveKeyIfChanged(DOOR_CLOSE_STEP_TIME, () -> { return Long.toString(config.getCloseStepTime());});
        saveKeyIfChanged(DOOR_CLOSE_TIME, () -> { return Long.toString(config.getCloseTime());});
        saveKeyIfChanged(DOOR_OPEN_STEP_TIME, () -> { return Long.toString(config.getOpenStepTime());});
        saveKeyIfChanged(DOOR_OPEN_TIME, () -> { return Long.toString(config.getOpenTime());});

        repository.save();
    }

    private void saveKeyIfChanged(String key, Getter getter) {
        String existingValue = repository.get(key);
        String newValue = getter.getValue();
        if (!existingValue.equals(newValue)) {
            repository.set(key, newValue);
            auditService.audit("audit.config.url.modified", existingValue, newValue);
        }
    }

    interface Getter {
        String getValue();
    }
}
