package com.epam.multithreading;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Singleton {
    private static Singleton instance;
    private static AtomicBoolean initialized;

    private static Lock lock = new ReentrantLock();

    public static Singleton getInstance() {
        if (!initialized.get()) {

            try {
                lock.lock();
                if (!initialized.get()) {
                    final Singleton local = new Singleton();
                    instance = local;
                    instance.init ();
                    initialized.set(true);
                }
            } finally {
                lock.unlock();
            }

        }
        return instance;
    }

    private void init() {
        //initializing
    }

}
