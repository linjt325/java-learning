
# lambda基本语法与函数式编程
```
(params) -> expression
(params) -> statement
(params) -> { statements }
```

java8之后提供了对函数式编程的支持,提供了java.util.function包为用于支持函数式编程.
对这类接口都添加了@FunctionalInterface注解

以Iterator接口和stream接口的方法为例:
# 首先要了解Stream接口  
> 该接口的方法分为两类: 一类叫惰性求值，一类叫及早求值。
判断一个操作是惰性求值还是及早求值很简单：只需看它的返回值。如果返回值是 Stream，那么是惰性求值。其实可以这么理解，如果调用惰性求值方法，Stream 只是记录下了这个惰性求值方法的过程，并没有去计算，等到调用及早求值方法后，就连同前面的一系列惰性求值方法顺序进行计算，返回结果。
通用形式为：
<code>Stream.惰性求值.惰性求值. ... .惰性求值.及早求值</code>
整个过程和建造者模式有共通之处。建造者模式使用一系列操作设置属性和配置，最后调 用一个 build 方法，这时，对象才被真正创建。
## forEach(Consumer) -> Consumer:消费者

```
        List<String> list = Arrays.asList("lambda","default method" , "stream API");
        //java 8 之后 list 提供了forEach方法 不需要再写for 循环
        list.forEach((n) -> System.out.println(n));
        //java8 方法引用, 方法引用由:: 双冒号操作符标识
        list.stream().forEach(System.out::println);
```

## filter(Predicate) -> Predicate :谓语 (可以理解为条件)

```
        List<String> list = Arrays.asList("java", "python", "c#", "c++", "ruby", "swift");
        System.out.println("filter with prefix \"j\"");
        list.stream().filter((s) -> s.startsWith("j")).forEach(System.out::println);

```
同时Predicate 还支持条件合并
```
        Predicate<String> condition1 = (n) -> n.startsWith("j");
        Predicate<String> condition2 = (n) -> n.endsWith("a");
        List<String> collect = list.stream().filter(condition1.and(condition2)).collect(Collectors.toList());
        collect.forEach(System.out::println);

```

## map(Function) ->Function    
将流的元素进行处理 重新生成一个流, 距离 计算商品的税

```
 List<Double> list = Arrays.asList(100d, 200d, 300d, 400d, 500d);
 list.stream().map(n -> n *= 0.17).forEach(System.out::println);
 ```
 
## collect(Collectors.toList())  将结果

## reduce(BinraryOperator) : 将数值合并成一个值
将流的元素进行处理 重新生成一个流, 最后对每个元素通过reduce方法进行整合:
```
Double aDouble = list.stream().map(n -> n *= 0.17).reduce((x, y) -> x + y).get();
```

## flatMap 将多个stream连接成一个stream

##  max和min  求最大值和最小值

## parallel() :数据并行化操作
Stream 的并行化也是 Java 8 的一大亮点。数据并行化是指将数据分成块，为每块数据分配单独的处理单元。这样可以充分利用多核 CPU 的优势。
并行化操作流只需改变一个方法调用。如果已经有一个 Stream 对象，调用它的 parallel() 方法就能让其拥有并行操作的能力。如果想从一个集合类创建一个流，调用 parallelStream() 就能立即获得一个拥有并行能力的流。
> 并行化操作只有在 数据规模比较大 或者 数据的处理时间比较长 的时候才能体现出优势
```
int sumSize = Stream.of("Apple", "Banana", "Orange", "Pear")
                    .parallel()
                    .map(s -> s.length())
                    .reduce(Integer::sum)
                    .get();
assertEquals(sumSize, 21);
```

## sorted()  元素顺序 
>  如果集合本身就是无序的，由此生成的流也是无序的。一些中间操作会产生顺序，比如对值做映射时，映射后的值是有序的，这种顺序就会保留 下来。如果进来的流是无序的，出去的流也是无序的。

Stream 具体的通用方法可以查看 [api](https://docs.oracle.com/javase/8/docs/api/)