package com.fld.ckz.util;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.HashMap;

public class Test {

    private static final String C_1 = "qwe";
    private static final String C_2 = "asd";
    public static void main(String[] args) {

        //String result = "{\"success\":true,\"requestOrder\":\"710bdba490de48d2a95960c7ae5c6cb9\",\"data\":{\"id\":\"ee9fc245b2d74eaca1259f36400a8d2b\",\"accessToken\":\"71f5a4a7a0124ca0bb82657eba7ff010\",\"account\":\"test_iia99\",\"expireTime\":1550817456378}}";
//        String result = "{\"success\":false,\"code\":10000001,\"error\":\"SYSTEM_ORGANIZATION_SIGNATURE_ACCOUNT_NOT_MATCH\",\"errorDesc\":\"机构签名和机构账号不符\"}";
//        JsonObject jsonObject = new JsonParser().parse(result).getAsJsonObject();
//        try {
//            String a = jsonObject.get("data").getAsString();
//            System.out.println(a);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        System.out.println(1);
        String k = C_1;
        System.out.println(k);
        k = C_2;
        System.out.println(k);
        System.out.println("c1"+C_1);
        System.out.println("c2"+C_2);

    }
}
