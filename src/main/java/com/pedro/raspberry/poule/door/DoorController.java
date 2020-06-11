package com.pedro.raspberry.poule.door;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DoorController {

    /**
     * Note : service is choosen depending on profile used.
     * "default" profile will select DoorServiceMock impl.
     * "prod" profile will select GPIODoorService which is the real service.
     */
    @Autowired
    private DoorService service;

    @GetMapping("/door")
    public String door(Model model) {
        DoorCommand doorCommand = prepareDoorCommand(0L);
        model.addAttribute("door", doorCommand);
        return "door";
    }

    @PostMapping("/door/stepup")
    public String stepup(Model model, @ModelAttribute("door") DoorCommand doorCommand) {
        service.stepUp(doorCommand.getTime());
        model.addAttribute("timeDone", doorCommand.getTime());
        model.addAttribute("door", doorCommand);
        return "door";
    }

    @PostMapping("/door/stepdown")
    public String stepdown(Model model, @ModelAttribute("door") DoorCommand doorCommand) {
        service.stepDown(doorCommand.getTime());
        model.addAttribute("timeDone", doorCommand.getTime());
        model.addAttribute("door", doorCommand);
        return "door";
    }

    @PostMapping("/door/open")
    public String open(Model model, @ModelAttribute("door") DoorCommand doorCommand) {
        service.stepUp(doorCommand.getTime2());
        model.addAttribute("timeDone", doorCommand.getTime2());
        model.addAttribute("door", doorCommand);
        return "door";
    }

    @PostMapping("/door/close")
    public String close(Model model, @ModelAttribute("door") DoorCommand doorCommand) {
        service.stepDown(doorCommand.getTime3());
        model.addAttribute("timeDone", doorCommand.getTime3());
        model.addAttribute("door", doorCommand);
        return "door";
    }

    private DoorCommand prepareDoorCommand(long timeDone) {
        DoorCommand doorCommand = new DoorCommand();
        doorCommand.setTime(DoorConstants.Step.getTime());
        doorCommand.setTime2(DoorConstants.Open.getTime());
        doorCommand.setTime3(DoorConstants.Close.getTime());
        return doorCommand;
    }
}
