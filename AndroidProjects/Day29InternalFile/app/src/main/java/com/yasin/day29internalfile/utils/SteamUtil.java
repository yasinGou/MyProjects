package com.yasin.day29internalfile.utils;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * Project: com.yasin.day29internalfile.utils
 * Created by Administrator
 * Date: 2016-09-06.
 */
public class SteamUtil {
    private SteamUtil(){}
    public static byte[] readStream(InputStream in){
        byte[] ret=null;
        return ret;
    }
    public static long readStream(InputStream in, OutputStream out){
        long ret=0;
        if (in!=null&&out!=null) {
            byte[] buf=new byte[1024];
            int len;
            while (true){
            len=in.read(buf);
            if (len==-1) {
                break;
            }
            out.write(buf,0,len);
            ret+=len;
        }
        }
        return ret;
    }
 }
