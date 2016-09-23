package com.yasin.day29sharepreferences;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

/**
 * Project: com.yasin.day29sharepreferences
 * Created by Administrator
 * Date: 2016-09-06.
 */
public class LoginActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {
    public EditText mTxtName;
    public EditText mTxtPass;
    public CheckBox chbRememberPass;

    //存储方式 保存到文件中
    //存储格式 key_value
    //存储内容 不要过长，精简
    //应用场景 配置字段，用户信息
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        mTxtName = (EditText) findViewById(R.id.username);
        mTxtPass = (EditText) findViewById(R.id.password);
        SharedPreferences sp = getSharedPreferences("appdata", MODE_PRIVATE);
        chbRememberPass = (CheckBox) findViewById(R.id.remember_pass);
        boolean rp = sp.getBoolean("rememberPass", false);
        chbRememberPass.setChecked(rp);
        chbRememberPass.setOnCheckedChangeListener(this);
        if (rp) {
            String username = sp.getString("username", null);
            String password = sp.getString("password", null);
            mTxtName.setText(username);
            mTxtPass.setText(password);
        }
    }

    public void btnLogin(View view) {
        SharedPreferences sp = getSharedPreferences("appdata", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("username",mTxtName.getText().toString());
        editor.putString("password",mTxtPass.getText().toString());
        editor.apply();//api 9以上使用
       // editor.commit();

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        SharedPreferences sp = getSharedPreferences("appdata", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        if (isChecked) {
            editor.putBoolean("rememberPass",true);
        }else {
            editor.remove("rememberPass");
        }
        editor.apply();
    }
}