package com.greencraft.utils;

public class EventLogger {
    static {
        Observer.getInstance().subscribe(message -> System.out.println("[LOG] " + message));
    }

    public static void log(String message) {
        Observer.getInstance().publish(message);
    }
}
