package com.fld.ckz.test.token;

import com.fld.ckz.core.Cache;
import com.fld.ckz.util.CacheManager;
import com.fld.ckz.util.OkHttpUtil;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.HashMap;
import java.util.Map;

/**
 * 通过OkHttp POST方式,获取accessToken
 */
public class GetTokenByOkHttp {
    /**
     * 企业账号
     */
    private static final String ACCOUNT = "test_iia99";
    /**
     * 企业签名
     */
    private static final String SIGNATURE = "54c6cdd75d1443ccbd4153edc28f434a";
    /**
     * 获取accessToken正式环境路径
     */
    private static final String URL = "http://tianxingshuke.com/api/rest/common/organization/auth";
    /**
     * 获取accessToken测试环境路径
     */
    private static final String TEST_URL = "http://test.tianxingshuke.com/api/rest/common/organization/auth";
    /**
     * 失效时间24小时
     */
    private static final Long EFFECTIVE_TIME = 24 * 60 * 60 * 1000L;

    public static void main(String[] args) {
        // 通过okHttp的方式获得授权码
        System.out.println(getAccessTokenByOkHttp());
    }

    /**
     * 通过okhttp发送post请求获取token值
     *
     * @return 授权码
     */
    public static String getAccessTokenByOkHttp() {
        Cache cache = null;
        cache = CacheManager.getCacheInfo("TX_accessToken");
        String accessToken = "71f5a4a7a0124ca0bb82657eba7ff010";

//        if (cache != null && !cache.isExpired()) {//缓存已存在，未过期
//            accessToken = (String) cache.getValue();
//            System.out.println("通过缓存取值");
//        } else {
//            //拼接请求体
//            Map<String, String> params = new HashMap<>(4);
//            params.put("account", ACCOUNT);
//            params.put("signature", SIGNATURE);
//            //拼接请求头
//            Map<String, String> headers = new HashMap<>(4);
//            headers.put("Content-Type", "application/json;charset=UTF-8");
//            // 若是测试环境则需要把TEST_URL替换成URL
//            String result = OkHttpUtil.postMethodHeader(TEST_URL, params, headers);
//            JsonObject jsonObject = new JsonParser().parse(result).getAsJsonObject();
//            if (jsonObject.get("success").getAsBoolean()) {
//                accessToken = jsonObject.get("data").getAsJsonObject().get("accessToken").getAsString();
//            } else {
//                //
//            }
//            System.out.println("通过接口取值");
//        }

        return accessToken;

        //71f5a4a7a0124ca0bb82657eba7ff010    2019-2-21 14:41:42    2019-2-22 14:41:48
        //"{\"success\":true,\"requestOrder\":\"710bdba490de48d2a95960c7ae5c6cb9\",\"data\":{\"id\":\"ee9fc245b2d74eaca1259f36400a8d2b\",\"accessToken\":\"71f5a4a7a0124ca0bb82657eba7ff010\",\"account\":\"test_iia99\",\"expireTime\":1550817456378}}";

    }
}
