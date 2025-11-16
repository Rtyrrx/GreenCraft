package com.greencraft.utils;
public class EventLogger {
    private static final Observer observer = new Observer();
    static {
        observer.subscribe(msg -> 
            System.out.println("[LOG] " + msg)
        );
    }
    public static void log(String message) {
        observer.publish(message);
    }
}
