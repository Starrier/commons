package org.starrier.common.utils;

import parquet.org.slf4j.Logger;
import parquet.org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author imperator
 * @date 2019-09-10
 */
public class SerializableUtil {


    private static final Logger LOGGER = LoggerFactory.getLogger(SerializableUtil.class);

    /**
     * 序列化
     *
     * @param object
     * @return
     */
    public static byte[] serializable(Object object) {

        try (ByteArrayOutputStream baos = new ByteArrayOutputStream(); ObjectOutputStream oos = new ObjectOutputStream(baos)) {
            oos.writeObject(object);
            return baos.toByteArray();
        } catch (IOException e) {
            LOGGER.error("serializable异常: " + e.getMessage());
            throw new RuntimeException("serializable异常: " + e.getMessage());
        }
    }

    /**
     * 反序列化
     *
     * @param bytes
     * @return
     */
    public static Object unserializable(byte[] bytes) {
        try (ByteArrayInputStream bais = new ByteArrayInputStream(bytes); ObjectInputStream ois = new ObjectInputStream(bais)) {
            return ois.readObject();
        } catch (Exception e) {
            throw new RuntimeException("unserializable异常: " + e.getMessage());
        }
    }

}
