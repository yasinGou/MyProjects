package com.yasin.networklibrary;

import com.yasin.asnyc.NetworkTask;

/**
 * Project: com.yasin.networklibrary
 * Created by Administrator
 * Date: 2016-08-31.
 */
public interface NetIml {
//   @UrlString("http://www.tngou.net/api/top/show?id=%d")
    @UrlString("http://www.tngou.net/api/cook/show?id=%d")
    NetworkTask<Menu> getShow(int id);

}
