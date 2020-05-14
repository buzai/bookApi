package com.kunfei.bookshelf;
import com.kunfei.bookshelf.bean.SearchBookBean;
import com.kunfei.bookshelf.libs.NetworkUtils;
import com.kunfei.bookshelf.libs.StringUtils;
import com.kunfei.bookshelf.libs.EncodeConverter;
import com.kunfei.bookshelf.libs.SSLSocketClient;
import com.kunfei.bookshelf.libs.StringUtils;
import com.kunfei.bookshelf.libs.TextUtils;
import com.kunfei.bookshelf.netlibs.MakeReq;


import com.kunfei.bookshelf.model.analyzeRule.AnalyzeUrl;
import com.kunfei.bookshelf.model.impl.IHttpGetApi;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import okhttp3.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;


@RestController
public class hello {
    private static OkHttpClient httpClient;

    /**
     * 分解URL
     */
    private void generateUrlPath(String ruleUrl) {
//        url = NetworkUtils.getAbsoluteURL(baseUrl, ruleUrl);
//        host = StringUtils.getBaseUrl(url);
//        urlPath = url.substring(host.length());
    }

    public Retrofit getRetrofitString(String url, String encode) {
        return new Retrofit.Builder().baseUrl(url)
                //增加返回值为字符串的支持(以实体类返回)
                .addConverterFactory(EncodeConverter.create(encode))
                //增加返回值为Observable<T>的支持
                .client(getClient())
                .build();
    }

    synchronized public static OkHttpClient getClient() {
        if (httpClient == null) {
            httpClient = new OkHttpClient.Builder()
                    .connectTimeout(15, TimeUnit.SECONDS)
                    .writeTimeout(15, TimeUnit.SECONDS)
                    .readTimeout(15, TimeUnit.SECONDS)
                    .retryOnConnectionFailure(true)
                    .sslSocketFactory(SSLSocketClient.getSSLSocketFactory(), SSLSocketClient.createTrustAllManager())
                    .hostnameVerifier(SSLSocketClient.getHostnameVerifier())
                    .protocols(Collections.singletonList(Protocol.HTTP_1_1))
                    .addInterceptor(getHeaderInterceptor())
                    .build();
        }
        return httpClient;
    }

    private static Interceptor getHeaderInterceptor() {
        return chain -> {
            Request request = chain.request()
                    .newBuilder()
                    .addHeader("Keep-Alive", "300")
                    .addHeader("Connection", "Keep-Alive")
                    .addHeader("Cache-Control", "no-cache")
                    .build();
            return chain.proceed(request);
        };
    }



    private Map<String, String> headerMap = new HashMap<>();

    public static Observable<List<String>> analyzeSearchBook(final Response<String> response) {
        return Observable.create(e -> {
            String baseUrl;
            baseUrl = NetworkUtils.getUrl(response);
            String body = response.body();

            if (TextUtils.isEmpty(body)) {
                System.out.println("获取搜索结果 error");
                return;
            } else {
                System.out.println("成功获取搜索结果");
                System.out.println(body);
            }
            List<String> person = new ArrayList<>();
            e.onNext(person);
            e.onComplete();
        });
    }

    private void getRequest() {
        //1.创建OkHttpClient对象
        OkHttpClient okHttpClient = new OkHttpClient();
        //2.创建Request对象，设置一个url地址（百度地址）,设置请求方式。
        Request request = new Request.Builder().url("http://novel.acg.gg/modules/article/search.php@searchkey=额头轻触").method("GET",null).build();
        //3.创建一个call对象,参数就是Request请求对象
        Call call = okHttpClient.newCall(request);
        //4.请求加入调度，重写回调方法
        call.enqueue(new Callback() {
            //请求失败执行的方法
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, okhttp3.Response response) throws IOException {
                String data = response.body().string();
                System.out.println("response");
                System.out.println(data);

            }

        });
    }

    @RequestMapping("/books/search")
    public String index() throws Exception {
        MakeReq req = new MakeReq("request");
        req.mygetdata();
        return "Greetings from Spring Boot!";
    }

}
