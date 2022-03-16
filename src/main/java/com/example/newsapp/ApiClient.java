package com.example.newsapp;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

class ApiClient {
    private static final String BASE_URL = "https://newsapi.org/v2/";
//    private String API_KEY="66e0493d55d24452820738413133ca73";
//    private static final String BASE_URL = "https://techcrunch.com/2020/10/09/twitter-retweet-changes-quote-tweet-election-misinformation/";
//    private static final String BASE_URL = "https://newsapi.org/v2/everything?q=tesla&from=2021-09-22&sortBy=publishedAt&apiKey=66e0493d55d24452820738413133ca73/";
    private static ApiClient apiClient;
    private static Retrofit retrofit;

    private ApiClient(){
        retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
    }
    public static synchronized ApiClient getInstance(){
        if (apiClient == null){
            apiClient = new ApiClient();
        }return apiClient;
    }

    public static ApiInterface getApi(){
        return retrofit.create(ApiInterface.class);
    }
}
