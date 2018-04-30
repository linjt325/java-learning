package top.linjt.java_learning.lambda;

import top.linjt.java_learning.lambda.base.BaseCompareWithLambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author: XxX
 * @date: 2018/4/27
 * @Description: 对流的数据进行过滤
 */
public class FilterTest extends BaseCompareWithLambda {


    List<String> list = Arrays.asList("java", "python", "c#", "c++", "ruby", "swift");

    @Override
    public void normalMethod() {

        List<String> temp = new ArrayList<>();
        for (String s : list) {
            if (s.startsWith("j")) {
                temp.add(s);
            }
        }

        temp.forEach(System.out::println);

    }

    @Override
    public void lambdaMethod() {
        System.out.println("filter with prefix \"j\"");
        list.stream().filter((s) -> s.startsWith("j")).forEach(System.out::println);
//        .collect(Collectors.toList())
        System.out.println("count langauge end with \"a\"" );
        long count = list.stream().filter((s) -> s.endsWith("a")).count();
        System.out.println(count);

        System.out.println("print all languages ");
        list.stream().filter((s) -> true).forEach(System.out::println);

        //合并条件
        System.out.println("get language j*a");
        Predicate<String> condition1 = (n) -> n.startsWith("j");
        Predicate<String> condition2 = (n) -> n.endsWith("a");
        List<String> collect = list.stream().filter(condition1.and(condition2)).collect(Collectors.toList());
        collect.forEach(System.out::println);

    }

    public void filter(Predicate<String> condition) {
        System.out.println(list.stream().filter(condition).count());
    }

    public static void main(String[] args) {

        FilterTest test = new FilterTest();

        test.run();
    }
}
