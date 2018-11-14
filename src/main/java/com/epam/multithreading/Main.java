package com.epam.multithreading;

import com.epam.multithreading.entity.Exchange;
import com.epam.multithreading.entity.ExchangeInfo;
import com.epam.multithreading.entity.ExchangeParticipant;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {

    public static void main(String[] args) {

        ExecutorService executorService = null;
        try {
            executorService = Executors.newCachedThreadPool();
            ExchangeInfo info = new ExchangeInfo();
            Exchange exchange = new Exchange(info);
            ExchangeParticipant participant = new ExchangeParticipant(exchange);
            executorService.submit(participant);

        } finally {
            if (executorService != null) {
                executorService.shutdown();
            }
        }


    }
}
