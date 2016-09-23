package com.yasintest;

import android.content.Intent;
import android.os.Process;
import android.os.SystemClock;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class OtherActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = OtherActivity.class.getSimpleName();
    private long time;
    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_other);
//
//        text = (TextView) findViewById(R.id.other_text);
//        text.setOnClickListener(this);
//        CharSequence chars = getIntent().getCharSequenceExtra("text");
//        text.setText(chars);
    }
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d(TAG, "onNewIntent: ");
        CharSequence charSequence = intent.getCharSequenceExtra("text");
        text.setText(charSequence);
    }
    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        intent.putExtra("data", "OtherActivity返回");
        setResult(RESULT_OK, intent);
        finish();
//        Intent intent = new Intent(this, ThreeActivity.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        startActivity(intent);
    }
//    @Override
//    public void onBackPressed() {
////        super.onBackPressed();
//        if (SystemClock.uptimeMillis() - time < 3000){
////            finishAffinity();
//            ActivityCompat.finishAffinity(this);
////            System.exit(0);
////            Process.killProcess(Process.myPid());
//        } else {
//            Toast.makeText(OtherActivity.this, "再点一次退出", Toast.LENGTH_SHORT).show();
//            time = SystemClock.uptimeMillis();
//        }
//    }
}
