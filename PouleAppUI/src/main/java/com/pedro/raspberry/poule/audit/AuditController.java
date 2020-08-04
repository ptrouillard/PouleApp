package com.pedro.raspberry.poule.audit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

        IntStream pageNumbers = IntStream.range(1, page.getTotalPages()+1);
        List<com.pedro.raspberry.poule.audit.Page> pages = pageNumbers.mapToObj(p -> {
            return new com.pedro.raspberry.poule.audit.Page(p);
        }).collect(Collectors.toList());

        model.addAttribute("currentPage", page);
        model.addAttribute("pages", pages);
        model.addAttribute("currentPageNumber", page.getPageable().getPageNumber());
        return "audit";
    }

}
