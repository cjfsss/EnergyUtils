package com.cjf.demo;

import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.cjf.demo.databinding.ActivityMainBinding;

import hos.cache.StorageFile;
import hos.cache.StorageSp;
import hos.location.LocationSource;
import hos.location.LocationSourceAndroidData;
import hos.location.LocationSourceAndroidDataCache;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding activityMainBinding;

    private LocationSource locationSource = null;
    private LocationSource.Location location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());
        locationSource = new LocationSourceAndroidDataCache(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
//            FpsMonitor.toggle();
        }
        activityMainBinding.btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isSuccess = StorageFile.saveCache("file", "张三");
                Toast.makeText(MainActivity.this, "是否成功：" + isSuccess, Toast.LENGTH_SHORT).show();
            }
        });
        activityMainBinding.btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value = StorageFile.getData("file");
                Toast.makeText(MainActivity.this, "结果：" + value, Toast.LENGTH_SHORT).show();
            }
        });
        activityMainBinding.btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long start = System.currentTimeMillis();
                User user = new User(System.currentTimeMillis(), "张三", "12345678", false);
                if (location == null) {
                    location = locationSource.getLocation();
                }
                boolean isSuccess = StorageFile.saveCache("location", location);
                if (location instanceof LocationSourceAndroidData.LocationData) {
                    StorageFile.saveCache("androidLocation", (Parcelable) ((LocationSourceAndroidData.LocationData) location).getLocation());
                }
                long end = System.currentTimeMillis();
                Log.i("TAG", "time: " + (end - start));
                Toast.makeText(MainActivity.this, "是否成功：" + isSuccess, Toast.LENGTH_SHORT).show();
            }
        });
        activityMainBinding.btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long start = System.currentTimeMillis();
                LocationSource.Location value = StorageFile.getData("location", LocationSource.Location.CREATOR);
                long end = System.currentTimeMillis();
                Log.i("TAG", "time: " + (end - start));
                if (value != null) {
                    android.location.Location androidLocation = StorageFile.getData("androidLocation", android.location.Location.CREATOR);
                    if (androidLocation == null) {
                        value = new LocationSourceAndroidData.LocationData(value);
                    } else {
                        value = new LocationSourceAndroidData.LocationData(value, androidLocation);
                    }
                    Toast.makeText(MainActivity.this, "结果：" + value.toString(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "结果：失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
        activityMainBinding.btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long start = System.currentTimeMillis();
                User user = new User(System.currentTimeMillis(), "李四", "12345678", false);
                boolean isSuccess = StorageSp.saveCache("userSP", user);
                long end = System.currentTimeMillis();
                Log.i("TAG", "time: " + (end - start));
                Toast.makeText(MainActivity.this, "是否成功：" + isSuccess, Toast.LENGTH_SHORT).show();
            }
        });
        activityMainBinding.btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long start = System.currentTimeMillis();
                User value = StorageSp.getData("userSP");
                long end = System.currentTimeMillis();
                Log.i("TAG", "time: " + (end - start));
                if (value != null) {
                    Toast.makeText(MainActivity.this, "结果：" + value.toString(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "结果：失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
        activityMainBinding.btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long start = System.currentTimeMillis();
                boolean clearSp = StorageSp.clear();
                boolean clearFile = StorageFile.clear();
                long end = System.currentTimeMillis();
                Log.i("TAG", "time: " + (end - start));
                Toast.makeText(MainActivity.this, "结果：sp:" + clearSp + " file:" + clearFile, Toast.LENGTH_SHORT).show();
            }
        });
        locationSource.addLocationChangedListener(new LocationSource.LocationChangedListener() {
            @Override
            public void locationChanged(LocationSource.LocationChangedEvent locationChangedEvent) {
                location = locationChangedEvent.getLocation();
            }
        });
        locationSource.startLocation();
    }

    @Override
    protected void onDestroy() {
        locationSource.stopLocation();
        super.onDestroy();
    }
}