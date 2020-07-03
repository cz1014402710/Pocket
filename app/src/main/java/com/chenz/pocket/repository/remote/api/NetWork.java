package com.chenz.pocket.repository.remote.api;

import android.util.Log;


import com.chenz.pocket.app.MyApplication;
import com.chenz.pocket.utils.NetworkUtil;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by chenz on 2017/3/30.
 */
public class NetWork {

    private static final int DEFAULT_TIMEOUT = 5;

    private static Retrofit retrofit;

    private static APIService mAPIService;

    private static NetWork mNetworks;

    public static NetWork getInstance() {
        if (mNetworks == null) {
            mNetworks = new NetWork();
        }
        return mNetworks;
    }

    public APIService getAPIService() {
        return mAPIService == null ? configRetrofit(APIService.class) : mAPIService;
    }

    private APIService configRetrofit(Class service) {
        retrofit = new Retrofit.Builder()
                .baseUrl(APIService.API_BASE_URL)
                .client(configClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return (APIService) retrofit.create(service);
    }

    Interceptor mInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            return chain.proceed(chain.request().newBuilder()
                    .addHeader("Content-Type", "application/json")
                    .build());
        }
    };

    private OkHttpClient configClient() {

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                //打印retrofit日志
                Log.i("RetrofitLog", "retrofitBack = " + message);
            }
        });
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        File cacheFile = new File(MyApplication.APP_CONTEXT.getCacheDir(), "cache");
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 100); //100Mb

        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(1000 * 15, TimeUnit.MILLISECONDS)
                .writeTimeout(1000 * 60, TimeUnit.MILLISECONDS)
                .readTimeout(1000 * 60, TimeUnit.MILLISECONDS)
//                .addInterceptor(mInterceptor)
                .addInterceptor(loggingInterceptor)
                .addNetworkInterceptor(new HttpCacheInterceptor())
                .cache(cache);
        return okHttpClient.build();
    }

    class HttpCacheInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();

            if (!NetworkUtil.isNetConnected(MyApplication.APP_CONTEXT)) {
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build();
                Log.d("Okhttp", "no network");
            }

            Response originalResponse = chain.proceed(request);
            if (NetworkUtil.isNetConnected(MyApplication.APP_CONTEXT)) {
                //有网的时候读接口上的@Headers里的配置，你可以在这里进行统一的设置
                String cacheControl = request.cacheControl().toString();
                return originalResponse.newBuilder()
                        .header("Cache-Control", cacheControl)
                        .removeHeader("Pragma")
                        .build();
            } else {
                return originalResponse.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=2419200")
                        .removeHeader("Pragma")
                        .build();
            }
        }
    }
}
