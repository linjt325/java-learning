package top.linjt.java_learning.lambda;

import top.linjt.java_learning.lambda.base.BaseCompareWithLambda;

import java.util.Arrays;
import java.util.List;

/**
 * @author: XxX
 * @date: 2018/4/27
 * @Description: 将流的元素进行处理 重新生成一个流, 例如 计算商品的税
 */
public class MapTest extends BaseCompareWithLambda {

    List<Double> list = Arrays.asList(100d, 200d, 300d, 400d, 500d);

    @Override
    public void normalMethod() {

        for (Double integer : list) {
            integer *= 0.17;
            System.out.println(integer);
        }



    }

    @Override
    public void lambdaMethod() {

        list.stream().map(n -> n *= 0.17).forEach(System.out::println);
    }

    public static void main(String[] args) {

        MapTest test = new MapTest();

        test.run();
    }
}
