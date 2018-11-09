package com.epam.multithreading.entity;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Euro extends Currency{

    private static Euro instance;
    private static AtomicBoolean initialized = new AtomicBoolean(false);
    private static Lock lock = new ReentrantLock();

    private Euro() {
    }

    public static Euro getInstance() {
        if (!initialized.get()) {

            try {
                lock.lock();
                if (!initialized.get()) {
                    final Euro local = new Euro();
                    instance = local;
                    initialized.set(true);
                }
            } finally {
                lock.unlock();
            }

        }
        return instance;
    }

}
