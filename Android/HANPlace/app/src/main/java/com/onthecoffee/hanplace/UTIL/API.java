package com.onthecoffee.hanplace.UTIL;
import android.app.Application;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
/**
 * Created by Soomti on 2017. 4. 12..
 */



class App extends Application
{
    public static Gson gson;

    @Override
    //자주쓰이는 변수들을 초기화한다
    public void onCreate() {
        super.onCreate();

        gson = new GsonBuilder().create();




    }


}
