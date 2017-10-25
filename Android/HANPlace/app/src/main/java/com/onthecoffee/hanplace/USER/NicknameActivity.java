package com.onthecoffee.hanplace.USER;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.gc.materialdesign.views.Button;
import com.onthecoffee.hanplace.CenterActivity;
import com.onthecoffee.hanplace.DOMAIN.HG_User;
import com.onthecoffee.hanplace.HttpClient;
import com.onthecoffee.hanplace.JOIN.JoinInActivity;
import com.onthecoffee.hanplace.LoginActivity;
import com.onthecoffee.hanplace.R;
import com.onthecoffee.hanplace.UTIL.ConnectServer;

import java.util.ArrayList;

/**
 * Created by BAEJJI on 2017-04-29.
 */

public class NicknameActivity extends AppCompatActivity {

    HG_User user;
    EditText nickname;
    Button checkBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_nickname);

        Intent intent = new Intent();
        Bundle bundle = getIntent().getExtras();
        user = (HG_User) bundle.getParcelable("user");
        nickname = (EditText)findViewById(R.id.nickname);
        checkBtn = (Button)findViewById(R.id.checkBtn);

    }

    public void clickBtn(View v) {

        String user_nickname = nickname.getText().toString();
        String result = null;

        try {

            ArrayList<String> data = new ArrayList<String>();
            data.add(HttpClient.ADDRESS+"setUserNickname.jsp");
            String userInfo = "nickname=" + user_nickname + "&email=" + user.getUser_email() + "&gender=" + user.getUser_gender() + "&profile=" + user.getUser_profile();
            data.add(userInfo);

            result = new ConnectServer().execute(data).get().toString();

            if(result.trim().equals("0")) {

                Toast.makeText(NicknameActivity.this, "이미 사용중인 닉네임 입니다.", Toast.LENGTH_SHORT).show();

            } else {

                new LoginActivity().loginSession(result);
                Intent intent = new Intent(getApplicationContext(), CenterActivity.class);
                startActivity(intent);

            }

        } catch (Exception e) {
            Log.i("Error", "setUserNickname Error");
        }

    }

}
