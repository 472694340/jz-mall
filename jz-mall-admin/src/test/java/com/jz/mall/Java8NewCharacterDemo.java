package com.jz.mall;

import org.junit.jupiter.api.Test;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class Java8NewCharacterDemo {
    public static void main(String[] args) {

        List<String> list = Arrays.asList("123","456");// 等同于  List<String> list = Arrays.asList(new String[]{"123","456"})
        System.out.println(list.getClass());// class java.util.Arrays$ArrayList 这是Arrays的一个内部类
//        System.out.println(list.remove(1)); //这里会报错  Exception in thread "main" java.lang.UnsupportedOperationException

        int[] strArray = new int[]{456,567};
        System.out.println(strArray.getClass());

        //正确数组转集合的方式
        List<String> arrayList = new ArrayList<String>(Arrays.asList("123","456"));
        System.out.println(arrayList.getClass());

        //数组转集合
        List arrayToList = Arrays.asList(strArray); //这里传入的是一个数组,相当于这个集合里只有一个元素,就是strArray数组
        arrayToList.stream().forEach(System.out::println);
        System.out.println(arrayToList.getClass());
        System.out.println(arrayToList.get(2));

        //集合转数组
        String[] array = list.toArray(new String[0]);
        System.out.println(array.getClass());

    }


    /**
     * 并行操作流
     */
    @Test
    public void parallelStreamApi(){
        List<Integer> list = Arrays.asList(2,4,6,7);
        list.parallelStream().forEach(num-> System.out.println(Thread.currentThread().getName() + ">>>" + num));//这里的执行是通过主线程和其他线程共同完成的
    }

    /**
     Optional用于防止 NullPointerException 的工具
     */
    @Test
    public void optionalDemo(){
        String str = "ShenLiang";
        /**
         * 构造器私有,只能通过下面方法
                public static <T> Optional<T> of(T value) {
                    return new Optional<>(value);
                }
         */
        Optional<String> optional =Optional.of(str);//创建对象

        System.out.println(optional.get()); //获取值
        System.out.println(optional.isPresent()); //存在值返回true,不存在返回false
        optional.ifPresent((s)->{ //如果Optional实例有值,则为其调用consumer,否则不作处理
            System.out.println(s.charAt(1));
        });// 输出 h
        System.out.println(optional.orElse("c"));//如果有值则返回,没有值则返回指定值"c"
    }

    //Date API
    @Test
    public void dateAPIDemo(){
        /**
         * 1.Clock类(当前日期和时间的方法)
         */
        Clock clock = Clock.systemDefaultZone();        //Clock类,获取当前时区
        long start = clock.millis();//获取当前系统毫秒数
        System.out.println("start: " + start); //start: 1677048344949
        Instant instant = clock.instant();//某一个特定时间点
        System.out.println(instant);//2023-02-22T06:45:44.949Z

        Date legacyDate = Date.from(instant);//通过instance创建旧版本的日期Date类
        System.out.println(legacyDate);//Wed Feb 22 14:45:44 CST 2023

        /**
         * 2.ZoneId类(时区)
         */
        ZoneId.getAvailableZoneIds().stream()
                .filter((s)->s.startsWith("Asia/Shanghai"))
                .forEach(System.out::println);//不加filter就是输出所有时区
        /**
                public static ZoneId of(String zoneId) {
                    return of(zoneId, true);
                }
         */
        ZoneId zoneId = ZoneId.of("Asia/Shanghai");
        System.out.println(zoneId.getRules()); //ZoneRules[currentStandardOffset=+08:00]

        /**
         * 3.LocalTime(本地时间) 定义一个没有时区信息的时间
         */
        ZoneId zoneSH = ZoneId.of("Asia/Shanghai");
        ZoneId zoneLA = ZoneId.of("America/Los_Angeles");
        LocalTime timeSH = LocalTime.now(zoneSH);
        LocalTime timeLA = LocalTime.now(zoneLA);
        System.out.println("timeSH: " + timeSH);//localTime: 14:45:44.960
        System.out.println("timeLA: " + timeLA);//localTime: 14:45:44.960
        //计算两个时区的时查
        long betweenHours = ChronoUnit.HOURS.between(timeLA, timeSH);//小时差
        long betweenMin = ChronoUnit.MINUTES.between(timeLA, timeSH);//分钟差
        System.out.println(betweenHours);//-8
        System.out.println(betweenMin);//-480

        //LocalTime 提供了多种工厂方法来简化对象的创建，包括解析时间字符串.
        LocalTime localTime = LocalTime.of(23, 59, 59);
        System.out.println(localTime);//23:59:59
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT).withLocale(Locale.GERMAN);
        LocalTime parse = LocalTime.parse("14:55", dateTimeFormatter);
        System.out.println(parse);//14:55

        /**
         * 4.LocalDate(本地日期),当前日期
         */
        LocalDate now = LocalDate.now();//获取当前日期
        System.out.println("今天的日期: " + now);//今天的日期: 2023-02-22
        System.out.println("明天的日期: " + now.plus(1,ChronoUnit.DAYS));//明天的日期: 2023-02-23
        System.out.println("昨天的日期: " + now.minusDays(1));//昨天的日期: 2023-02-21
        System.out.println("今天是周几: " + now.getDayOfWeek());//今天是周几: WEDNESDAY(周三)
        LocalDate birthday = LocalDate.of(1994, 8, 1);
        System.out.println("我生日那天是周几: " + birthday.getDayOfWeek());//我生日那天是周几: MONDAY 周一


        /**
         * 5.LocalDateTime(本地具体时间)
         */
        LocalDateTime dateNow = LocalDateTime.now();
        String date = DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(dateNow);
        System.out.println(date);//2023-02-22T15:23:42.979
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println(timeFormatter.format(dateNow));//2023-02-22 15:25:09


        LocalDateTime rightNow = LocalDateTime.of(2020, 12, 31, 12, 0, 0);
        String dateRight= DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(rightNow);
        System.out.println(dateRight);
        DateTimeFormatter formatterOfYYYY = DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm:ss");
        System.out.println(formatterOfYYYY.format(rightNow));//2021-12-31 12:00:00 在跨年周的时候使用YYYY会出现年份错误问题

        DateTimeFormatter formatterOfYyyy = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println(formatterOfYyyy.format(rightNow));//2020-12-31 12:00:00


    }




}
