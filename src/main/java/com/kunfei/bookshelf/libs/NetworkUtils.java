package com.kunfei.bookshelf.libs;


import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import retrofit2.Response;

public class NetworkUtils {
    public static final Pattern headerPattern = Pattern.compile("@Header:\\{.+?\\}", Pattern.CASE_INSENSITIVE);

    public static String getUrl(Response response) {
        okhttp3.Response networkResponse = response.raw().networkResponse();
        if (networkResponse != null) {
            return networkResponse.request().url().toString();
        } else {
            return response.raw().request().url().toString();
        }
    }

    /**
     * 获取绝对地址
     */
    public static String getAbsoluteURL(String baseURL, String relativePath) {
        if (TextUtils.isEmpty(relativePath)) return "";
        if (TextUtils.isEmpty(baseURL)) return relativePath;
        String header = null;
        if (StringUtils.startWithIgnoreCase(relativePath, "@header:")) {
            header = relativePath.substring(0, relativePath.indexOf("}") + 1);
            relativePath = relativePath.substring(header.length());
        }
        try {
            URL absoluteUrl = new URL(baseURL);
            URL parseUrl = new URL(absoluteUrl, relativePath);
            relativePath = parseUrl.toString();
            if (header != null) {
                relativePath = header + relativePath;
            }
            return relativePath;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return relativePath;
    }
}
