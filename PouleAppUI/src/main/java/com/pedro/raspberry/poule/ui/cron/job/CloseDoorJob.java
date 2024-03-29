package com.pedro.raspberry.poule.ui.cron.job;

import com.pedro.raspberry.poule.ui.door.DoorService;
import com.pedro.raspberry.poule.ui.remoteAddr.RemoteAddrHolder;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CloseDoorJob implements Job {

    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        logger.info("CloseDoorJob called.");
        RemoteAddrHolder.set("cron");
        DoorService doorService = (DoorService)jobExecutionContext.getJobDetail().getJobDataMap().get("doorService");
        doorService.down();
    }
}
