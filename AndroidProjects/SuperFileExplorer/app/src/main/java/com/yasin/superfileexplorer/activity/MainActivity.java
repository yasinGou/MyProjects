package com.yasin.superfileexplorer.activity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.yasin.superfileexplorer.R;
import com.yasin.superfileexplorer.adapter.FileListAdapter;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {
    private List<File> mfiles;
    public FileListAdapter adapter;
    public File file = new File("/");
    private AlertDialog.Builder builder;
    private Activity context = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = (ListView) findViewById(R.id.file_list);
        mfiles = new ArrayList<>();
        //获取手机/目录下面的所有内容
        File[] files = getSubFiles(new File("/"));
//        String parent = new File("/").getParent();
//        Toast.makeText(MainActivity.this, ""+parent, Toast.LENGTH_SHORT).show();
        mfiles.addAll(sort(Arrays.asList(files)));
        adapter = new FileListAdapter(this, mfiles);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        listView.setOnItemLongClickListener(this);
        builder = new AlertDialog.Builder(this);
        builder.setTitle("提示");
        builder.setMessage("是否退出超级浏览器");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                Intent intent = new Intent(this, SetPassActivity.class);
                intent.putExtra("title", "请设置启动密码");
                startActivity(intent);
                finish();
                break;
        }
        return true;
    }

    private static File[] getSubFiles(File dir) {
        File[] ret = null;
        if (dir != null && dir.isDirectory()) {
            ret = dir.listFiles();
        }
        return ret;
    }

    public static List<File> sort(List<File> list) {

        Collections.sort(list, new Comparator<File>() {
            @Override
            public int compare(File lhs, File rhs) {
                if (lhs.isDirectory() && rhs.isDirectory()) {

                    return lhs.getName().compareToIgnoreCase(rhs.getName());

                } else if (lhs.isDirectory() && rhs.isFile()) {

                    return -1;

                } else if (lhs.isFile() && rhs.isDirectory()) {

                    return 1;

                } else if (lhs.isFile() && rhs.isFile()) {
                    return lhs.getName().compareToIgnoreCase(rhs.getName());
                } else {
                    return 1;
                }


            }
        });

        return list;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        file = mfiles.get(position);
        if (file.isDirectory()) {
            File[] subFiles = getSubFiles(file);
            if (subFiles != null) {
                mfiles.clear();
                mfiles.addAll(sort(Arrays.asList(subFiles)));
                adapter.notifyDataSetChanged();
            } else {
                mfiles.clear();
                adapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void onBackPressed() {
        //      super.onBackPressed();
        File parentFile = file.getParentFile();
        if (parentFile != null) {
            File[] subFiles = getSubFiles(parentFile);
            if (subFiles != null) {
                mfiles.clear();
                mfiles.addAll(sort(Arrays.asList(subFiles)));
                adapter.notifyDataSetChanged();
                file = file.getParentFile();
            }
        } else {
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ActivityCompat.finishAffinity(context);
                }
            });
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            AlertDialog dialog = builder.create();
            dialog.setCancelable(false);
            dialog.show();
        }

    }


    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
       // Log.d("gouyazhou", "onLongClick: ");
        //当返回值为true时 点击事件不被触发，只处理长按事件。
        return true;
    }
}

