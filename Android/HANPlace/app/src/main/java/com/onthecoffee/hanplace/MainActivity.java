package com.onthecoffee.hanplace;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MainActivity extends AppCompatActivity {

    private Handler mHandler;
    private Runnable mRunnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.real_activity_main);

        mRunnable = new Runnable()
        {
            @Override
            public void run()
            {
                Intent intent = new Intent(getApplicationContext()
                        , LoginActivity.class);
                startActivity(intent);
            }
        };

        mHandler = new Handler();
        mHandler.postDelayed(mRunnable, 5000);

    }

    @Override
    protected void onDestroy() {
        Log.i("test", "onDstory()");
        mHandler.removeCallbacks(mRunnable);
        super.onDestroy();
    }


    //setContentView(R.layout.activity_main);
    // test
        /*
        Button btn = (Button)findViewById(R.id.gogomap);
        Button btn2 = (Button)findViewById(R.id.gogodb);
        Button btn3 = (Button)findViewById(R.id.gogoevent);
        Button btn4 = (Button)findViewById(R.id.gogojoin);
        Button btn5 = (Button)findViewById(R.id.gogopracticefragment);
        Button btn6 = (Button)findViewById(R.id.gogologin);
        Button btn7 = (Button)findViewById(R.id.list);

        Button signup = (Button)findViewById(R.id.centerActivity);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, MapActivity.class); // 두번째 액티비티를 실행하기 위한 인텐트
                startActivity(intent); // 두번째 액티비티를 실행합니다.

            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, DatabaseActivity.class);
                startActivity(intent);
                finish(); //인텐트 종료~, 인텐트는 이동해도 그전 class가 켜져있으므로 꺼줍니다
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EventListActivity.class);
                startActivity(intent);
                finish(); //인텐트 종료~, 인텐트는 이동해도 그전 class가 켜져있으므로 꺼줍니다
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, JoinInActivity.class);
                startActivity(intent);
                finish();
            }
        });


        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MapActivity0.class);
                startActivity(intent);
                finish();
            }
        });

        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();

            }
        });


        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, StaggeredGridActivityFragment.class);
                startActivity(intent);
                finish();
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, CenterActivity.class);
                startActivity(intent);
                finish();

            }
        });
        */
}
