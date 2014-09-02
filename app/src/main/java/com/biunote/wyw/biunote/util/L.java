package com.biunote.wyw.biunote.util;

import android.content.Context;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;

/**
 * Created by wang on 2014/9/2.
 *
 *
 */
public class L {
    public static void toast(Context context,String msg){
        Toast.makeText(context, msg,Toast.LENGTH_LONG).show();
    }

    public  static  void v(String msg){
        LogUtils.LOGV(TAG,msg);
    }

    public  static  void d(String msg){
        LogUtils.LOGD(TAG,msg);
    }

    /**
     * write log to file.
     * @param log
     */
    public static void writeLog(byte log[]){

    }

    /**
     * 字符集的测试,在乱码状态下使用
     * @param datastr
     * <p>传入需要测试的字符串</p>
     * */
    public static void testCharset(String datastr){
        try {
            String temp = new String(datastr.getBytes(), "GBK");
            L.v("****** getBytes() -> GBK ******\n"+temp);

            temp = new String(datastr.getBytes("GBK"), "UTF-8");
            L.v("****** GBK -> UTF-8 *******\n"+temp);

            temp = new String(datastr.getBytes("GBK"), "ISO-8859-1");
            L.v("****** GBK -> ISO-8859-1 *******\n"+temp);

            temp = new String(datastr.getBytes("ISO-8859-1"), "UTF-8");
            L.v("****** ISO-8859-1 -> UTF-8 *******\n"+temp);

            temp = new String(datastr.getBytes("ISO-8859-1"), "GBK");
            L.v("****** ISO-8859-1 -> GBK *******\n"+temp);

            temp = new String(datastr.getBytes("UTF-8"), "GBK");
            L.v("****** UTF-8 -> GBK *******\n"+temp);

            temp = new String(datastr.getBytes("UTF-8"), "ISO-8859-1");
            L.v("****** UTF-8 -> ISO-8859-1 *******\n"+temp);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private static final String TAG = "BiuNote";
}
