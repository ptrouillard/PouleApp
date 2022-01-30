package com.pedro.raspberry.poule.ui.door;

import com.pedro.raspberry.poule.ui.audit.AuditService;
import com.pedro.raspberry.poule.ui.config.ConfigService;
import com.pedro.raspberry.poule.ui.remoteAddr.RemoteAddrHolder;
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

    @Autowired
    private DoorRepository repository;

    @Autowired
    private AuditService auditService;

    @Autowired
    private ConfigService configService;

    @GetMapping("/door")
    public String door(Model model) {
        return "door";
    }

    @PostMapping("/door/stepup")
    public String stepup(Model model) {
        try {
            long elapsed = service.up(configService.getConfig().getOpenStepTime());
            model.addAttribute("timeDone", elapsed);
        } catch (Exception e) {
            model.addAttribute("error", "door.error");
        }
        return "door";
    }

    @PostMapping("/door/stepdown")
    public String stepdown(Model model) {
        try {
            long elapsed = service.down(configService.getConfig().getCloseStepTime());
            model.addAttribute("timeDone", elapsed);
        } catch (Exception e) {
            model.addAttribute("error", "door.error");
        }
        return "door";
    }

    @PostMapping("/door/open")
    public String open(Model model) {
        try {
            long elapsed = service.up(configService.getConfig().getOpenTime());
            model.addAttribute("timeDone", elapsed);
        } catch (Exception e) {
            model.addAttribute("error", "door.error");
        }
        return "door";
    }

    @PostMapping("/door/close")
    public String close(Model model) {
        try {
            long elapsed = service.down(configService.getConfig().getCloseTime());
            model.addAttribute("timeDone", elapsed);
        } catch (Exception e) {
            model.addAttribute("error", "door.error");
        }
        return "door";
    }
}
