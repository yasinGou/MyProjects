package com.yasin.endcalldemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.test.mock.MockApplication;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText phoneNum;
    private MyApplication application;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        phoneNum = (EditText) findViewById(R.id.phoneNum);
        Button ok = (Button) findViewById(R.id.ok);
        ok.setOnClickListener(this);
        application = (MyApplication) getApplication();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ok:
                String num = phoneNum.getText().toString();
                application.setTelphoneNum(num);
                Toast.makeText(MainActivity.this, ""+application.getTelphoneNum(), Toast.LENGTH_SHORT).show();
                break;
        }

    }
}
