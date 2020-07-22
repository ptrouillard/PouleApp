package com.pedro.raspberry.poule.door;

import com.google.common.base.Stopwatch;
import com.pedro.raspberry.poule.audit.AuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.concurrent.TimeUnit;

@Controller
public class DoorController {

    /**
     * Note : service is choosen depending on profile used.
     * "default" profile will select DoorServiceMock impl.
     * "prod" profile will select GPIODoorService which is the real service.
     */
    @Autowired
    private DoorService service;

    @Autowired
    private DoorRepository repository;

    @Autowired
    private AuditService auditService;

    @GetMapping("/door")
    public String door(Model model) {
        Door door = loadDoor();
        model.addAttribute("door", door);
        return "door";
    }

    @PostMapping("/door/stepup")
    public String stepup(Model model) {
        auditService.audit("Door moved upwards");
        Door door = loadDoor();

        Stopwatch stopwatch = Stopwatch.createStarted();
        service.stepUp(door.getOpenStepTime());
        stopwatch.stop();
        model.addAttribute("timeDone", stopwatch.elapsed(TimeUnit.MILLISECONDS));
        model.addAttribute("door", door);
        return "door";
    }

    @PostMapping("/door/stepdown")
    public String stepdown(Model model) {
        auditService.audit("Door moved downwards");
        Door door = loadDoor();

        Stopwatch stopwatch = Stopwatch.createStarted();
        service.stepDown(door.getCloseStepTime());
        stopwatch.stop();
        model.addAttribute("timeDone", stopwatch.elapsed(TimeUnit.MILLISECONDS));
        model.addAttribute("door", door);
        return "door";
    }

    @PostMapping("/door/open")
    public String open(Model model) {
        auditService.audit("Door is opened");
        Door door = loadDoor();

        Stopwatch stopwatch = Stopwatch.createStarted();
        service.stepUp(door.getOpenTime());
        stopwatch.stop();
        model.addAttribute("timeDone", stopwatch.elapsed(TimeUnit.MILLISECONDS));
        model.addAttribute("door", door);
        return "door";
    }

    @PostMapping("/door/close")
    public String close(Model model) {
        auditService.audit("Door is closed");
        Door door = loadDoor();

        Stopwatch stopwatch = Stopwatch.createStarted();
        service.stepDown(door.getCloseTime());
        stopwatch.stop();
        model.addAttribute("timeDone", stopwatch.elapsed(TimeUnit.MILLISECONDS));
        model.addAttribute("door", door);
        return "door";
    }

    @PostMapping("/door/save")
    public String save(Model model, @ModelAttribute("door") Door doorCommand) {
        auditService.audit("Door configuration is saved");
        repository.save(doorCommand);
        model.addAttribute("door", loadDoor());
        model.addAttribute("doorSaved", true);
        return "door";
    }

    private Door loadDoor() {

        // get config from database if exists
        Door doorConfig = repository.findById(1);
        if (doorConfig == null) {
            doorConfig = new Door(1, DoorConstants.Close.getTime(), DoorConstants.Open.getTime(), DoorConstants.Step.getTime(), DoorConstants.Step.getTime());
            repository.save(doorConfig);
            doorConfig = repository.findById(1);
        }
        return doorConfig;
    }
}
