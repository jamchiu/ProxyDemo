package tech.zhaojian.staticProxy;

/**
 * 静态代理Demo
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
class SubjectProxy implements Subject {
    private Subject realSubject = new RealSubject();

    @Override
    public void say() {
        //代理类可以进行方法增强
        System.out.println("静态代理>>>被代理类say方法被执行前...");
        realSubject.say();
        System.out.println("静态代理>>>被代理类say方法被执行后...");
    }
}

public class StaticProxy {
    public static void main(String[] args) {
        SubjectProxy proxy = new SubjectProxy();
        proxy.say();
    }
}