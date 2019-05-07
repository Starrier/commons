package org.starrier.common.utils;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import parquet.org.slf4j.Logger;
import parquet.org.slf4j.LoggerFactory;

import java.util.SortedMap;

/**
 * 验签工具
 *
 * @author imperator
 * @date 2019-09-23
 */
public class SignUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(SignUtils.class);

    /**
     * 验签
     *
     * @param params
     * @param sign
     * @param timestamp
     * @return
     */
    public static boolean verifySign(SortedMap<String, String> params, String sign, Long timestamp) {
        String paramsJson = "Timestamp" + timestamp + JSONObject.toJSONString(params);
        return verifySign(paramsJson, sign);
    }

    public static boolean verifySign(String params, String sign) {
        LOGGER.info("Header sign:[{}]", sign);
        if (StringUtils.isEmpty(params)) {
            return false;
        }
        LOGGER.info("Params:[{}]", params);
        String paramSign = getParamsSign(params);
        LOGGER.info("Param sign:[{}]", paramSign);
        return sign.equals(paramSign);
    }

    private static String getParamsSign(String params) {
        return DigestUtils.md5DigestAsHex(params.getBytes()).toUpperCase();
    }
}
