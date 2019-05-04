package com.mjlife.task;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public abstract class AbstactSignTask implements Runnable {
    private static HttpClient httpClient = HttpClients.createDefault();

    protected abstract HttpUriRequest getRequest();

    protected abstract <T extends BaseSignResult> T getResult(String resultStr);

    protected abstract void record(BaseSignResult result);

    public void run() {
        try {
            HttpResponse response = httpClient.execute(getRequest());
            record(getResult(EntityUtils.toString(response.getEntity())));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
