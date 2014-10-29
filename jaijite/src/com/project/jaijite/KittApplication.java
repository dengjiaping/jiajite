
package com.project.jaijite;

import android.app.Application;
import android.content.Context;

public class KittApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getBaseContext();
    }

    public static Context getContext() {
        return context;
    }
}
