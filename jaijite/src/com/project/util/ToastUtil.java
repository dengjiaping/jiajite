
package com.project.util;

import android.content.Context;
import android.widget.Toast;

import com.project.jaijite.KittApplication;


public class ToastUtil {

    public static void showLongToast(String message) {
        showToast(message, Toast.LENGTH_LONG);
    }

    public static void showShortToast(String message) {
        showToast(message, Toast.LENGTH_SHORT);
    }

    /**
     * @Description:
     * @param message
     * @param duration
     */
    private static void showToast(String message, int duration) {
        Context context = KittApplication.getContext();
        Toast.makeText(context, message, duration).show();
    }

}
