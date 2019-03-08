package com.fld.ckz.controller;

import com.fld.ckz.core.Cache;
import com.fld.ckz.util.CacheManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KzhController {

    @RequestMapping(method = RequestMethod.GET,value = "kz",produces = "application/json;charset=UTF-8")
    public String sg(String name){
        String result = null;
        Cache cache = null;
        cache = CacheManager.getCacheInfo("name");

        if (cache != null && !cache.isExpired()) {//缓存已存在，未过期
            result = (String) cache.getValue();
        } else {
            CacheManager.putCacheInfo("name" , name ,1*60*1000L);
            cache = CacheManager.getCacheInfo("name");
            result = (String) cache.getValue();
        }
        return result;
    }
}
