package com.cheney.study.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

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
        maxAndMin();


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
}
