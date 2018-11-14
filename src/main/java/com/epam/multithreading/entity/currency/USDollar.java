package com.epam.multithreading.entity.currency;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class USDollar extends Currency {

    private static USDollar instance;
    private static AtomicBoolean initialized = new AtomicBoolean(false);
    private static Lock lock = new ReentrantLock();

    private USDollar() {
    }

    public static USDollar getInstance() {
        if (!initialized.get()) {

            try {
                lock.lock();
                if (!initialized.get()) {
                    final USDollar local = new USDollar();
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
