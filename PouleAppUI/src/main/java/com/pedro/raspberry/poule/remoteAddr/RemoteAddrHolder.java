package com.pedro.raspberry.poule.remoteAddr;

import org.springframework.stereotype.Component;

public class RemoteAddrHolder {
    private static ThreadLocal<String> remoteAdressHolder = new ThreadLocal<>();

    public static String get() {
        return remoteAdressHolder.get();
    }

    public static void set(String remoteAddr) {
        remoteAdressHolder.set(remoteAddr);
    }
}
