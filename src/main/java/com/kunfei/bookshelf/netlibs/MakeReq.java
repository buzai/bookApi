package com.kunfei.bookshelf.netlibs;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Call;

import com.kunfei.bookshelf.model.analyzeRule.AnalyzeByXPath;


public class MakeReq {
    public String name;
    public MakeReq(String name2) {
        name = name2;
    }
//    public interface Call <T> extends java.lang.Cloneable {
//public interface Callback <T> {
//    void onResponse(retrofit2.Call<T> call, retrofit2.Response<T> response);
//
//    void onFailure(retrofit2.Call<T> call, java.lang.Throwable throwable);
//}
    public void mygetdata() {
//        https://so.biqusoso.com/s.php?ie=utf-8&siteid=biqugexsw.com&q=
        String url = "https://so.biqusoso.com";
        ReqClient client = new ReqClient(url, "utf-8");
        MyHttpFace face = client.createService(MyHttpFace.class);
        Call<String> call;
        call = face.get("s.php?ie=utf-8&siteid=biqugexsw.com&q=再建天宫");
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                System.out.println(response);
                if (response.isSuccessful()) {
                    System.out.println("response isSuccessful");
                    System.out.println(response.body());

                } else {
                    System.out.println("response isSuccessful not");
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                System.out.println("error");

            }
        });
    }

}
