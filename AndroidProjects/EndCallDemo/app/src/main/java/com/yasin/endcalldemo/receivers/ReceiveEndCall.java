package com.yasin.endcalldemo.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.media.AudioManager;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.android.internal.telephony.ITelephony;
import com.yasin.endcalldemo.MyApplication;

import java.lang.reflect.Method;

public class ReceiveEndCall extends BroadcastReceiver {
    public String telphoneNum;
    public ReceiveEndCall() {

    }

    @Override
    public void onReceive(Context context, Intent intent) {
        AudioManager mAudioManager=(AudioManager)context.getSystemService(Context.AUDIO_SERVICE);
       // BlockList b=new BlockList(context);Context context1 = context.getApplicationContext();
        MyApplication application = (MyApplication) context.getApplicationContext();
        String telphoneNum = application.getTelphoneNum();
        Log.d("Receiver", "onReceive: "+telphoneNum);
        if(intent.getAction().equals(Intent.ACTION_NEW_OUTGOING_CALL)){

            //如果是去电（拨出）
            String num=getResultData();

            if(num.equals("15736873273")){
                setResultData(null); //清除电话
           //     break;
            }


        }else{ //由于android没有来电广播所以，去掉拨打电话就是来电状态了
            String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
            String number = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
            if(state.equalsIgnoreCase(TelephonyManager.EXTRA_STATE_RINGING)){
                if(number.equals("15736873273")){//拦截指定的电话号码
                    //先静音处理
                    mAudioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
                    try {
                        /* //挂断电话   方法一
                         Method method = Class.forName(
                                "android.os.ServiceManager").getMethod(
                                "getService", String.class);
                            // 获取远程TELEPHONY_SERVICE的IBinder对象的代理
                            IBinder binder = (IBinder) method.invoke(null,
                                new Object[] { Context.TELEPHONY_SERVICE });
                            // 将IBinder对象的代理转换为ITelephony对象
                            ITelephony telephony = ITelephony.Stub
                                .asInterface(binder);
                            // 挂断电话
                            telephony.endCall();  Log.e("msg", "end"); */
                        //挂断电话   方法二
                        ITelephony  iTelephony = getITelephony(context); //获取电话接口
                        iTelephony.endCall(); // 挂断电话
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    //再恢复正常铃声
                    mAudioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
                  //  break;
                }

            }
        }
    }
    /**
     * 根据反射获取end()方法2
     * @param context
     * @return
     */
    private static ITelephony getITelephony(Context context) {
        ITelephony iTelephony=null;
        TelephonyManager mTelephonyManager = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        Class<TelephonyManager> c = TelephonyManager.class;
        Method getITelephonyMethod = null;
        try {
            getITelephonyMethod = c.getDeclaredMethod("getITelephony",
                    (Class[]) null); // 获取声明的方法
            getITelephonyMethod.setAccessible(true);
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        try {
            iTelephony = (ITelephony) getITelephonyMethod.invoke(
                    mTelephonyManager, (Object[]) null); // 获取实例
            return iTelephony;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return iTelephony;

}
}
