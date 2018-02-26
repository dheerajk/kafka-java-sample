package com.example.kafka.launchers;

import com.example.kafka.managers.BroadcasterManager;

import java.util.Scanner;

public class MultipleProducersLauncher {

    private static final String EXIT = "quit";

    public static void main(String[] commandLineArguments) {
        Scanner inputScanner = new Scanner(System.in);
        System.out.println("Enter message or type quit to exit: ");
        BroadcasterManager manager = new BroadcasterManager();
        manager.createBroadcaster("Morning", "Good morning!", 1000);
        manager.createBroadcaster("Afternoon", "Good afternoon!", 2000);
        manager.createBroadcaster("Night", "Good night!", 3000);

        String line = inputScanner.nextLine();
        while (!line.equals(EXIT)) {
            manager.broadcast();
            line = inputScanner.nextLine();
        }

        inputScanner.close();
        manager.shutdownAllBroadcasters();
    }

}
