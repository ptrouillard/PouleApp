package com.pedro.raspberry.poule.api.supervision;

import com.pedro.raspberry.poule.adapter.supervision.InsightResult;
import com.pedro.raspberry.poule.adapter.supervision.SupervisionAdapter;
import com.pedro.raspberry.poule.adapter.supervision.SupervisionInsight;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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

        InsightResult result = null;
        try {
            result = InsightResult.success(adapter.getInsights());
        } catch (Exception e) {
            result = InsightResult.error(e.getMessage());
        }
        return result;
    }

}
