package com.example.kafka.launchers;

import com.example.kafka.MessageBroadcaster;

import java.util.Scanner;

public class SingleProducerLauncher {

    private static final String EXIT = "quit";

    public static void main(String[] commandLineArguments) {
        Scanner inputScanner = new Scanner(System.in);
        System.out.println("Enter message or type quit to exit: ");
        MessageBroadcaster messageBroadcaster = new MessageBroadcaster();

        String line = inputScanner.nextLine();
        while (!line.equals(EXIT)) {
            messageBroadcaster.broadcast(line);
            line = inputScanner.nextLine();
        }
        inputScanner.close();
        messageBroadcaster.shutdown();
    }

}
