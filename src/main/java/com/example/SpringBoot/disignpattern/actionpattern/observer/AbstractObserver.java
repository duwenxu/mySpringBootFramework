package com.example.springboot.disignpattern.actionpattern.observer;

import com.example.springboot.disignpattern.actionpattern.observer.switchenum.SwitchState;

/**
 * @author duwenxu
 * @create 2020-07-01 16:41
 */
public abstract class AbstractObserver {

    public abstract void updateState(SwitchState state);

}
