package tech.zhaojian.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * JDK 动态代理Demo
 */

//Subjuet(公共接口)
interface Subject {
    void say();
}

//RealSubject(被代理类)
class RealSubject implements Subject {
    @Override
    public void say() {
        System.out.println(this.getClass().getSimpleName() + ">>>" + "say 方法正在执行...");
    }
}

//Proxy(代理类)
class InvocationHandlerImpl implements InvocationHandler {
    private Object subject;

    public InvocationHandlerImpl(Object subject) {
        this.subject = subject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("JDK 动态代理>>>被代理类say方法被执行前...");
        Object returnVal = method.invoke(subject, args);
        System.out.println("JDK 动态代理>>>被代理类say方法被执行后...");

        return returnVal;
    }
}


public class JdkProxy {
    public static void main(String[] args) {
        Subject subject = new RealSubject();
        Subject proxy = (Subject) Proxy.newProxyInstance(subject.getClass().getClassLoader(), subject.getClass().getInterfaces(), new InvocationHandlerImpl(subject));
        proxy.say();
    }
}
