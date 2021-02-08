package org.starrier.common.utils.request;

import org.apache.http.util.Asserts;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author starrier
 * @date 2021/1/25
 */
class HttpsUtilsTest {

    @Test
    void sendPostWithJson() {
    }

    @Test
    void sendGetWithHeader() {
    }

    @Test
    void sendPost() throws IOException {

        String starrierRepos = HttpsUtils.sendPost("https://api.github.com/users/Starrier/repos");
        Asserts.notNull(starrierRepos,"not null");
    }
}