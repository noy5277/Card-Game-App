package com.example.cardgameapp;

public interface IObservable {
    void Add(IObserver o);
    void Remove(IObserver o);
    void Notify();
}
