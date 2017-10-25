package com.onthecoffee.hanplace;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.onthecoffee.hanplace.DOMAIN.HG_User;
import com.onthecoffee.hanplace.JOIN.JoinInActivity;
import com.onthecoffee.hanplace.USER.NicknameActivity;
import com.onthecoffee.hanplace.USER.UserInfoActivity;
import com.onthecoffee.hanplace.UTIL.ConnectServer;

import com.gc.materialdesign.views.Button;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;


/**
 * Created by Soomti on 2017. 1. 11..
 */
public class LoginActivity extends FragmentActivity {

    EditText email, pw;
    TextView sign_up;
    Button loginBtn;
    LoginButton facebookBtn;
    CallbackManager callbackManager;

    private static SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = (EditText) findViewById(R.id.email);
        pw = (EditText) findViewById(R.id.pw);
        loginBtn = (Button)findViewById(R.id.loginBtn);
        sign_up = (TextView) findViewById(R.id.sign_up);

        callbackManager = CallbackManager.Factory.create();
        facebookBtn = (LoginButton) findViewById(R.id.facebookBtn);
        facebookBtn.setReadPermissions(Arrays.asList("user_status"));

        facebookBtn.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {

            @Override
            public void onSuccess(LoginResult loginResult) {

                LoginManager.getInstance().logInWithReadPermissions(LoginActivity.this, Arrays.asList("public_profile"));

                GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {

                    String email, gender, profile;
                    Intent intent;

                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {

                        try {

                            email = object.getString("email");
                            gender = object.getString("gender").substring(0,1).toUpperCase();
                            profile = "http://graph.facebook.com/" + object.getString("id") + "/picture?type=large";

                            String result = new UserInfoActivity().getUserNickname(email);
                            JSONObject user = new JSONObject(result);
                            loginSession(user.getString("user_id"));

                            if(user.getString("user_nickname").equals("null")) {

                                intent = new Intent(getApplicationContext(), NicknameActivity.class);
                                intent.putExtra("user", new HG_User(email, gender, profile));

                            } else {

                                intent = new Intent(getApplicationContext(), CenterActivity.class);

                            }

                            startActivity(intent);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                });

                Bundle parameters = new Bundle();
                parameters.putString("fields", "id, email, gender");
                request.setParameters(parameters);
                request.executeAsync();

            }

            @Override
            public void onCancel() {
                // App code
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
            }

        });

    }

    public String login(String email, String pw) {

        String result = null;

        try {

            ArrayList<String> data = new ArrayList<String>();
            data.add(HttpClient.ADDRESS+"login.jsp");
            String login = "email=" + email+ "&pw=" + pw;
            data.add(login);

            result = new ConnectServer().execute(data).get();

        } catch (Exception e) {
            Log.i("Error", "login Error");
        }

        return result;

    }

    public void btnClick(View v) {

        String result;
        Intent intent = new Intent();

        if (v.getId() == R.id.sign_up) {

            intent = new Intent(getApplicationContext(), JoinInActivity.class);
            startActivity(intent);

        } else if (v.getId() == R.id.loginBtn) {

            String emailText = email.getText().toString().trim();
            String pwText = pw.getText().toString().trim();

            if (emailText.getBytes().length > 0 && pwText.getBytes().length > 0) {

                result = login(emailText, pwText);

                if (!result.equals("0")) {

                    loginSession(result);

                    intent = new Intent(getApplicationContext(), CenterActivity.class); //메인으로 돌아가
                    startActivity(intent);
                    finish();

                } else {

                    Toast.makeText(LoginActivity.this, "아이디 또는 패스워드가 틀렸습니다.", Toast.LENGTH_SHORT).show();

                }

            } else {

                Toast.makeText(LoginActivity.this, "아이디 또는 패스워드를 입력해주세요.", Toast.LENGTH_SHORT).show();

            }

        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    public void loginSession(String user_id) {

        preferences = getSharedPreferences("login_session", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("user_id", user_id);
        editor.commit();

    }

    public void loginSessionOut() {

        preferences = getSharedPreferences("login_session", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("user_id", null);
        //editor.commit();
        editor.clear();
        editor.commit();
        finish();
    }

}