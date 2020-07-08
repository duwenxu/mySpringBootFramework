package com.example.springboot.disignpattern.actionpattern.observer.switchenum;


import java.util.Arrays;
import java.util.Optional;

public enum SwitchState {

    OPEN("OPEN",1),
    CLOSE("CLOSE",2),
    UNAVAIBLE("UNAVAIBLE",0);

    private int value;

    private String state;

    SwitchState( String state,int value) {
        this.value = value;
        this.state = state;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public static int getValue(String state){
        Optional<SwitchState> first = Arrays.stream(values()).filter(switchState ->
                switchState.state.equals(state.toUpperCase())
        ).findFirst();
        return first.map(switchState -> switchState.value).orElseGet(() -> UNAVAIBLE.value);
    }
}
