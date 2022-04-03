package com.cjf.demo;

import android.app.Application;

import com.google.gson.Gson;

import hos.core.ActivityManager;
import hos.core.AppCompat;
import hos.util.log.HiConsolePrinter;
import hos.util.log.HiLog;
import hos.util.log.HiLogConfig;
import hos.util.log.HiLogManager;

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
        ActivityManager.getInstance().init(this);
        HiLogManager.init(new HiLogConfig() {
            @Override
            public JsonParser injectJsonParser() {
                return new JsonParser() {
                    @Override
                    public String toJson(Object src) {
                        return new Gson().toJson(src);
                    }
                };
            }
        },new HiConsolePrinter());
    }
}
