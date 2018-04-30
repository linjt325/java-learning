package top.linjt.java_learning.lambda;

import top.linjt.java_learning.lambda.base.BaseCompareWithLambda;

import java.util.Arrays;
import java.util.List;

/**
 * @author: XxX
 * @date: 2018/4/27
 * @Description: 将流的元素进行处理 重新生成一个流, 最后对每个元素通过reduce方法进行整合:例如计算所有商品的税总额
 */
public class ReduceTest extends MapTest {


    @Override
    public void normalMethod() {

        Double taxSum=0d;
        for (Double integer : list) {
            integer *= 0.17;
            taxSum += integer;
        }
        System.out.println(taxSum);
    }

    @Override
    public void lambdaMethod() {

        //reduce 方法中可以指定计算的初始值
        Double aDouble = list.stream().map(n -> n *= 0.17).reduce((x, y) -> x + y).get();
        System.out.println(aDouble);
    }

    public static void main(String[] args) {

        ReduceTest test = new ReduceTest();

        test.run();
    }
}
