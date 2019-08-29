package com.example.springboot.spring.aop.proxytest.JDKProxyImp;

import java.lang.reflect.Proxy;

/**
 * 生成JDK动态代理的代理对象
 *
 * @author duwenxu
 * @create 2019-02-28 18:21
 */
public class JDKProxyFactory {
    //需要代理的对象
    private Object object;

    public JDKProxyFactory(Object object) {
        this.object = object;
    }

    /**
     *JDK实现代理只需要使用newProxyInstance方法,但是该方法需要接收三个参数,完整的写法是:
     * static Object newProxyInstance(ClassLoader loader, Class<?>[] interfaces,InvocationHandler h )
     * 注意该方法是在Proxy类中是静态方法,且接收的三个参数依次为:
     *
     * ClassLoader loader:指定当前目标对象使用类加载器,获取加载器的方法是固定的
     * Class<?>[] interfaces:目标对象实现的接口的类型,使用泛型方式确认类型
     * InvocationHandler h:事件处理,执行目标对象的方法时,会触发事件处理器的方法,会把当前执行目标对象的方法作为参数传入
     *
     * @return
     */
    public Object getProxyInstance() {
        return Proxy.newProxyInstance(JDKProxyFactory.class.getClassLoader(),
                object.getClass().getInterfaces(),
                (proxy, method, args) -> {  //new InvocationHandler(){}  lambda表达式实现
                    System.out.println("before....");
                    Object ObjectProxy = method.invoke(object, args);
                    System.out.println("after....");
                    return ObjectProxy;
                });
    }
}
