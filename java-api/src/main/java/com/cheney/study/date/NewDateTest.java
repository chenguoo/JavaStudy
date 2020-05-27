package com.cheney.study.date;

import java.time.Instant;

/**
 * todo
 *
 * @author Cheney
 * @date 2019/12/27/027 19:35
 */
public class NewDateTest {

    public static void main(String[] args) {

    }
    /*
    方法前缀的含义,统一了api:

    of：静态工厂方法(用类名去调用)。
    parse：静态工厂方法，关注于解析(用类名去调用)。
    now： 静态工厂方法，用当前时间创建实例(用类名去调用)
    get：获取某些东西的值。
    is：检查某些东西的是否是true。
    with：返回一个部分状态改变了的时间日期对象拷贝(单独一个with方法,参数为TemporalAdjusters类型)。
    plus：返回一个时间增加了的、时间日期对象拷贝(如果参数是负数也能够有minus方法的效果)。
    minus：返回一个时间减少了的、时间日期对象拷贝。
    to：把当前时间日期对象转换成另外一个，可能会损失部分状态。
    at：把这个对象与另一个对象组合起来，例如： date.atTime(time)。
    format :根据某一个DateTimeFormatter格式化为字符串。
    */

    /**
     * Instant：时间戳，相当于java.util的Date
     */
    public static void testInstant() {
        //1970-01-01T00:02:00.000100Z
        Instant instant = Instant.ofEpochSecond(120, 100000);
        // instant.
    }

    /**
     * LocalDate：只包含日期，比如：2016-10-20
     */
    public static void testLocalDate() {

    }

    /**
     * LocalTime：只包含时间，比如：23:12:10
     */
    public static void testLocalTime() {

    }

    /**
     * LocalDateTime：包含日期和时间，比如：2016-10-20 23:14:21
     */
    public static void testLocalDateTime() {

    }

    /**
     * Duration：计算两个“时间”的间隔
     */
    public static void testDuration() {

    }

    /**
     * Period：用于计算两个“日期”的间隔
     */
    public static void testPeriod() {

    }

    /**
     * ZoneOffset：时区偏移量，比如：+8:00
     */
    public static void testZoneOffset() {

    }

    /**
     * ZonedDateTime：可以得到特定时区的日期/时间
     */
    public static void testZonedDateTimee() {

    }

    /**
     * Clock：时钟，比如获取目前美国纽约的时间
     */
    public static void testClock() {

    }

    /**
     * DateTimeFormatter：时间格式化
     */
    public static void testDateTimeFormatter() {

    }


}
