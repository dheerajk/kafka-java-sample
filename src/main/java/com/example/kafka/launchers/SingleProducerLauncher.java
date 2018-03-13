package com.example.kafka.launchers;

import com.example.kafka.MessageBroadcaster;
import com.example.kafka.models.Transaction;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.List;

public class SingleProducerLauncher {

    public static void main(String[] commandLineArguments) throws Exception {

        Type TXN_TYPE = new TypeToken<List<Transaction>>() {
        }.getType();

        MessageBroadcaster messageBroadcaster = new MessageBroadcaster();

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        File inputFile = new File(classLoader.getResource("data/input/30k-SingleUserSameData.json").getFile());
        JsonReader reader = new JsonReader(new FileReader(inputFile.getAbsolutePath()));

        Gson gson = new Gson();
        List<Transaction> data = gson.fromJson(reader, TXN_TYPE);


        int size = data.size();
        for (int index = 0; index < size; index++) {
            messageBroadcaster.broadcast("" + index, gson.toJson(data.get(index)));
        }
//        data.forEach(txn -> messageBroadcaster.broadcast(gson.toJson(txn)));

        messageBroadcaster.shutdown();
    }

}
