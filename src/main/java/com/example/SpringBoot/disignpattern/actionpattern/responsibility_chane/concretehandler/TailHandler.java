package com.example.springboot.disignpattern.actionpattern.responsibility_chane.concretehandler;

import com.example.springboot.disignpattern.actionpattern.responsibility_chane.AbstractHandler;

/**
 * @author duwenxu
 * @create 2020-07-03 16:35
 */
public class TailHandler extends AbstractHandler {

    private String key;

    public TailHandler(String key) {
        this.key = key;
    }

    @Override
    public void handleRequest(String requestKey) {
        if (requestKey.equals(this.key)){
            System.out.println(this.getClass().getSimpleName()+"处理成功！");
        }else {
            this.handleException(requestKey);
        }
    }
}


