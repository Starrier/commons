package org.starrier.common.token;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.KeyGenerator;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.security.Key;
import java.security.SecureRandom;

import static org.starrier.common.token.TokenConstant.KEY_ALGORTHM;

/**
 * @author : Starrier
 * @date 2019/3/6 0006
 * <p>
 * Description :
 */
public class DESBuilder {

    private Key key;

    public Key getKey() {
        return key;
    }

    public void setKey(Key key) {
        this.key = key;
    }

    /**
     * 构造函数.
     *
     * @param str 传入的字符串，根据字符串随机生成Key
     */
    public DESBuilder(String str) {
        generatorRandomKey(str);
    }

    /**
     * @param strKey 通过strKey生成随机Key
     */
    private void generatorRandomKey(String strKey) {
        try {
            KeyGenerator generator = KeyGenerator.getInstance(KEY_ALGORTHM);
            generator.init(new SecureRandom(strKey.getBytes()));
            this.key = generator.generateKey();
        } catch (Exception e) {
            throw new RuntimeException("Error initializing SqlMap class. Cause: " + e);
        }
    }

    /**
     * @return Key对象
     */
    public String getKeyToString() {
        return Base64.encodeBase64String(key.getEncoded());
    }

    /**
     * @return 文件
     */
    public void getKeyToFile(String keyAddress) throws IOException {
        try (FileOutputStream fileOutput = new FileOutputStream(keyAddress);
             ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput);) {
            objectOutput.writeObject(this.key);
        }
    }
}