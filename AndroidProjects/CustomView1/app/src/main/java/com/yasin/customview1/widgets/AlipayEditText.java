package com.yasin.customview1.widgets;

import android.content.Context;
import android.text.InputFilter;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

import com.yasin.customview1.R;

/**
 * Project: com.yasin.customview1.widgets
 * Created by Yasin
 * Date: 2016-09-28.
 */
public class AlipayEditText extends EditText {

    //在代码中手动创建的时候，调用这个方法
    public AlipayEditText(Context context) {
        super(context);
    }
    //在布局中使用控件时候，自动调用这个方法
    //布局里的属性，由AttributeSet来获取
    public AlipayEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    /**
     *当文本变化的时候回调
     * @param text   内容
     * @param start  开始修改的位置
     * @param lengthBefore  修改内容之前的长度
     * @param lengthAfter 修改内容之后的长度
     */
    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {


        Log.d("Alipay", "onTextChanged: "+start+"  "+lengthBefore+"  "+lengthAfter);
//        if (text.length()==1) {
//            View focus = focusSearch(FOCUS_RIGHT);
//            if (focus != null) {
//                clearFocus();//清除自身的焦点
//                focus.requestFocus();//找到的控件，获取新的焦点
//            }
//        }else {
//            //删除当前内容
//            View view = focusSearch(FOCUS_LEFT);
//            if (view != null) {
//                clearFocus();
//                view.requestFocus();
//            }
//        }
    }

//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        boolean ret=false;
//        //当按键是退格键 删除是处理
//        if (keyCode== KeyEvent.KEYCODE_DEL) {
//            if (getText().length()==0) {
//                clearFocus();
//                View view = focusSearch(FOCUS_LEFT);
//                if (view != null) {
//                    view.requestFocus();
//                }
//                ret=true;
//            }
//        }
//        return ret;
//    }
}
