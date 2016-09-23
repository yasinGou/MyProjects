package com.yasin.baseadapter;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Project: com.yasin.baseadapter
 * Created by Administrator
 * Date: 2016-09-02.
 */
public class Result {
    @SerializedName("total")
    private String total;
    @SerializedName("tngou")
    private List<Entry> entries;

    public List<Entry> getEntries() {
        return entries;
    }

    public void setEntries(List<Entry> entries) {
        this.entries = entries;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
