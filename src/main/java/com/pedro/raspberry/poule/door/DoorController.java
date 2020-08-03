package com.pedro.raspberry.poule.door;

import com.google.common.base.Stopwatch;
import com.pedro.raspberry.poule.audit.AuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
    public String stepup(Model model, HttpServletRequest request) {
        Door door = loadDoor();

        long elapsed = service.stepUp(door.getOpenStepTime(), request.getRemoteAddr());
        model.addAttribute("timeDone", elapsed);
        model.addAttribute("door", door);
        return "door";
    }

    @PostMapping("/door/stepdown")
    public String stepdown(Model model, HttpServletRequest request) {
        Door door = loadDoor();

        long elapsed = service.stepDown(door.getCloseStepTime(), request.getRemoteAddr());
        model.addAttribute("timeDone", elapsed);
        model.addAttribute("door", door);
        return "door";
    }

    @PostMapping("/door/open")
    public String open(Model model, HttpServletRequest request) {
        Door door = loadDoor();

        long elapsed = service.stepUp(door.getOpenTime(), request.getRemoteAddr());
        model.addAttribute("timeDone", elapsed);
        model.addAttribute("door", door);
        return "door";
    }

    @PostMapping("/door/close")
    public String close(Model model, HttpServletRequest request) {
        Door door = loadDoor();

        long elapsed = service.stepDown(door.getCloseTime(), request.getRemoteAddr());
        model.addAttribute("timeDone", elapsed);
        model.addAttribute("door", door);
        return "door";
    }

    @PostMapping("/door/save")
    public String save(Model model, @ModelAttribute("door") Door doorCommand) {
        auditService.audit("audit.configuration.saved");
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
