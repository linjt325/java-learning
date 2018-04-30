package top.linjt.java_learning.lambda.base;

/**
 * @author: XxX
 * @date: 2018/4/26
 * @Description:
 */
public abstract class BaseCompareWithLambda<T> {

    public abstract void normalMethod();

    public abstract void lambdaMethod();

    public void run() {

        normalMethod();

        lambdaMethod();
    }


}
