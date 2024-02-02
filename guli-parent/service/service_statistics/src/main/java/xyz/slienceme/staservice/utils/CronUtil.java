package xyz.slienceme.staservice.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CronUtil {

    public static String generateCronExpression(int intervalInMinutes) {
        // 创建一个 Calendar 实例
        Calendar calendar = Calendar.getInstance();

        // 获取当前时间
        Date currentDate = calendar.getTime();

        // 将当前时间加上指定的分钟间隔
        calendar.add(Calendar.MINUTE, intervalInMinutes);

        // 获取加上间隔后的时间
        Date nextDate = calendar.getTime();

        // 使用 SimpleDateFormat 将时间转换为 Cron 表达式格式
        SimpleDateFormat dateFormat = new SimpleDateFormat("ss mm HH dd MM ? yyyy");
        String cronExpression = dateFormat.format(currentDate) + "-" + dateFormat.format(nextDate);

        return cronExpression;
    }

    public static void main(String[] args) {
        // 生成每5分钟执行一次的 Cron 表达式
        String cronExpression = generateCronExpression(5);
        System.out.println("Generated Cron Expression: " + cronExpression);
    }
}
