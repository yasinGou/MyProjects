package com.yasin.day30moneyusage.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Project: com.yasin.day30moneyusage.database
 * Created by Administrator
 * Date: 2016-09-07.
 */

/**
 * 用于自动创建数据库，并且根据指定的参数能够进行数据库的更新操作，全部可自动处理
 * Oncreat onUpgrade
 */
public class DbHelper extends SQLiteOpenHelper {

    public static final String CREAT_TABLE_COST = "create table cost (" +
            "_id integer primary key autoincrement," +
            "money double not null default 0," +
            "use_time long," +
            "type integer" +
            ") ";
    public static final String CREAT_TABLE_INCOME="create table income(_id integer primary key autoincrement," +
            "money double not null default 0,)";
    public DbHelper(Context context) {
        //context:用于在内部存储区创建数据库文件
        //参数4：版本号，当前程序在代码中设置的版本信息，
        //这个版本号应该是程序中最新的数据库版本
        //类自动检测数据库文件版本，不一致就升级
        super(context, "myapp", null, 2);
        //    context.getDatabasePath()
    }

    /**
     * 当第一次使用数据库，并且数据库文件不存在时，调用此方法。创建数据库表
     *当数据库存在时，不再调用此方法
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        //如果数据库表需要显示在listview,那么必须包含_id字段
        db.execSQL(CREAT_TABLE_COST);
        db.execSQL(CREAT_TABLE_INCOME);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //更新版本
        if (newVersion==3) {
            db.execSQL(CREAT_TABLE_INCOME);
        }

        db.execSQL("alert table income add colum type integar default 0 ");
    }
}
