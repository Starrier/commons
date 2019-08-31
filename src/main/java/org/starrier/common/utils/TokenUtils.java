package org.starrier.common.utils;

import com.google.common.collect.Maps;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.NonNull;
import org.starrier.common.token.DESCoder;

import java.util.Collections;
import java.util.Date;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static org.starrier.common.token.TokenConstant.SECRET;

/**
 * @author : Starrier
 * @date 2019/3/6 0006
 * <p>
 * Description :
 */
public class TokenUtils {

    /**
     * Generate Token
     *
     * @param claims
     * @return
     */
    public static String generateToken(Map<String, Object> claims)     {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + 30000L))
                .signWith(SignatureAlgorithm.HS512, new DESCoder().toKey(SECRET))
                .compact();
    }

    /**
     * 利用jwt解析token信息.
     *
     * @param token 要解析的token信息
     * @return
     */
    private static Optional<Claims> getClaimsFromToken(String token) {
        return Optional.ofNullable(Jwts.parser()
                .setSigningKey(new DESCoder().toKey(SECRET))
                .parseClaimsJws(token)
                .getBody());
    }

    /**
     * 验证token是否过期
     *
     * @param token 要解析的token信息
     * @return
     */
    public static boolean isExpired(String token) throws Exception {
        Optional<Claims> claims = getClaimsFromToken(token);
        return claims.filter(value -> !value.getExpiration().before(new Date())).isPresent();
    }

    /**
     * 获取 token 中的参数值
     *
     * @param token 要解析的token信息
     * @return
     */
    private static Map<String, Object> extractInfo(String token)     {
        Optional<Claims> claims = getClaimsFromToken(token);
        if (claims.isPresent()) {
            Set<String> keySet = claims.get().keySet();
            Map<String, Object> info = Maps.newHashMapWithExpectedSize(keySet.size());
            keySet.forEach((@NonNull String key) -> info.put(key, claims.get().get(key)));
            return info;
        }
        return Collections.emptyMap();
    }

    /**
     * 获取 token 中的封装数据
     *
     * @param token token
     * @param key   word.
     * @return token info with key.
     */
    public static String getTokenInfoByKey(String token, String key) throws Exception {
        Map<String, Object> params = TokenUtils.extractInfo(token);
        return String.valueOf(params.get(key));
    }
}
