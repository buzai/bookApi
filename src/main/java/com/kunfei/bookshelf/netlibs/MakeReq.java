package com.kunfei.bookshelf.netlibs;
import com.kunfei.bookshelf.bean.SearchBookBean;
import com.kunfei.bookshelf.model.analyzeRule.AnalyzeRule;
import org.seimicrawler.xpath.JXNode;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Call;

import com.kunfei.bookshelf.model.analyzeRule.AnalyzeByXPath;

import java.util.List;
import com.kunfei.bookshelf.libs.StringUtils;


public class MakeReq {
    public String name;
    public MakeReq(String name2) {
        name = name2;
    }
    public static void getItemInList(AnalyzeRule analyzer) {
        String bookName = null;
        try {
            System.out.println("call bookName");

            bookName = StringUtils.formatHtml(analyzer.getString("class.s2@tag.a@text"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("bookName");

        System.out.println(bookName);
    }

    public static void log(String str) {
        System.out.println(str);
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
                    List<Object> collections = null;
                    AnalyzeRule analyzer = new AnalyzeRule(null);
                    analyzer.setContent(response.body(), url);
                    try {
                        collections = analyzer.getElements("class.search-list@tag.li!0");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println(collections.size());
                    for (int i = 0; i < collections.size(); i++) {
                        Object object = collections.get(i);
                        analyzer.setContent(object, url);
                        getItemInList(analyzer);
                    }
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
