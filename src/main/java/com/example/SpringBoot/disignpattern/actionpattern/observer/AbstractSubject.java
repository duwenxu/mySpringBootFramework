package com.example.springboot.disignpattern.actionpattern.observer;

import com.example.springboot.disignpattern.actionpattern.observer.switchenum.SwitchState;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractSubject {

     Set<AbstractObserver> observers = new HashSet<>();

    /**
     * 绑定观察者
     * @param observer
     */
    public void attach(AbstractObserver observer){
        observers.add(observer);
    }

    /**
     * 解绑观察者
     * @param observer
     */
    public void detach(AbstractObserver observer){
        observers.remove(observer);
    }

    /**
     * 发生变化时通知 观察者
     */
    public void notifyObserver(SwitchState state){
        for (AbstractObserver observer : observers) {
            observer.updateState(state);
        }
    }



}
