package com.example.springboot.disignpattern.actionpattern.observer;


import com.example.springboot.disignpattern.actionpattern.observer.observes.Observer1;
import com.example.springboot.disignpattern.actionpattern.observer.observes.Observer2;
import com.example.springboot.disignpattern.actionpattern.observer.subjects.Subject1;
import com.example.springboot.disignpattern.actionpattern.observer.switchenum.SwitchState;

/**
 * @author duwenxu
 * @create 2020-07-02 9:44
 */
public class TestDemo {

    public static void main(String[] args) {
        AbstractObserver observer1 = new Observer1();
        AbstractObserver observer2 = new Observer2();

        Subject1 subject1 = new Subject1(SwitchState.CLOSE);
        subject1.attach(observer1);
        subject1.attach(observer2);

        subject1.change(SwitchState.CLOSE);

        subject1.detach(observer1);

        subject1.change(SwitchState.UNAVAIBLE);
    }
}