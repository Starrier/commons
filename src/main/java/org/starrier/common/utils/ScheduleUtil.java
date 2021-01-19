package org.starrier.common.utils;

import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author imperator
 * @date 2019-09-10
 */
public class ScheduleUtil {
    private static HashMap<String, ScheduledFuture> map = new HashMap<>();
    private static ScheduledExecutorService pool;

    public static void init() {
        pool = Executors.newScheduledThreadPool(3);
    }

    /**
     * @param sr     需要执行测线程，该线程必须继承SRunnable
     * @param delay  延迟执行时间
     * @param period 执行周期时间
     * @param unit   时间单位 比如TimeUnit.SECONDS
     */
    public static void stard(SRunnable sr, long delay, long period, TimeUnit unit) {
        if (sr.getName() == null || map.get(sr.getName()) != null) {
            throw new UnsupportedOperationException("线程名不能为空或者线程名不能重复！");
        }
        if (pool == null || pool.isShutdown()) init();
        ScheduledFuture scheduledFuture = pool.scheduleAtFixedRate(sr, delay, period, unit);
        map.put(sr.getName(), scheduledFuture);
    }

    /**
     * @param sr 停止当前正在执行的线程，该线程必须是继承SRunnable
     */
    public static void stop(SRunnable sr) {
        if (sr.getName() == null) {
            throw new UnsupportedOperationException("停止线程时，线程名不能为空！");
        }
        if (pool == null || pool.isShutdown()) return;//服务未启动
        if (map.size() > 0 && map.get(sr.getName()) != null) {
            map.get(sr.getName()).cancel(true);
            map.remove(sr.getName());
        }
        if (map.size() <= 0) {
            shutdown();
        }
    }

    /**
     * 停止所有线程服务
     */
    public static void shutdown() {
        map.clear();
        pool.shutdown();
    }

    /**
     * 判断该线程是否还存活着，还在运行
     *
     * @param sr
     * @return
     */
    public static boolean isAlive(SRunnable sr) {
        if (map.size() > 0 && map.get(sr.getName()) != null) {
            return !map.get(sr.getName()).isDone();
        }
        return false;
    }

    /*该接口定义了线程的名字，用于管理，如判断是否存活，是否停止该线程等等*/
    public interface SRunnable extends Runnable {
        String getName();
    }
}
