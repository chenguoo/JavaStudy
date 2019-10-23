package com.cheney.study.lambda;

import com.cheney.study.lambda.vo.User;
import org.junit.Test;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.*;
import static org.junit.Assert.assertEquals;

/**
 * TODO
 *
 * @version V1.0
 * @className: StreamTest
 * @author: Cheney
 * @date 2019-09-25 11:08
 */
public class StreamTest {
    /**
     * 测试生成流:五种方式
     *
     * @param
     * @return
     * @author Cheney
     * @date 2019/9/25/025 11:32
     */
    @Test
    public void createStream() throws IOException {
        //1.通过集合生成，应用中最常用的一种
        List<Integer> integerList = asList(1, 2, 3, 4, 5);
        Stream<Integer> stream1 = integerList.stream();

        //2.通过数组生成
        /*通过Arrays.stream方法生成流，并且该方法生成的流是数值流【即IntStream】而不是Stream<Integer>。补充一点使用数值流可以避免计算过程中拆箱装箱，提高性能。
         Stream API提供了mapToInt、mapToDouble、mapToLong三种方式将对象流【即Stream】转换成对应的数值流，同时提供了boxed方法将数值流转换为对象流*/
        int[] intArr = new int[]{1, 2, 3, 4, 5};
        IntStream stream2 = Arrays.stream(intArr);

        //3.通过值生成
        // 通过Stream的of方法生成流，通过Stream的empty方法可以生成一个空流
        Stream<Integer> stream3 = Stream.of(1, 2, 3, 4, 5);

        //4.通过文件生成
        // 通过Files.line方法得到一个流，并且得到的每个流是给定文件中的一行
        Stream<String> lines4 = Files.lines(Paths.get("data.txt"), Charset.defaultCharset());

        //5.通过函数生成 提供了iterate和generate两个静态方法从函数中生成流
        // iterate 方法接受两个参数，第一个为初始化值，第二个为进行的函数操作，因为iterator生成的流为无限流，通过limit方法对流进行了截断，只生成5个偶数
        Stream<Integer> stream51 = Stream.iterate(0, n -> n + 2).limit(5);
        // generate方法接受一个参数，方法参数类型为Supplier，由它为流提供值。generate生成的流也是无限流，因此通过limit对流进行了截断
        Stream<Double> stream52 = Stream.generate(Math::random).limit(5);
    }


    @Test
    public void minAndMax() {
        Optional<Integer> min = users.stream().map(User::getAge).min(Integer::compareTo);
        Optional<Integer> max = users.stream().map(User::getAge).max(Integer::compareTo);
        //或者
        OptionalInt minInt = users.stream().mapToInt(User::getAge).min();
        OptionalInt maxInt = users.stream().mapToInt(User::getAge).max();
        //或者
        min = users.stream().map(User::getAge).collect(minBy(Integer::compareTo));
        max = users.stream().map(User::getAge).collect(maxBy(Integer::compareTo));
        //或者
        min = users.stream().map(User::getAge).reduce(Integer::min);
        max = users.stream().map(User::getAge).reduce(Integer::max);
    }

    @Test
    public void summingIntTest() {
        int sum = users.stream().collect(summingInt(User::getAge));
        // or
        sum = users.stream().map(User::getAge).reduce(0, Integer::sum);
        // or
        sum = users.stream().mapToInt(User::getAge).sum();
        /*
         在上面求和、求最大值、最小值的时候，对于相同操作有不同的方法可以选择执行。
         可以选择collect、reduce、min/max/sum方法，推荐使用min、max、sum方法。
         因为它最简洁易读，同时通过mapToInt将对象流转换为数值流，避免了装箱和拆箱操作
         */
    }

    /**
     * 求平均值
     *
     * @param
     * @return
     * @author Cheney
     * @date 2019/9/25/025 12:58
     */
    @Test
    public void averagingIntTest() {
        //如果数据类型为double、long，则通过averagingDouble、averagingLong方法进行求平均
        double average = users.stream().collect(averagingInt(User::getAge));
    }

    /**
     * 通过summarizingInt同时求总和、平均值、最大值、最小值
     *
     * @param
     * @return
     * @author Cheney
     * @date 2019/9/25/025 12:58
     */
    @Test
    public void summarizingIntTest() {
        //如果数据类型为double、long，则通过summarizingDouble、summarizingLong方法
        IntSummaryStatistics intSummaryStatistics = users.stream().collect(summarizingInt(User::getAge));
        double average = intSummaryStatistics.getAverage();  //获取平均值
        int min = intSummaryStatistics.getMin();  //获取最小值
        int max = intSummaryStatistics.getMax();  //获取最大值
        long sum = intSummaryStatistics.getSum();  //获取总和
    }

    /**
     * 通过joining拼接流中的元素
     *
     * @param
     * @return
     * @author Cheney
     * @date 2019/9/25/025 13:00
     */
    @Test
    public void joiningTest() {
        String result = users.stream().map(User::getName).collect(Collectors.joining(", "));
    }

    /**
     * 排序
     *
     * @param
     * @return
     * @author Cheney
     * @date 2019/9/25/025 11:21
     */
    @Test
    public void sortedTest() {
        users.stream()
                .sorted(Comparator.comparing(User::getAge))
                .forEach(System.out::println);
    }

    /**
     * 按map的键排序
     *
     * @param
     * @return
     * @author Cheney
     * @date 2019/10/23/023 15:59
     */
    @Test
    public void sortedMapTest() {
        HashMap<String, Integer> codes = new HashMap<>();
        codes.put("United States", 1);
        codes.put("Germany", 46);
        codes.put("France", 33);
        codes.put("China", 86);
        codes.put("Pakistan", 92);

        //按照Map的键排序
        LinkedHashMap<String, Integer> sortedMap = codes.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                // .sorted(Map.Entry.comparingByKey().reversed())  //逆序
                // .sorted(Map.Entry.comparingByValue())           //按值排序
                .collect(toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (v1, v2) -> v2,
                        LinkedHashMap::new));
        //打印
        sortedMap.entrySet().forEach(System.out::println);

        // other 大家可能都知道TreeMap内的元素是有顺序的，所以利用TreeMap排序也是可取的一种方法。
        // 您需要做的就是创建一个TreeMap对象，并将数据从HashMapput到TreeMap中，非常简单
        // 将 `HashMap` 转为 `TreeMap`
        Map sorted = new TreeMap(codes);
        sorted.entrySet().forEach(System.out::println);

    }

    /**
     * 分组
     *
     * @param
     * @return
     * @author Cheney
     * @date 2019/9/25/025 11:21
     */
    @Test
    public void groupingByTest() {
        Map<String, List<User>> collect = users.stream()
                .collect(Collectors.groupingBy(User::getSex));

        /*
         * TODO
         * 在collect方法中传入groupingBy进行分组，其中groupingBy的方法参数为分类函数。
         * 还可以通过嵌套使用groupingBy进行多级分类:
         *
         * Map<Type, List<Dish>> result = menu.stream().collect(groupingBy(Dish::getType,
                groupingBy(dish -> {
                    if (dish.getCalories() <= 400) return CaloricLevel.DIET;
                    else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                    else return CaloricLevel.FAT;
                })));*/
    }

    /**
     * 进阶通过partitioningBy进行分区
     * 分区是特殊的分组，它分类依据是true和false，所以返回的结果最多可以分为两组
     *
     * @param
     * @return
     * @author Cheney
     * @date 2019/9/25/025 13:04
     */
    @Test
    public void partitioningByTest() {
        Map<Boolean, List<User>> result = users.stream().collect(partitioningBy(User::isWorking));
        //等同于:
        result = users.stream().collect(groupingBy(User::isWorking));

        //这个例子可能并不能看出分区和分类的区别，甚至觉得分区根本没有必要，换个明显一点的例子：
        List<Integer> integerList = asList(1, 2, 3, 4, 5);
        Map<Boolean, List<Integer>> res = integerList.stream().collect(partitioningBy(i -> i < 3));
        // 返回值的键仍然是布尔类型，但是它的分类是根据范围进行分类的，分区比较适合处理根据范围进行分类

    }

    /**
     * 最简单的合并两个流
     *
     * @param
     * @return
     * @author Cheney
     * @date 2019/9/27/027 18:26
     */
    @Test
    public void concatTest() {
        Stream<Integer> stream1 = Stream.of(1, 2, 3);
        Stream<Integer> stream2 = Stream.of(4, 5);
        Stream<Integer> result = Stream.concat(stream1, stream2);
        assertEquals(
                asList(1, 2, 3, 4, 5),
                result.collect(Collectors.toList())
        );
    }

    /**
     * concat()方法并不支持合并多个stream，这时需要用到of()，可以实现多个stream的合并
     *
     * @param
     * @return
     * @author Cheney
     * @date 2019/9/27/027 18:27
     */
    @Test
    public void ofTest() {
        Stream<Integer> stream1 = Stream.of(1, 2, 3);
        Stream<Integer> stream2 = Stream.of(4, 5);
        Stream<Integer> stream3 = Stream.of(6, 7, 8);
        Stream<Integer> result = Stream.of(stream1, stream2, stream3)
                .flatMap(i -> i);
        assertEquals(
                asList(1, 2, 3, 4, 5, 6, 7, 8),
                result.collect(Collectors.toList()));
    }


    private final static List<User> users = new ArrayList<>();

    static {
        User user = new User();
        user.setName("张三");
        user.setAge(18);
        user.setSex("男");
        users.add(user);
        user = new User();
        user.setName("李四");
        user.setAge(28);
        user.setSex("男");
        users.add(user);
        user = new User();
        user.setName("张珊");
        user.setAge(20);
        user.setSex("女");
        users.add(user);

        user = new User();
        user.setName("王静");
        user.setAge(26);
        user.setSex("女");
        users.add(user);
        user = new User();
        user.setName("谢字");
        user.setAge(23);
        user.setSex("男");
        users.add(user);
        user = new User();
        user.setName("陈琪");
        user.setAge(18);
        user.setSex("女");
        users.add(user);

    }


}
