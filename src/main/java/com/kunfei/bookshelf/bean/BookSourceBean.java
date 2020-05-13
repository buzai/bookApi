package com.kunfei.bookshelf.bean;

import com.sun.org.apache.xpath.internal.objects.XString;

import java.util.Map;

public interface BookSourceBean {

    String getTag();
    public String httpUserAgent = "";

    String getNoteUrl();

    void setNoteUrl(String noteUrl);

    String getVariable();

    void setVariable(String variable);

    void putVariable(String key, String value);

    Map<String, String> getVariableMap();

    public default String getHttpUserAgent() {
        return this.httpUserAgent;
    }

}
