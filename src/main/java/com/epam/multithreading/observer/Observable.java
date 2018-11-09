package com.epam.multithreading.observer;

public interface Observable<T> {

    void notifyObservers();

    void attach(T observer);
}
