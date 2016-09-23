package com.yasin.calculatordemo;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayout;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements View.OnClickListener  {
    private static final String TAG =MainActivity.class.getSimpleName() ;
    private TextView edit;
    private TextView show;
    private StringBuilder builder;
    private int point=0;
    private int size=40;
    private LinearLayout linearLayout;
    private int linearlayout_width;
    private int edit_width=0;
    private boolean isOperator=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        popLock();
        builder = new StringBuilder();
        edit = (TextView) findViewById(R.id.edit);
        show = (TextView) findViewById(R.id.show);
        linearLayout = (LinearLayout) findViewById(R.id.l_layout);
        GridLayout layout = (GridLayout) findViewById(R.id.layout);
        for (int i = 0; i <layout.getChildCount() ; i++) {
            TextView text = (TextView) layout.getChildAt(i);
                text.setOnClickListener(this);
        }
    }
   private void popLock(){
       AlertDialog dialog=new AlertDialog.Builder(this).create();
       dialog.setTitle("请解锁");
       dialog.setMessage("1+1=?");
       dialog.setButton(DialogInterface.BUTTON_POSITIVE,"2",listener);
       dialog.setButton( DialogInterface.BUTTON_NEGATIVE,"1",listener);
       dialog.setCanceledOnTouchOutside(false);
       dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
           @Override
           public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
               if (keyCode== KeyEvent.KEYCODE_BACK) {
                   return true;
               }else {
                   return false;
               }
           }
       });
       dialog.show();

   }

    DialogInterface.OnClickListener listener=new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which) {
                case DialogInterface.BUTTON_NEGATIVE:
                    Toast toast = Toast.makeText(MainActivity.this, "真笨，这么简单都没算对！拜拜", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER,0,0);
                    toast.show();
                    MainActivity.this.finish();
                    break;
                case DialogInterface.BUTTON_POSITIVE:
                   dialog.dismiss();
                    show.setText("小二，欢迎你使用！");
                    break;
            }
        }
    };
    @Override
    public void onClick(View v) {
        linearlayout_width = linearLayout.getWidth();
        TextView text = (TextView) v;
        String str = text.getText().toString();
        switch (str) {
            case "C":
                builder.delete(0,builder.length());
                point=0;
                size=40;
                isOperator=false;
                show.setText("");
                break;
            case "←":
                if (builder.length() != 0) {
                    if (builder.charAt(builder.length()-1)=='.') {
                        point=0;
                    }
                    if (isOperator()) {
                        isOperator=false;
                    }
                    if(size<40){
                        size+=5;
                        edit.setTextSize(TypedValue.COMPLEX_UNIT_SP,size);
                    }
                    builder.deleteCharAt(builder.length()-1);
                }
                break;
            case "x":
                if (builder.length()==0) {

                }else if(!isOperator){
                    builder.append(str);
                    point=0;
                    isOperator=true;
                }

                break;
            case "÷":
                if (builder.length()==0) {

                }else if(!isOperator){
                    builder.append(str);
                    point=0;
                    isOperator=true;
                }

                break;
            case "+":
                if (builder.length()==0) {

                }else if(!isOperator){
                    builder.append(str);
                    point=0;
                    isOperator=true;
                }

                break;
            case "-":

                if(!isOperator){
                    builder.append(str);
                    point=0;
                    isOperator=true;
                }

                break;
            case ".":
                if (point==0) {
                    if (builder.length()==0||isOperator()) {
                        builder.append("0");
                    }
                    builder.append(str);
                }
                point++;
                break;
            case "=":
                if (builder.length()!=0) {
                    isOperator=false;
                    show.setText(builder.toString());
                    if (isOperator()) {
                        builder.deleteCharAt(builder.length()-1);
                    }
                    int length = builder.length();
                    Log.d(TAG, "onClick: isoperator ========="+builder);
                    Log.d(TAG, "onClick:+====++++ "+calResult(builder.toString()));
                    builder.append(calResult(builder.toString()));
                    builder.delete(0,length);

                    if (builder.toString().contains(".")) {
                        point++;
                    }

                }

                break;
           default:
               isOperator=false;
               builder.append(str);
               break;
        }
        edit.setText(builder.toString());
        edit_width = edit.getWidth();
        if (edit_width>linearlayout_width*3/4) {
            if (size==5)
            {
                size=5;
            }else {
                size-=5;
            }
        }
        edit.setTextSize(TypedValue.COMPLEX_UNIT_SP,size);

    }

    private boolean isOperator() {
        return (builder.charAt(builder.length()-1)=='+') || (builder.charAt(builder.length()-1)=='-')
                || (builder.charAt(builder.length()-1)=='x')||(builder.charAt(builder.length()-1)=='÷');
    }

    private String calResult(String str){
        StringBuilder builder=new StringBuilder();
        builder.append(str);
        Pattern pattern = Pattern.compile("([\\d.]+)([x÷])([\\d.]+)");
        Matcher matcher = pattern.matcher(str);
        float result=0;
        while (matcher.find()){
            float first = Float.parseFloat(matcher.group(1));
            float second = Float.parseFloat(matcher.group(3));
            switch (matcher.group(2)) {
                case "x":
                    result = first * second;
                    break;
                case "÷":
                    result = first / second;
                    break;
            }

            builder.replace(matcher.start(),matcher.end(),Float.toString(result));
            matcher.reset(builder.toString());
        }
        if (builder.charAt(0)=='-') {
            pattern = Pattern.compile("([-\\d.]+)([+-])([\\d.]+)");
        }else{

        pattern = Pattern.compile("([\\d.]+)([+-])([\\d.]+)");
        }
        matcher = pattern.matcher(builder.toString());
        while (matcher.find()){
            float first = Float.parseFloat(matcher.group(1));
            float second = Float.parseFloat(matcher.group(3));
            switch (matcher.group(2)) {
                case "+":
                    result = first + second;
                    break;
                case "-":
                    result = first - second;
                    break;
            }
            builder.replace(matcher.start(),matcher.end(),Float.toString(result));
            matcher.reset(builder.toString());
        }

        if (builder.charAt(builder.length()-1)=='0') {
           builder.delete(builder.length()-2,builder.length());
        }
        int index = builder.indexOf("--");
        if (index != -1) {
            builder.deleteCharAt(index);
        }
        return builder.toString();
    }



}
