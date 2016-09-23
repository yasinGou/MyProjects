package com.yasin.dialogdemo;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public ArrayList<String> objects;
    public ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("提示");
        builder.setMessage("确定要退出吗？");
        builder.setPositiveButton("退出", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.setNegativeButton("在逛逛！", new DialogInterface.OnClickListener() {
            /**
             *
             * @param dialog
             * @param which  代表哪一个按钮被点击了
             */
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //对话框隐藏
                dialog.dismiss();
            }
        });
        builder.setNeutralButton("再想想", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "hello", Toast.LENGTH_SHORT).setGravity(Gravity.CENTER,0,0);

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();

    }


    public void BtnSelectGender(View view) {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("选择性别");
        builder.setItems(new String[]{"男", "女", "其他"}, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void BtnSelectHobby(View view) {
        objects = new ArrayList<>();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        boolean[] checed={true,false,false};
        builder.setTitle("爱好");
        builder.setMultiChoiceItems(new String[]{"电影", "音乐", "篮球"}, checed, new DialogInterface.OnMultiChoiceClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                Toast.makeText(MainActivity.this, which+"  "+isChecked, Toast.LENGTH_SHORT).show();
            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    /**
     * 显示加载进度对话框，并且使用单例模式
     * @param view
     */
    public void btnShowProgress(View view) {
        if (dialog==null) {
            dialog = new ProgressDialog(this);
            dialog.setTitle("加载中");
            dialog.setMessage("正在加载");
            //为对话框设置style
            dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            //设置进度是否是"不确切的" . true,显示进度不断加载。false，显示具体进度
            dialog.setIndeterminate(false);
        }
        dialog.show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    dialog.setProgress(i);
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        //在线程中执行，改变进度。
    //    dialog.setProgress(50);
    }


    @Override
    protected void onDestroy() {
        dialog=null;
        super.onDestroy();
    }
}
