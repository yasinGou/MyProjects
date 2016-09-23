package com.yasin.day30moneyusage;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.yasin.day30moneyusage.database.DbHelper;

public class EditActivity extends AppCompatActivity {

    public EditText txtMoeny;
    public EditText texType;
    public SQLiteDatabase database;
    private long recordId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        txtMoeny = (EditText) findViewById(R.id.txt_money);
        texType = (EditText) findViewById(R.id.txt_type);
        DbHelper dbHelper = new DbHelper(this);
        database = dbHelper.getWritableDatabase();
        recordId=getIntent().getLongExtra("recordId",-1);
        if (recordId!=-1) {
            Cursor cursor = database.query("cost", null, "_id=?", new String[]{Long.toString(recordId)}, null, null, null);
            if (cursor != null) {
                //只有一条数据
                if (cursor.moveToNext()) {
                    int index = cursor.getColumnIndex("money");
                    if (index!=-1) {
                        double money = cursor.getDouble(index);
                        txtMoeny.setText(Double.toString(money));
                    }

                    index = cursor.getColumnIndex("type");
                    if (index!=-1) {
                        int type = cursor.getInt(index);
                        texType.setText(Integer.toString(type));
                    }
                }
                cursor.close();
            }
        }
    }

    public void btnSave(View view) {
        //更新数据
        double money=0;
        int type=0;
        String str_money = txtMoeny.getText().toString();
        String str_type = texType.getText().toString();
        money=Double.parseDouble(str_money);
        type=Integer.parseInt(str_type);
        if (recordId!=-1) {
            ContentValues values = new ContentValues();
            values.put("money",money);
            values.put("type",type);
            int num = database.update("cost", values, "_id=?", new String[]{Long.toString(recordId)});
            if (num>0) {
                //更新成功
                finish();
            }else {

            }

        }
    }

    @Override
    protected void onDestroy() {
        database.close();
        database=null;
        super.onDestroy();
    }
}
