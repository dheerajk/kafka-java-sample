package com.example.kafka.launchers;

import com.example.kafka.MessageBroadcaster;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MultipleProducersLauncher {

    private static final String EXIT = "quit";
    private final List<MessageBroadcaster> broadcasters;

    public MultipleProducersLauncher() {
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

    public static void main(String[] commandLineArguments) {
        Scanner inputScanner = new Scanner(System.in);
        System.out.println("Enter message or type quit to exit: ");
        MessageBroadcaster morningBroadcaster = new MessageBroadcaster("Good morning!");
        MessageBroadcaster afternoonBroadcaster = new MessageBroadcaster("Good afternoon!");
        MessageBroadcaster nightBroadcaster = new MessageBroadcaster("Good night!");
        MultipleProducersLauncher multipleProducersLauncher = new MultipleProducersLauncher();
        multipleProducersLauncher.addBroadCaster(morningBroadcaster);
        multipleProducersLauncher.addBroadCaster(afternoonBroadcaster);
        multipleProducersLauncher.addBroadCaster(nightBroadcaster);

        String line = inputScanner.nextLine();
        while (!line.equals(EXIT)) {
            multipleProducersLauncher.broadcast();
            line = inputScanner.nextLine();
        }

        inputScanner.close();
        multipleProducersLauncher.shutdownAllBroadcasters();
    }

}
