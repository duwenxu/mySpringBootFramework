package com.example.springboot.disignpattern.actionpattern.observer.subjects;

import com.example.springboot.disignpattern.actionpattern.observer.AbstractSubject;
import com.example.springboot.disignpattern.actionpattern.observer.switchenum.SwitchState;

/**
 *
 * 具体 主题（被观察者）
 * @author duwenxu
 * @create 2020-07-01 16:49
 */
public class Subject2 extends AbstractSubject {

    private SwitchState state;

    public Subject2(SwitchState state) {
        this.state = state;
    }

    public void change(SwitchState newState){
        state = newState;
        System.out.println(this.getClass().getSimpleName()+"状态改变, newState="+state.getState()+state.getValue());
        this.notifyObserver(state);
    }
}