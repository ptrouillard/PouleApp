package com.pedro.raspberry.poule.ui.door;

import com.google.common.base.Stopwatch;
import com.pedro.raspberry.poule.adapter.door.DoorAdapter;
import com.pedro.raspberry.poule.ui.audit.AuditService;
import com.pedro.raspberry.poule.ui.config.ConfigService;
import com.pedro.raspberry.poule.ui.remoteAddr.RemoteAddrHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.ConnectException;
import java.util.concurrent.TimeUnit;

@Service("doorService")
public class DoorService {

    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    private AuditService auditService;

    @Autowired
    private DoorAdapter doorAdapter;

    @Autowired
    private DoorRepository doorRepository;

    @Autowired
    private ConfigService configService;

    /**
     * Open the door
     * @return
     */
    public long up() {
        return up(configService.getConfig().getOpenTime());
    }

    /**
     * Move the door upwards during few seconds
     * @param ms
     * @return
     */
    public long up(long ms) {
        auditService.audit("audit.door.opening.invoked", RemoteAddrHolder.get());
        Stopwatch stopwatch = Stopwatch.createStarted();
        try {
            doorAdapter.stepUp(ms);
        } catch (Exception e) {
            throw e;
        } finally {
            stopwatch.stop();
        }
        long elapsed = stopwatch.elapsed(TimeUnit.MILLISECONDS);
        auditService.audit("audit.door.opening.finished", elapsed, RemoteAddrHolder.get());
        return elapsed;
    }

    /**
     * close the door
     * @return
     */
    public long down() {
        return down(configService.getConfig().getCloseTime());
    }

    /**
     * Move the door downwards during few seconds
     * @param ms
     * @return
     */
    public long down(long ms) {
        auditService.audit("audit.door.closing.invoked", RemoteAddrHolder.get());
        Stopwatch stopwatch = Stopwatch.createStarted();
        try {
            doorAdapter.stepDown(ms);
        } catch (Exception e) {
            throw e;
        } finally {
            stopwatch.stop();
        }
        long elapsed = stopwatch.elapsed(TimeUnit.MILLISECONDS);
        auditService.audit("audit.door.closing.finished", elapsed, RemoteAddrHolder.get());
        return elapsed;
    }

}
