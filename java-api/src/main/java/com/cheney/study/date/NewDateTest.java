package com.cheney.study.date;

import org.junit.Test;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

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


    /**设置格式化模板**/
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSS");

    /**设置日期时区常量**/
    public static final ZoneId CHINA_ZONE_ID = ZoneId.systemDefault();
    /**Date格式化为DateTime**/
    @Test
    public void dateToDateTime(){
        Date date = new Date();
        LocalDateTime dateTime = date.toInstant().atZone(CHINA_ZONE_ID).toLocalDateTime();
        System.out.println(dateTime);
    }
    /**LocalDate/LocalDateTime转Date**/
    @Test
    public void toDate(){
        // LocalDate
        LocalDate localDate = LocalDate.now();
        Date d1 = Date.from(localDate.atStartOfDay(CHINA_ZONE_ID).toInstant());
        System.out.println(d1);

        // LocalDateTime
        LocalDateTime localDateTime = LocalDateTime.now();
        Date d2 = Date.from(localDateTime.atZone(CHINA_ZONE_ID).toInstant());
        System.out.println(d2);
    }

    /**日期格式化**/
    @Test
    public void formatDate(){
        System.out.println(LocalDateTime.now().format(DATE_TIME_FORMATTER));
    }
    /**日期加减**/
    @Test
    public void plusDay(){
        LocalDateTime dateTime = LocalDateTime.now(CHINA_ZONE_ID);
        //天
        dateTime=dateTime.plusDays(1);
        //时
        dateTime=dateTime.plusHours(-1);
        //分钟
        dateTime=dateTime.plusMinutes(30);
        System.out.println(dateTime.format(DATE_TIME_FORMATTER));
    }
    /**日期时间间隔**/
    @Test
    public void betweenDay(){
        // LocalDateTime
        LocalDateTime startDate = LocalDateTime.of(2019,07,01,12,12,22);
        LocalDateTime endDate = LocalDateTime.of(2019,07,03,12,12,22);
        Long withSecond =  endDate.atZone(CHINA_ZONE_ID).toEpochSecond() - startDate.atZone(CHINA_ZONE_ID).toEpochSecond();
        System.out.println(withSecond/60/60/24);

        // LocalDate
        LocalDate startDate2 = LocalDate.of(2019,07,01);
        LocalDate endDate2 = LocalDate.of(2019,07,03);
        Long withSecond2 =  endDate2.toEpochDay() - startDate2.toEpochDay();
        System.out.println(withSecond2);
    }

    /**第一天and最后一天**/
    @Test
    public void theLastDay(){
        // 当月第一天
        LocalDateTime dateTime = LocalDateTime.of(2019,07,03,12,12,22);
        dateTime = dateTime.with(TemporalAdjusters.firstDayOfMonth());
        System.out.println(dateTime);
        // 当月最后一天
        dateTime = dateTime.with(TemporalAdjusters.lastDayOfMonth());
        System.out.println(dateTime);

        //当月的第几天
        dateTime = LocalDateTime.now();
        int dayOfMonth = dateTime.getDayOfMonth();
        System.out.println(dayOfMonth);
        // 当前周的第几天
        int dayOfWeek = dateTime.getDayOfWeek().getValue();
        System.out.println(dayOfWeek);
    }

    /*
    SpringBoot中应用LocalDateTime

    将LocalDateTime字段以时间戳的方式返回给前端添加日期转化类
    eg:
    public class LocalDateTimeConverter extends JsonSerializer{
        @Override
        public void serialize(LocalDateTime value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeNumber(value.toInstant(ZoneOffset.of("+8")).toEpochMilli());
        }
    }

    并在LocalDateTime字段上添加@JsonSerialize(using = LocalDateTimeConverter.class)注解，如下：
    eg:
    @JsonSerialize(using = LocalDateTimeConverter.class)
    protected LocalDateTime gmtModified;

    将LocalDateTime字段以指定格式化日期的方式返回给前端 在LocalDateTime字段上添加@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")注解即可，如下：
    eg:
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    protected LocalDateTime gmtModified;

    对前端传入的日期进行格式化 在LocalDateTime字段上添加@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")注解即可，如下：
    eg:
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    protected LocalDateTime gmtModified;
*/
}
