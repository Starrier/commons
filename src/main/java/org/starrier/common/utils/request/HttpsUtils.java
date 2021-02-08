package org.starrier.common.utils.request;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.util.Strings;

import java.io.IOException;
import java.util.Map;

/**
 * @author starrier
 * @date 2021/1/25
 */
public class HttpsUtils {

    /**
     * HTTPS post request using JSON transport
     *
     * @param url  target https url
     * @param json transport content with json
     * @return request result
     * @throws IOException throws exception
     */
    public static String sendPostWithJson(String url, String json) throws IOException {

        HttpPost post = new HttpPost(url);

        // send a JSON data
        post.setEntity(new StringEntity(json));

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(post)) {
            return EntityUtils.toString(response.getEntity());
        }

    }

    /**
     *
     * @param url
     * @param headers
     * @return
     * @throws IOException
     */
    public static String sendGetWithHeader(String url, Map<String, String> headers) throws IOException {

        HttpGet request = new HttpGet(url);

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(request)) {

            // add request headers
            if (MapUtils.isNotEmpty(headers)) {
                for (String key : headers.keySet()) {
                    if (StringUtils.isNotBlank(headers.get(key))) {
                        request.addHeader(key, headers.get(key));
                    }
                }
            }

            HttpEntity entity = response.getEntity();
            if (entity != null) {
                return EntityUtils.toString(entity);
            }

            return Strings.EMPTY;
        }

    }

    /**
     *
     * add request parameters or form parameters
     * <blockquote><pre>
     *         List<NameValuePair> urlParameters = new ArrayList<>();
     *         urlParameters.add(new BasicNameValuePair("username", "abc"));
     *         urlParameters.add(new BasicNameValuePair("password", "123"));
     *         urlParameters.add(new BasicNameValuePair("custom", "secret"));
     *
     *         post.setEntity(new UrlEncodedFormEntity(urlParameters));
     * </pre></blockquote>
     * @param url
     * @return
     * @throws IOException
     */
    public static String sendPost(String url) throws IOException {

        HttpPost post = new HttpPost(url);

        try (CloseableHttpClient httpClient = HttpClients.createDefault();

             CloseableHttpResponse response = httpClient.execute(post)){

           return EntityUtils.toString(response.getEntity());
        }

    }
}
