package com.cheney.study.lambda;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 *
 * @version V1.0
 * @className: CollectTest
 * @author: Cheney
 * @date 2019-08-31 15:19
 */
public class CollectTest {

    @Test
    public void ListToMap() {
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

        Map<String, String> collect = list.stream()
                .collect(Collectors.toMap(p -> p.get("name"), p -> p.get("sex"), (k1, k2) -> k1));
        System.out.println(collect);
    }
}
