package com.jt.test;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * @author Yuanzhibx
 * @Date 2020-07-22
 */
public class TestHttpClient {

    /**
     * 利用httpClient机制 访问百度服务器       http://www.baidu.com
     * 实现步骤:
     * 		1.定义请求网址
     * 		2.定义httpClient工具API对象
     * 		3.定义请求的类型
     * 		4.发起请求,获取返回值结果
     * 		5.校验返回值
     * 		6.获取返回值结果数据
     */
    @Test
    public void testGet() throws IOException {
        String url = "http://blog.yanbingxu.com/";
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = httpClient.execute(httpGet);
        //发起请求之后 需要判断返回值结果是否正确  一般条件下判断响应状态码信息是否为200.
        //404 400 提交参数异常  406 接收参数异常 500 服务器异常  504  超时   200正常
        int status = response.getStatusLine().getStatusCode();
        if (status == 200) {
            HttpEntity httpEntity = response.getEntity();
            String result = EntityUtils.toString(httpEntity, "UTF-8");
            System.out.println(result);
        }
    }

}
