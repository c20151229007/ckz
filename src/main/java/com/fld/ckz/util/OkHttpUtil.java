package com.fld.ckz.util;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.util.Map;
import java.util.concurrent.TimeUnit;


/**
 * OkHttp方法
 * 查询pom网址为 http://mvnrepository.com/
 * 引用pom包为
 * <dependency>
 * <groupId>com.squareup.okhttp</groupId>
 * <artifactId>okhttp</artifactId>
 * <version>2.7.5</version>
 * </dependency>
 */
public class OkHttpUtil {

    private static OkHttpClient client = new OkHttpClient();

    static {
        // 超时配置
        client.setConnectTimeout(30, TimeUnit.SECONDS);
        client.setReadTimeout(30, TimeUnit.SECONDS);
    }

    /**
     * get方法带头信息
     *
     * @param url     路径
     * @param headers 请求头
     * @return 返回结果
     */
    public static Response getMethodHeader(String url, Map<String, String> headers) {
        Long start = System.currentTimeMillis();
        Response response = null;
        try {
            Request request;
            Request.Builder builder = new Request.Builder();
            for (Map.Entry<String, String> entity : headers.entrySet()) {
                builder.header(entity.getKey(), entity.getValue());
            }
            request = builder.url(url).get().build();
            response = client.newCall(request).execute();
        } catch (Exception e) {
            System.out.println("调用异常:url:" + url);
            e.printStackTrace();
        } finally {
            Long end = System.currentTimeMillis();
            System.out.println("开始时间：" + start + " 结束时间：" + end + " 此次调用时长为" + (end - start));
        }
        return response;
    }


    /**
     * post方法带头信息
     *
     * @param url     路径
     * @param headers 请求头
     * @return 返回结果
     */
    public static String postMethodHeader(String url, Map<String, String> param, Map<String, String> headers) {
        Long start = System.currentTimeMillis();
        String result = null;
        try {
            RequestBody formBody = getFormEncodingBuilder(param).build();
            Request request;
            Request.Builder builder = new Request.Builder();
            for (Map.Entry<String, String> entity : headers.entrySet()) {
                builder.header(entity.getKey(), entity.getValue());
            }
            request = builder.url(url).post(formBody).build();
            result = client.newCall(request).execute().body().string();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("调用异常:url:" + url);
        } finally {
            Long end = System.currentTimeMillis();
            System.out.println("开始时间：" + start + " 结束时间：" + end + " 此次调用时长为" + (end - start));
        }
        return result;
    }

    /**
     * post方法提供参数
     *
     * @param param 参数列表
     * @return FormEncodingBuilder
     */
    private static FormEncodingBuilder getFormEncodingBuilder(Map<String, String> param) {
        FormEncodingBuilder formEncodingBuilder = new FormEncodingBuilder();
        for (Map.Entry<String, String> entry : param.entrySet()) {
            formEncodingBuilder.add(entry.getKey(), entry.getValue());
        }
        return formEncodingBuilder;
    }
}
