package com.yasin.day30moneyusage.database;

import android.provider.BaseColumns;

/**
 * Project: com.yasin.day30moneyusage
 * Created by Administrator
 * Date: 2016-09-07.
 */
public class DataContracts {
    //标准的谷歌官方写法
    //创建一个类，一个表作为一个静态内部类，字段名，表名声明为静态常量。

    public static class Cost implements BaseColumns {
        public static final String TABLE_NAME="cost";
        public static final String MOENY="moeny";
    }
}
