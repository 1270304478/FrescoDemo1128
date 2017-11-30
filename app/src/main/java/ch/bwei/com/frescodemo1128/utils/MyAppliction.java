package ch.bwei.com.frescodemo1128.utils;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * 此类的作用：
 *
 * @author: forever
 * @date: 2017/11/28 8:45
 */
public class MyAppliction extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);

    }
}
