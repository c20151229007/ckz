package com.fld.ckz.test.result;

import com.fld.ckz.test.token.GetTokenByOkHttp;
import com.fld.ckz.util.OkHttpUtil;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GetResultByOkHttp {
    /**
     * 正式环境路径（根据不同产品需要修改不同的路径）
     */
    private static final String BASE_URL = "https://tianxingshuke.com/api/rest/";
    /**
     * 测试环境路径（根据不同产品需要修改不同的路径）
     */
    private static final String TEST_BASE_URL = "https://test.tianxingshuke.com/api/rest/";
    /**
     * 账号
     */
    private static final String ACCOUNT = "test_iia99";
    /**
     * 路径拼接符号
     */
    private static final String AND_MARK = "&";
    private static final String EQUAL_MARK = "=";
    /**
     * 授权码过期或无效返回代码
     */
    private static final Integer HTTP_CODE = 401;
    /**
     * 银联四要素验证
     */
    private static final String YLSYS = "unionpay/auth/4element";
    /**
     * 手机号在网时长查询(三大运营商)
     */
    private static final String ZWSC = "operators/mobile/online";
    /**
     * 个人身份验证
     */
    private static final String SFYZ = "police/identity/validation";
    /**
     * 运营商三要素验证（三大运营商）
     */
    private static final String YYSSYS = "operators/auth/3element";

    public static void main(String[] args) {
        // 根据不同的接口传入的参数不同，此处需要根据接口文档入参进行填写
        String follow_url = SFYZ;
        Map<String, String> params = new HashMap<>(16);
        params.put("name", "许日康");
        params.put("idCard", "460003197804052815");
        params.put("mockType", "EXIST");
        // 通过okHttp发送请求
        System.out.println(getResultByOkHttp(params,follow_url));
    }

    /**
     * 发送okHttp请求获得json结果
     *
     * @param params 参数
     * @return json结果
     */
    private static String getResultByOkHttp(Map<String, String> params,String follow_url) {
        String result = null;
        // token并非每次都需要获取，可将未过期的token重复使用
        String token = getTokenByOkHttp();
        Response response = getResponseByOkHttp(params, token,follow_url);
        try {
            if (response.isSuccessful()) {
                result = response.body().string();
            } else {
                //如果token过期,重新获取token
                if (response.code() == HTTP_CODE) {
                    //设置缓存过期

                    token = getTokenByOkHttp();
                    response = getResponseByOkHttp(params, token,follow_url);
                    result = response.body().string();
                } else {
                    System.out.println("实际返回code为：" + response.code());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 发送okHttp请求获得Response
     *
     * @param params      参数
     * @param accessToken 授权码
     * @return 响应结果
     */
    private static Response getResponseByOkHttp(Map<String, String> params, String accessToken, String follow_url) {
        String url = TEST_BASE_URL + follow_url + "?account=" + ACCOUNT + "&accessToken=" + accessToken + getParams(params);
        System.out.println(url);
        Map<String, String> headers = new HashMap<>(4);
        headers.put("Content-Type", "application/json;charset=UTF-8");
        return OkHttpUtil.getMethodHeader(url, headers);

    }

    /**
     * 获取token
     * token有效时间为第一次获取后24小时内有效，并非每次都需要获取
     *
     * @return accessToken
     */
    private static String getTokenByOkHttp() {
        //获取token信息
        return GetTokenByOkHttp.getAccessTokenByOkHttp();
    }

    /**
     * 转换为get方式的参数
     *
     * @param param map类型的参数
     * @return String
     */
    private static String getParams(Map<String, String> param) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<String, String> entry : param.entrySet()) {
            stringBuilder.append(AND_MARK).append(entry.getKey()).append(EQUAL_MARK).append(entry.getValue());
        }
        return stringBuilder.toString();
    }


}