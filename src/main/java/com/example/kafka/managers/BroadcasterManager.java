package com.example.kafka.managers;

import com.example.kafka.MessageBroadcaster;

import java.util.ArrayList;
import java.util.List;

public class BroadcasterManager {

    private final List<MessageBroadcaster> broadcasters;

    public BroadcasterManager() {
        this.broadcasters = new ArrayList<MessageBroadcaster>();
    }

    public void addBroadCaster(MessageBroadcaster broadcaster) {
        broadcasters.add(broadcaster);
    }

    public void shutdownAllBroadcasters() {
        broadcasters.forEach(MessageBroadcaster::shutdown);
        broadcasters.clear();
    }

    public void broadcast() {
        broadcasters.forEach(MessageBroadcaster::broadcast);
    }

    public void createBroadcaster(String name, String message, int timeInterval) {
        MessageBroadcaster broadcaster = new MessageBroadcaster(name, message, timeInterval);
        addBroadCaster(broadcaster);
    }

}
