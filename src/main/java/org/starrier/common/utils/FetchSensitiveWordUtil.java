package org.starrier.common.utils;

import com.google.common.collect.Maps;
import lombok.Cleanup;
import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import static org.starrier.common.constant.Constant.UTF8;

/**
 * @author Starrier
 * @date 2018/12/13.
 */
public class FetchSensitiveWordUtil {

    /**
     * @param filePath {@link String}
     * @return {@link Map<Integer,String> }
     */
    @SneakyThrows(IOException.class)
    public Map<Integer, String> fetchSensitiveWords(final String filePath) {
        Map<Integer, String> wordsMaps = Maps.newHashMap();
        File file = new File(filePath);
        int count = 0;
        if (file.isFile() && file.exists()) {
            @Cleanup InputStreamReader reader = new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8);
            try(BufferedReader bufferedReader = new BufferedReader(reader)){
                String lineText = null;
                while ((lineText = bufferedReader.readLine()) != null) {
                    if (!"".equals(lineText)) {
                        String readers = lineText.split("\\+")[0];
                        wordsMaps.put(count, readers);
                        count++;
                    }
                }
            }
        }
        return wordsMaps;
    }

}
