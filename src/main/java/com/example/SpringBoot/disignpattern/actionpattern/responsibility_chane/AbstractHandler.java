package com.example.springboot.disignpattern.actionpattern.responsibility_chane;

/**
 * 抽象处理者
 *
 * @author duwenxu
 * @create 2020-07-03 16:22
 */
public abstract class AbstractHandler {

    private AbstractHandler nextHandler;

    public AbstractHandler() {
    }

    public void setNextHandler(AbstractHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    protected void handleException(String requestKey){
        if (this.getNextHandler()!=null){
            this.getNextHandler().handleRequest(requestKey);
        }else {
            try {
                throw  new Exception("Handler链不能够处理该请求！");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public AbstractHandler getNextHandler() {
        return nextHandler;
    }

    public abstract void handleRequest(String requestKey);
}
