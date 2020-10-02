package io.exigence.businesscomponents.slack.validator.web;

import com.squareup.okhttp.*;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

public class WebClient {

    private OkHttpClient okHttpClient;

    public WebClient() {
        this.okHttpClient = new OkHttpClient();
    }

    public String get(String url) {
        return this.get(url, Collections.emptyMap());
    }

    public String get(String url, Map<String, String> queryParams) {
        Response response = this.getRequest(url, queryParams);
        return parseResponse(response);
    }

    private String parseResponse(Response response) {
        if (response != null) {
            try {
                ResponseBody responseBody = response.body();
                String resp = responseBody.string();
                responseBody.close();
                return resp;
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }

    private Response getRequest(String url, Map<String, String> queryParams) {
        HttpUrl.Builder builder = HttpUrl.parse(url)
                .newBuilder();
        interceptQueryParams(queryParams, builder);

        Request request = new Request.Builder()
                .url(builder.build())
                .build();
        return this.doRequest(request);
    }


    private void interceptQueryParams(Map<String, String> queryParams, HttpUrl.Builder builder) {
        queryParams.forEach(builder::addQueryParameter);
    }

    private Response doRequest(Request request) {
        try {
            return this.okHttpClient
                    .newCall(request)
                    .execute();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
