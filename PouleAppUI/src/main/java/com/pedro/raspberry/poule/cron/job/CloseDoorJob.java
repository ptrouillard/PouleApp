package com.pedro.raspberry.poule.cron.job;

import com.pedro.raspberry.poule.door.DoorConstants;
import com.pedro.raspberry.poule.door.DoorService;
import com.pedro.raspberry.poule.remoteAddr.RemoteAddrHolder;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class CloseDoorJob implements Job {

    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    /**
     * Note : service is choosen depending on profile used.
     * "default" profile will select DoorServiceMock impl.
     * "prod" profile will select GPIODoorService which is the real service.
     */
    @Autowired
    private DoorService service;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        logger.info("CloseDoorJob called.");
        DoorService doorService = (DoorService)jobExecutionContext.getMergedJobDataMap().get("doorService");
        RemoteAddrHolder.set("cron");
        doorService.stepDown(DoorConstants.Close.getTime());
    }
}
