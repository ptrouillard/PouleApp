package com.pedro.raspberry.poule.supervision;

import java.io.IOException;

public interface SupervisionAdapter {
    String getInsight(SupervisionInsight key) throws IOException, InterruptedException;
}
