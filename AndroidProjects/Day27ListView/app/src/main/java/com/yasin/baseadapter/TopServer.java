package com.yasin.baseadapter;

import com.yasin.asnyc.NetworkTask;
import com.yasin.networklibrary.UrlString;

/**
 * Project: com.yasin.baseadapter
 * Created by Administrator
 * Date: 2016-09-02.
 */
public interface TopServer {
    @UrlString("http://www.tngou.net/api/top/list?id=%d&page=%d&rows=%d")
    NetworkTask<Result> getList(long id,int page,int rows);
}
