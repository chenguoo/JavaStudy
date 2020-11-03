package com.cheney.study.enums;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 枚举工具类
 * @author Cheney
 * @date 2020-11-03 20:00
 */
public class EnumUtils {
    /**
     * 获取这些状态作为下拉列表的填充
     * <p>
     * 这里用了Java 8的新特性 附加约束。也就是说extends后面可以在通过&符号附加额外约束，可以重复使用，
     * 注意必须为接口类型，不能为抽象类或者其他Class。表示泛型的上界受到多个约束的制约。
     * <E extends Enum<?> & Enumerator>可以解读为E必须为一个枚举类而且同时还必须实现Enumerator接口。
     * @param clazz
     * @return java.util.Map<java.lang.Integer, java.lang.String>
     * @author Cheney
     * @date 2020/11/3/003 20:01
     */
    public static <E extends Enum<?> & Enumerator> Map<Integer, String> enumToMap(Class<E> clazz) {
        E[] enumConstants = clazz.getEnumConstants();
        Map<Integer, String> map = new LinkedHashMap<>();
        for (E e : enumConstants) {
            map.put(e.value(), e.description());
        }
        return map;
    }

    /**
     * Stream 版本
     * @param enumClazz
     * @return java.util.Map<java.lang.Integer, java.lang.String>
     * @author Cheney
     * @date 2020/11/3/003 20:03
     */
    public static <E extends Enum<?> & Enumerator> Map<Integer, String> enumToOptions(Class<E> enumClazz) {
        // 合并时检查 key 是否重复
        BinaryOperator<String> merge = (u, v) -> {
            throw new IllegalStateException(String.format("Duplicate key %s", u));
        };
        Enumerator[] enumConstants = enumClazz.getEnumConstants();
        return Stream.of(enumConstants)
                .collect(Collectors.toMap(Enumerator::value,
                        Enumerator::description,
                        merge, LinkedHashMap::new));

    }

    /**
     * 根据值找到枚举
     * @param enumClazz
     * @param value
     * @return java.util.Optional<E>
     * @author Cheney
     * @date 2020/11/3/003 20:04
     */
    public static <E extends Enum<?> & Enumerator> Optional<E> getEnumByValue(Class<E> enumClazz, final Integer value) {

        return Stream.of(enumClazz.getEnumConstants())
                .filter(enumerator -> Objects.equals(enumerator.value(), value))
                .findAny();
    }


    public static void main(String[] args) {
        Map<Integer, String> map = EnumUtils.enumToMap(EnabledEnum.class);
        System.out.println(map);
        map = EnumUtils.enumToOptions(EnabledEnum.class);
        System.out.println(map);
        Optional<EnabledEnum> value = EnumUtils.getEnumByValue(EnabledEnum.class, 1);
        value.ifPresent(System.out::println);
    }
}
