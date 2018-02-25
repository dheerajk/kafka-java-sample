package com.example.kafka;

import java.util.Scanner;

public class SingleConsumerLauncher {

    private static final String EXIT = "quit";

    public static void main(String[] commandLineArguments) throws Exception {
        Scanner inputScanner = new Scanner(System.in);
        System.out.println("Enter message or type quit to exit: ");
        MessageConsumer messageConsumer = new MessageConsumer();
        new Thread(messageConsumer).start();

        String line = inputScanner.nextLine();
        while (!line.equals(EXIT)) {
            line = inputScanner.nextLine();
        }
        inputScanner.close();
        messageConsumer.getKafkaConsumer().wakeup();
        System.out.println("Stopping consumer .....");
        messageConsumer.join();
    }

}
