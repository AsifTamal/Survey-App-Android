package com.v2tech.asifulislam.surveyapp.api;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import io.reactivex.plugins.RxJavaPlugins;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Conn {

    private static final String BASE_URL =  "https://example-response.herokuapp.com/";
    private static Conn mConnecion;
    private Retrofit retrofit;

    public Conn() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
        String format = simpleDateFormat.format(new Date());
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(
                        new Interceptor() {
                            @Override
                            public Response intercept(Chain chain) throws IOException {
                                Request original = chain.request();

                                Request.Builder requestBuilder = original.newBuilder()
                                     .addHeader("Timestamp", format)
                                        .method(original.method(), original.body());

                                Request request = requestBuilder.build();
                                return chain.proceed(request);
                            }
                        }
                ).build();


        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }

    public static synchronized Conn getRetroConnecion() {
        if (mConnecion == null) {
            mConnecion  =new Conn();
        }
        return mConnecion;
    }

    public  iApiurls getApiService()
    {

        return retrofit.create(iApiurls.class);


    }
}
