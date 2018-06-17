package com.yalematta.earcasts.data.remote;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by yalematta on 6/15/18.
 */

public class ApiClient {

    private static ApiClient client;
    private ApiInterface apiInterface;

    public static final String BASE_URL = "https://api.fyyd.de/0.2/";

    public ApiClient() {
        OkHttpClient client = new OkHttpClient.Builder().build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        apiInterface = retrofit.create(ApiInterface.class);
    }

    public static ApiClient getClient() {
        if (client == null) {
            client = new ApiClient();
        }
        return client;
    }

    public ApiInterface getApiInterface() {
        return apiInterface;
    }
}
