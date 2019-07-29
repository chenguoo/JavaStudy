package com.cheney.study.lambda;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Description:
 *
 * @author Cheney
 * @date 2018/8/15/01510:57
 */
public class LambdaTest {

    public static void main(String[] args) {

        //使用lambda表达式map进行数据计算
        //map();

        //使用lambda表达式filter进行数据过滤
        //length();

        //使用lambda表达式计算最大值,最小值,总和,平均值
        //        maxAndMin();
        //        flatMapTest();

//        sunTest();
        sunTest2();
    }


    /**
     * 使用lambda表达式map进行数据计算
     */
    public static void map() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1000);
        list.add(1200);
        list.add(1300);
        list.add(1400);
        list.add(1500);
        list.add(1800);
        //以前的方法
        for (Integer salary : list) {
            double newSalary = salary + 0.2 * salary;
            System.out.println(newSalary);
        }
        System.out.println("----------------lambda----------------");
        //lambda表达式
        list.stream().map((salary) -> salary + 0.2 * salary).forEach(System.out::println);

    }

    /**
     * 使用lambda表达式filter进行数据过滤
     */
    public static void length() {
        ArrayList<String> list = new ArrayList<>();
        list.add("java");
        list.add("php");
        list.add("c");
        System.out.println("----------------lambda----------------");

        //lambda表达式
        List<String> filterList = list.stream().filter(lang -> lang.length() > 3).collect(Collectors.toList());
        filterList.forEach(System.out::println);
        //lambda表达式处理 字符串拼接
        String upperString = list.stream().map(String::toUpperCase).collect(Collectors.joining("-"));
        System.out.println(upperString);
    }

    /**
     * 使用lambda表达式计算最大值,最小值,总和,平均值
     */
    public static void maxAndMin() {
        List<Integer> list = Arrays.asList(2, 4, 5, 3, 2, 6, 7, 89, 43, 33);

        System.out.println("----------------lambda----------------");
        IntSummaryStatistics statistics = list.stream().mapToInt((num) -> num).summaryStatistics();
        System.out.println("max:" + statistics.getMax());
        System.out.println("min:" + statistics.getMin());
        System.out.println("sum:" + statistics.getSum());
        System.out.println("average:" + statistics.getAverage());
    }

    public static void flatMapTest() {
        Stream<List<Integer>> inputStream = Stream.of(
                Arrays.asList(1),
                Arrays.asList(2, 3),
                Arrays.asList(4, 5, 6)
        );
        Stream<Integer> outputStream = inputStream.
                flatMap((childList) -> childList.stream());
        outputStream.forEach(System.out::println);
    }

    /**
     * @return void
     * @Description 求和
     * @Author Cheney
     * @Date 2019/7/29/029 19:20
     * @Param []
     */
    public static void sunTest() {
        int value = Arrays.asList(1, 2, 3, 4, 5).stream()
                .reduce(0, (sun, integer) -> sun + integer).intValue();
        System.out.println(value);

    }

    /**
     * @return void
     * @Description 求和
     * @Author Cheney
     * @Date 2019/7/29/029 19:20
     * @Param []
     */
    public static void sunTest2() {
        ArrayList<Map> maps = new ArrayList<>();
        HashMap map1 = new HashMap<>(2);
        map1.put("name", "zhanshang");
        map1.put("age", 18);
        maps.add(map1);
        HashMap map2 = new HashMap<>(2);
        map2.put("name", "lisi");
        map2.put("age", 22);
        maps.add(map2);
        int age = maps.stream()
                .mapToInt(map -> (int) map.get("age")).sum();

        System.out.println(age);

    }
}
