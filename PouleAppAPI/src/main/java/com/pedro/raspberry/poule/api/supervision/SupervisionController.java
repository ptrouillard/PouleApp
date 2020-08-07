package com.pedro.raspberry.poule.api.supervision;

import com.pedro.raspberry.poule.adapter.supervision.InsightResult;
import com.pedro.raspberry.poule.adapter.supervision.SupervisionAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicReference;

@RestController
public class SupervisionController {

    private static Logger logger = LoggerFactory.getLogger(SupervisionController.class.getName());

    @Autowired
    public SupervisionAdapter adapter;

    /**
     * calling /insight will collect system insights
     * @return
     */
    @GetMapping("/supervision/insights")
    public InsightResult insights()
    {
        logger.info("supervison insights invoked");
        AtomicReference<InsightResult> result = new AtomicReference<>();
        adapter.getInsights().ifPresent(insight -> {
            result.set(InsightResult.success(insight));
        });

        // change this when JDK 9 will be supported on RASPI
        if (!adapter.getInsights().isPresent()) {
            result.set(InsightResult.error("No data fetched from API"));
        }
        return result.get();
    }

}
