package com.yasin.superfileexplorer.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.yasin.superfileexplorer.R;

public class StartActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_activity);
        editPass = (EditText) findViewById(R.id.editPass);
        Button ok = (Button) findViewById(R.id.ok);
        ok.setOnClickListener(this);
       
    }

    @Override
    public void onClick(View v) {
        SharedPreferences sp = getSharedPreferences("data", MODE_PRIVATE);
        String correctPass = sp.getString("password", null);
        String password = editPass.getText().toString();
        if (correctPass != null) {
            if (password.equals(correctPass)) {
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
            }else {
                Toast.makeText(StartActivity.this, "密码错误，请重新输入密码", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
