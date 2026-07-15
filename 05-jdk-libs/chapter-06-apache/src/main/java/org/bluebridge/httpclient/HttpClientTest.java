package org.bluebridge.httpclient;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;

/**
 * HttpClient工具类测试
 *
 * @author lingwh
 * @date 2019/6/20 15:48
 */
@Slf4j
public class HttpClientTest {

    /**
     * 测试HttpClient发送不带有参数的GET请求
     *
     * @throws IOException
     */
    @Test
    public void httpGet() throws IOException {
        // 创建Httpclient对象
        CloseableHttpClient httpclient = HttpClients.createDefault();
        // 创建http GET请求
        HttpGet httpGet = new HttpGet("http://www.baidu.com");
        CloseableHttpResponse response = null;
        try {
            // 执行请求
            response = httpclient.execute(httpGet);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                // 请求体内容
                String content = EntityUtils.toString(response.getEntity(), "UTF-8");
                log.info("content: {}", content);
            }
        } finally {
            if (response != null) {
                response.close();
            }
            // 相当于关闭浏览器
            httpclient.close();
        }
    }
}
