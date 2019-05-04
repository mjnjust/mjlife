package com.mjlife.task;

import com.alibaba.fastjson.JSON;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.springframework.stereotype.Component;

@Component
public class CovacsSignTask extends AbstactSignTask {
    protected HttpUriRequest getRequest() {
        String SIGNURL = "https://ecosystem-api.ecovacs.cn/v2/private/fb8c08c2de2f43119cb0d85b8401787f/eco_a/2.6.7/c_iphone/2/user/sign?accessToken=c98e14e376fb0bc57001a93490f32759&authAppkey=Z3N1MQ8wDQYDVQQHDAZzdXpo&authSign=6173ce294b9605929fd53f3c86425dca&authTimespan=20180916115425&requestId=7c6a17960a4ed7250bac2c07eff3ad43&uid=1642070";
        HttpGet get = new HttpGet(SIGNURL);
        return get;
    }

    protected CovacsSignResult getResult(String resultStr) {
        return JSON.parseObject(resultStr, CovacsSignResult.class);
    }

    protected void record(BaseSignResult signResult) {
        CovacsSignResult result = (CovacsSignResult) signResult;
        System.out.println(result.getCode());
        System.out.println(result.getMsg());
    }
}