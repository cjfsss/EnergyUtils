package com.cjf.demo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;

import com.cjf.demo.databinding.ActivityMainBinding;

import hos.util.cache.StorageFile;
import hos.util.cache.StorageSp;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());
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
                String value = StorageFile.getCache("file");
                Toast.makeText(MainActivity.this, "结果：" + value, Toast.LENGTH_SHORT).show();
            }
        });
        activityMainBinding.btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long start = System.currentTimeMillis();
                User user = new User(System.currentTimeMillis(), "张三", "12345678", false);
                boolean isSuccess = StorageFile.saveCache("user", user);
                long end = System.currentTimeMillis();
                Log.i("TAG", "time: " + (end - start));
                Toast.makeText(MainActivity.this, "是否成功：" + isSuccess, Toast.LENGTH_SHORT).show();
            }
        });
        activityMainBinding.btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long start = System.currentTimeMillis();
                User value = StorageFile.getCache("user");
                long end = System.currentTimeMillis();
                Log.i("TAG", "time: " + (end - start));
                if (value != null) {
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
                User value = StorageSp.getCache("userSP");
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
    }
}