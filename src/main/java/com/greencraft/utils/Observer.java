package com.greencraft.utils;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
public class Observer {
    private static Observer instance = new Observer();
    private final List<Consumer<String>> listeners = new ArrayList<>();
    private Observer() {
        
    }
    public static Observer getInstance() { 
        return instance; 
    }
    public void subscribe(Consumer<String> listener) { 
        listeners.add(listener); 
    }
    public void publish(String event) { 
        for(Consumer<String> l:listeners) {
            l.accept(event); 
        }
    }
}
