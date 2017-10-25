package com.onthecoffee.hanplace.USER;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.onthecoffee.hanplace.HttpClient;
import com.onthecoffee.hanplace.UTIL.ConnectServer;

import java.util.ArrayList;

/**
 * Created by BAEJJI on 2017-04-29.
 */

public class UserInfoActivity extends AppCompatActivity {

    public String getUserNickname(String email) {

        String result = null;

        try {

            ArrayList<String> data = new ArrayList<String>();
            data.add(HttpClient.ADDRESS+"userNickname.jsp");
            String login = "email=" + email;
            data.add(login);

            result = new ConnectServer().execute(data).get();

        } catch (Exception e) {
            Log.i("Error", "getUserNickname Error");
        }

        return result;

    }

}