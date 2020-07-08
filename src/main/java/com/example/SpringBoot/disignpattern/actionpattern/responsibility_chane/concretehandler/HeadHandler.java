package com.example.springboot.disignpattern.actionpattern.responsibility_chane;

/**
 * @author duwenxu
 * @create 2020-07-03 16:26
 */
public class HeadHandler extends AbstractHandler{

    private String key;

    public HeadHandler(String key) {
        this.key = key;
    }

    @Override
    public void handleRequest() {
        
    }
}
