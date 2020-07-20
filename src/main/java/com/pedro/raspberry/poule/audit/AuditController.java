package com.pedro.raspberry.poule.audit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Iterator;

/**
 * Created by pierre on 05/07/2020.
 */
@Controller
public class AuditController {

    @Autowired
    private AuditService auditService;

    @GetMapping("/audit")
    public String audit(Model model, @RequestParam Integer pageNumber) {

        Page<Audit> page = auditService.getPage(pageNumber);
        model.addAttribute("audits", page);
        return "audit";
    }

}
