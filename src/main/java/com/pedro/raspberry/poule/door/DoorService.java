package com.pedro.raspberry.poule.door;

import com.google.common.base.Stopwatch;
import com.pedro.raspberry.poule.audit.AuditService;
import com.pedro.raspberry.poule.door.local.LocalDoorAdapter;
import com.pedro.raspberry.poule.remoteAddr.RemoteAddrHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service("doorService")
public class DoorService {

    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    private AuditService auditService;

    @Autowired
    private DoorAdapter doorAdapter;

    public long stepUp(long ms) {
        auditService.audit("audit.door.opening.invoked", RemoteAddrHolder.get());
        Stopwatch stopwatch = Stopwatch.createStarted();
        try {
            doorAdapter.stepUp(ms);
        } finally {
            stopwatch.stop();
        }
        long elapsed = stopwatch.elapsed(TimeUnit.MILLISECONDS);
        auditService.audit("audit.door.opening.finished", elapsed, RemoteAddrHolder.get());
        return elapsed;
    }

    public long stepDown(long ms) {
        auditService.audit("audit.door.closing.invoked", RemoteAddrHolder.get());
        Stopwatch stopwatch = Stopwatch.createStarted();
        try {
            doorAdapter.stepDown(ms);
        } finally {
            stopwatch.stop();
        }
        long elapsed = stopwatch.elapsed(TimeUnit.MILLISECONDS);
        auditService.audit("audit.door.closing.finished", elapsed, RemoteAddrHolder.get());
        return elapsed;
    }
}
