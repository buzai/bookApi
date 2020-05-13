package com.kunfei.bookshelf.netlibs;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import com.kunfei.bookshelf.libs.EncodeConverter;



public class ReqClient {
    public String baseUrl = "https://api.weibo.com/2/";
    public String encode = "gbk";
    public Retrofit.Builder retrofitBuilder;

    ReqClient(String baseUrl1, String encode1) {
        baseUrl = baseUrl1;
        encode = encode1;
        retrofitBuilder = new Retrofit.Builder()
                        .baseUrl(this.baseUrl)
                        .addConverterFactory(EncodeConverter.create(this.encode));
    }

    private static OkHttpClient.Builder okHttpClientBuilder =
            new OkHttpClient.Builder();

    public <T> T createService(Class<T> serviceClass) {
        retrofitBuilder.client(okHttpClientBuilder.build());
        Retrofit retrofit = retrofitBuilder.build();
        return retrofit.create(serviceClass);
    }

}
