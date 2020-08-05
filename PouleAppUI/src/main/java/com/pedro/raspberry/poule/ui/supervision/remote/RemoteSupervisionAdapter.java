package com.pedro.raspberry.poule.ui.supervision.remote;

import com.pedro.raspberry.poule.adapter.supervision.SupervisionAdapter;
import com.pedro.raspberry.poule.adapter.supervision.SupervisionInsight;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Component
@Profile("prod")
public class RemoteSupervisionAdapter implements SupervisionAdapter {

    @Override
    public String getInsight(SupervisionInsight key) throws IOException, InterruptedException {

        // TODO
        return null;
    }
}
