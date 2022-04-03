package com.cjf.demo;

import android.app.Application;

import hos.core.AppCompat;

/**
 * <p>Title: App </p>
 * <p>Description:  </p>
 * <p>Company: www.mapuni.com </p>
 *
 * @author : 蔡俊峰
 * @version : 1.0
 * @date : 2022/4/3 10:17
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AppCompat.getInstance().init(this);
    }
}
