package com.cheney.study.lambda;

import org.junit.Test;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;

/**
 *
 *
 * @version V1.0
 * @className: CollectTest
 * @author: Cheney
 * @date 2019-08-31 15:19
 */
public class ReduceTest {

    @Test
    public void testSum1() {
        Integer[] arr = {2};
        List<Integer> list = Arrays.asList(arr);
        Integer aBoolean = list.stream().reduce((one, two) -> {
            System.out.println(one);
            System.out.println(two);
            return one + two;
        }).orElse(0);
        System.out.println("final -> " + aBoolean);
    }

    @Test
    public void testSum2() {
        Integer[] arr = {2};
        List<Integer> list = Arrays.asList(arr);
        Integer aBoolean = list.stream().reduce(0, (one, two) -> {
            System.out.println(one);
            System.out.println(two);
            return one + two;
        });
        System.out.println("final -> " + aBoolean);
    }

    @Test
    public void listToMap() {
        ArrayList<Map<String, String>> list = new ArrayList<>();
        HashMap<String, String> map = new HashMap<>();
        map.put("name", "张三");
        map.put("sex", "男");
        list.add(map);
        map = new HashMap<>();
        map.put("name", "李四");
        map.put("sex", "男");
        list.add(map);
        map = new HashMap<>();
        map.put("name", "张姗姗");
        map.put("sex", "女");
        list.add(map);

        Map<String, String> reduce = list.stream().reduce(new HashMap<String, String>(),
                new BiFunction<Map<String, String>, Map<String, String>, Map<String, String>>() {
                    @Override
                    public Map<String, String> apply(Map<String, String> map, Map<String, String> item) {
                        //item 为list中的一个map
                        map.put(item.get("name"), item.get("sex"));
                        return map;
                    }
                }, new BinaryOperator<Map<String, String>>() {
                    @Override
                    public Map<String, String> apply(Map<String, String> map1, Map<String, String> map2) {
                        System.out.println("并发处理时执行合并操作。。。");
                        map1.putAll(map2);
                        return map1;
                    }
                });
        System.out.println(reduce);
    }
}
