package com.pedro.raspberry.poule.adapter.supervision;

import java.io.IOException;

public interface SupervisionAdapter {
    Insight getInsights() throws IOException, InterruptedException;
}
