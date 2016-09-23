package com.yasintest;

import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static int count;
    private TextView text;
    private EditText edit;

    public MainActivity() {
        Log.d(TAG, "MainActivity: ");
    }
    /**
     * 创建
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        count++;
//        Log.d(TAG, "onCreate: " + count);
//        text = ((TextView) findViewById(R.id.main_text));
//        text.setOnClickListener(this);
//        if (savedInstanceState != null) {
//            CharSequence charSequence = savedInstanceState.getCharSequence("text");
//            text.setText(charSequence);
//        }
//        edit = (EditText) findViewById(R.id.main_edit);
  }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putCharSequence("text", text.getText());
    }

    @Override
    public void onClick(View v) {
        ((TextView) v).setText("已点击");
        Intent intent = new Intent(this, OtherActivity.class);
//        Intent intent = new Intent("com.jash.activitydemo.OTHER");
//        intent.addCategory("com.jash.activitydemo.OTHER_CATEGORY");\
//        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:123456"));
//        intent.addCategory(Intent.CATEGORY_APP_BROWSER);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("text", edit.getText());
//        Bundle bundle = new Bundle();
//        bundle.putCharSequence("text", text.getText());
//        intent.putExtras(bundle);
//        ActivityOptions options = ActivityOptions.makeScaleUpAnimation(v, v.getWidth() / 2, v.getHeight() / 2, 0, 0);
//        startActivity(intent, options.toBundle());
        ActivityOptionsCompat compat = ActivityOptionsCompat.makeScaleUpAnimation(v, v.getWidth() / 2, v.getHeight() / 2, 0, 0);
        ActivityCompat.startActivity(this, intent, compat.toBundle());
//        ActivityOptionsCompat compat = ActivityOptionsCompat.makeClipRevealAnimation(v, v.getWidth() / 2, v.getHeight() / 2, 0, 0);
//        ActivityCompat.startActivity(this, intent, compat.toBundle());

        startActivityForResult(intent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 0:
                if (resultCode == RESULT_OK) {
                    String extra = data.getStringExtra("data");
                    Toast.makeText(MainActivity.this, "返回的数据:" + extra, Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
