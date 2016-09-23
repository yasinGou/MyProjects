package com.yasin.superfileexplorer.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.yasin.superfileexplorer.R;

/**
 * Project: com.yasin.superfileexplorer.activity
 * Created by Administrator
 * Date: 2016-09-06.
 */
public class SetPassActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText firstEdit;
    private EditText secondEdit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_activity);
        TextView title = (TextView) findViewById(R.id.title);
        String extra = getIntent().getStringExtra("title");
        if (extra != null) {
            title.setText(extra);
        }
        firstEdit = (EditText) findViewById(R.id.first_pass);
        secondEdit = (EditText) findViewById(R.id.second_pass);
        Button ok = (Button) findViewById(R.id.set_ok);
        ok.setOnClickListener(this);

}

    @Override
    public void onClick(View v) {
        String firstPass = firstEdit.getText().toString();
        String secondPass = secondEdit.getText().toString();
        if (secondPass.equals(firstPass)) {
            SharedPreferences sp = getSharedPreferences("data", MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("password", firstPass.toString());
            editor.putBoolean("isFirst", false);
            editor.apply();
            Toast.makeText(SetPassActivity.this, "密码设置成功", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, StartActivity.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(SetPassActivity.this, "密码输入不一致", Toast.LENGTH_SHORT).show();
        }

    }
}
