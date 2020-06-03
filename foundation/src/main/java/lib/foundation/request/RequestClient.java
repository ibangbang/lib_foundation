package lib.foundation.request;

import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * client for request.
 */
public class RequestClient {

    private static Retrofit retrofit;
    private static final int DEFAULT_TIME_OUT = 10;

    //default init
    public static void init(String baseUrl) {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.NONE);

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS);//连接 超时时间
        builder.writeTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS);//写操作 超时时间
        builder.readTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS);//读操作 超时时间
        builder.retryOnConnectionFailure(true);//错误重连

        builder.addInterceptor(logging);

        // 初始化Retrofit
        retrofit = new Retrofit.Builder()
                .client(builder.build())
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
    }

    /**
     * builder pattern
     */
    public class Builder {
        String baseUrl;
        OkHttpClient.Builder builder;
        List<Interceptor> interceptorList;
        CallAdapter.Factory callFactory;
        Converter.Factory converterFactory;

        public void setBaseUrl(String baseUrl) {
            this.baseUrl = baseUrl;
        }

        public void setOkHttpClientBuilder(OkHttpClient.Builder builder) {
            this.builder = builder;
        }

        public void addInterceptors(Interceptor... interceptors) {
            if (interceptorList == null) {
                interceptorList = new ArrayList<>();
            }
            if (interceptors.length > 0)
                interceptorList.addAll(Arrays.asList(interceptors));
        }

        public void setCallFactory(CallAdapter.Factory callFactory) {
            this.callFactory = callFactory;
        }

        public void setConverterFactory(Converter.Factory converterFactory) {
            this.converterFactory = converterFactory;
        }

        public void build() {

            if(baseUrl == null){
                Logger.e("set baseUrl first !!!");
            }

            if(builder == null){
                builder = new OkHttpClient.Builder();
                builder.connectTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS);//连接 超时时间
                builder.writeTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS);//写操作 超时时间
                builder.readTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS);//读操作 超时时间
                builder.retryOnConnectionFailure(true);//错误重连
            }

            if(interceptorList!=null){
                for (Interceptor interceptor : interceptorList){
                    builder.addInterceptor(interceptor);
                }
            }

            if(callFactory == null){
                callFactory = RxJava2CallAdapterFactory.create();
            }

            if(converterFactory == null){
                converterFactory = JacksonConverterFactory.create();
            }

            retrofit = new Retrofit.Builder()
                    .client(builder.build())
                    .baseUrl(baseUrl)
                    .addCallAdapterFactory(callFactory)
                    .addConverterFactory(converterFactory)
                    .build();
        }
    }

    public static <T> T create(Class<T> service) {
        return retrofit.create(service);
    }

}
