package top.linjt.java_learning.lambda;

import top.linjt.java_learning.lambda.base.BaseCompareWithLambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: XxX
 * @date: 2018/4/26
 * @Description: 通过Iterator 提供的forEach方法结合lambda表达式进行遍历
 */
public class IteratorTest extends BaseCompareWithLambda {

    List<String> list = Arrays.asList("lambda","default method" , "stream API");



    @Override
    public void normalMethod() {

        for (String s : list) {
            System.out.println(s);
        }

    }

    @Override
    public void lambdaMethod() {
        //java 8 之后 list 提供了forEach方法 不需要再写for 循环
        list.forEach((n) -> System.out.println(n));
        //java8 方法引用, 方法引用由:: 双冒号操作符标识
        list.stream().forEach(System.out::println);
    }

    public static void main(String[] args) {

        IteratorTest test = new IteratorTest();

        test.run();
    }
}
