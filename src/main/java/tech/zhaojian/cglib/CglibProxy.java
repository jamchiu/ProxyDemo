package tech.zhaojian.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Cglib 动态代理Demo
 */
//Subjuet
//注意，这里是没有接口的，因为没有接口，所以这个类无法使用JDK代理
class Subject {
    public void say() {
        System.out.println(this.getClass().getSimpleName() + ">>>" + "say 方法正在执行...");
    }
}

//Cglib动态代理类，必须实现MethondInterceptor接口
class SubjectMethodInterceptor implements MethodInterceptor{
    @Override
    public Object intercept(Object object, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {

        //代理类可以进行方法增强
        System.out.println("Cglib 动态代理>>>被代理类say方法被执行前...");
        Object returnVal= methodProxy.invokeSuper(object,args);
        System.out.println("Cglib 动态代理>>>被代理类say方法被执行后...");

        return returnVal;
    }
}

public class CglibProxy {
    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Subject.class);
        enhancer.setCallback(new SubjectMethodInterceptor());

        Subject subject = ((Subject) enhancer.create());
        subject.say();
    }
}
