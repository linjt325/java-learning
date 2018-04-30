package top.linjt.java_learning.lambda;

import top.linjt.java_learning.lambda.base.BaseCompareWithLambda;


/**
 * @author: XxX
 * @date: 2018/4/25
 * @Description: java8之前的常规创建线程方法和 使用lambda进行线程创建的比较
 */
public class ThreadTest extends BaseCompareWithLambda {

    @Override
    public void normalMethod() {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("normal thread  method ,before java8");
            }
        });
        thread.start();

    }

    @Override
    public void lambdaMethod() {
        Thread thread = new Thread(() -> System.out.println("lambad method "));
        thread.start();
    }

    public static void main(String[] args) {
        ThreadTest test = new ThreadTest();

        test.run();
    }

}
