package com.example.springboot.disignpattern.actionpattern.observer.observes;

import com.example.springboot.disignpattern.actionpattern.observer.AbstractObserver;
import com.example.springboot.disignpattern.actionpattern.observer.switchenum.SwitchState;

/**
 * 具体观察者  
 * @author duwenxu
 * @create 2020-07-02 9:39
 */
public class Observer2 extends AbstractObserver {

    private SwitchState observerState;

    public Observer2() {
    }

    public SwitchState getObserverState() {
        return observerState;
    }

    @Override
    public void updateState(SwitchState state) {
        observerState = state;
        System.out.println(this.getClass().getSimpleName()+"状态改变,newState = "+state);
    }
}