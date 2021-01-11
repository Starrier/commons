package org.starrier.common.unique;

import org.springframework.stereotype.Component;

/**
 * @author starrier
 * @date 2021/1/9
 */
@Component
public class UniqueIdGenerateUtils {

    private final long workerId;
    private final long datacenterId;
    private final long twepoch = 1288834974657L; //Thu, 04 Nov 2010 01:42:54 GMT
    private final long workerIdBits = 5L; //节点ID长度
    private final long datacenterIdBits = 5L; //数据中心ID长度
    private final long maxWorkerId = -1L ^ (-1L << workerIdBits); //最大支持机器节点数0~31，一共32个
    private final long maxDatacenterId = -1L ^ (-1L << datacenterIdBits); //最大支持数据中心节点数0~31，一共32个
    private final long sequenceBits = 12L; //序列号12位
    private final long workerIdShift = sequenceBits; //机器节点左移12位
    private final long datacenterIdShift = sequenceBits + workerIdBits; //数据中心节点左移17位
    private final long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits; //时间毫秒数左移22位
    private final long sequenceMask = -1L ^ (-1L << sequenceBits); //4095
    private long sequence = 0L;
    private long lastTimestamp = -1L;

    public UniqueIdGenerateUtils() {
        this(0L, 0L);
    }

    public UniqueIdGenerateUtils(long workerId, long datacenterId) {
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
        }
        if (datacenterId > maxDatacenterId || datacenterId < 0) {
            throw new IllegalArgumentException(String.format("datacenter Id can't be greater than %d or less than 0", maxDatacenterId));
        }
        this.workerId = workerId;
        this.datacenterId = datacenterId;
    }

    public static UniqueIdGenerateUtils get() {
        return IdGenHolder.instance;
    }

    public synchronized long nextId() {
        //获取当前毫秒数
        long timestamp = timeGen();
        //如果服务器时间有问题(时钟后退) 报错。
        if (timestamp < lastTimestamp) {
            throw new RuntimeException(String.format(
                    "Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
        }
        //如果上次生成时间和当前时间相同,在同一毫秒内
        if (lastTimestamp == timestamp) {
            //sequence自增，因为sequence只有12bit，所以和sequenceMask相与一下，去掉高位
            sequence = (sequence + 1) & sequenceMask;
            //判断是否溢出,也就是每毫秒内超过4095，当为4096时，与sequenceMask相与，sequence就等于0
            if (sequence == 0) {
                timestamp = tilNextMillis(lastTimestamp); //自旋等待到下一毫秒
            }
        } else {
            sequence = 0L; //如果和上次生成时间不同,重置sequence，就是下一毫秒开始，sequence计数重新从0开始累加
        }
        lastTimestamp = timestamp;
        // 最后按照规则拼出ID。
        // 000000000000000000000000000000000000000000  00000            00000       000000000000
        // time                                                               datacenterId   workerId    sequence
        return ((timestamp - twepoch) << timestampLeftShift) | (datacenterId << datacenterIdShift)
                | (workerId << workerIdShift) | sequence;
    }

    protected long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    protected long timeGen() {
        return System.currentTimeMillis();
    }

    private static class IdGenHolder {
        private static final UniqueIdGenerateUtils instance = new UniqueIdGenerateUtils();
    }

}
