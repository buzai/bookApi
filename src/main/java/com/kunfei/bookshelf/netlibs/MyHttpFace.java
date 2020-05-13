package com.kunfei.bookshelf.netlibs;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;
import java.util.Map;


/**
 * Created by GKF on 2018/1/21.
 * get web content
 */

public interface MyHttpFace {
    @GET
//    Call <Response> get(@Url String url, @HeaderMap Map<String, String> headers);
    Call <String> get(@Url String url);

    @GET
    Call <String> getMap(@Url String url,
                           @QueryMap(encoded = true) Map<String, String> queryMap,
                           @HeaderMap Map<String, String> headers);
}
