package com.onthecoffee.hanplace.JOIN;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.gc.materialdesign.views.Button;
import com.onthecoffee.hanplace.CenterActivity;
import com.onthecoffee.hanplace.EVENT.EventListActivity;
import com.onthecoffee.hanplace.HttpClient;
import com.onthecoffee.hanplace.R;
import com.onthecoffee.hanplace.UTIL.ConnectServer;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by BAEJJI on 2017-04-12.
 */

public class JoinInActivity extends AppCompatActivity {
    EditText email, pw, pwCheck, nickname;
    RadioGroup radioGroup;
    RadioButton sex;
    Button nextBtn;
    //CircleImageView profile;
    ImageView profile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        email = (EditText)findViewById(R.id.email);
        pw = (EditText)findViewById(R.id.pw);
        pwCheck = (EditText)findViewById(R.id.pwCheck);
        nickname = (EditText)findViewById(R.id.nickname);
        radioGroup = (RadioGroup)findViewById(R.id.sex);
        nextBtn = (Button)findViewById(R.id.nextBtn);
        profile = (ImageView)findViewById(R.id.profile);

        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build()); //activity_main 수정 시 필요
        //"https://cdn0.iconfinder.com/data/icons/kameleon-free-pack-rounded/110/Man-1-128.png"
        Bitmap image = new EventListActivity().getEventImage("https://cdn0.iconfinder.com/data/icons/kameleon-free-pack-rounded/110/Man-1-128.png");

        profile.setImageBitmap(image);

    }

    //next 버튼 들어옴
    public String checkUserInfo(String user_email, String nickname) {

        String result = null;

        try {

            ArrayList<String> data = new ArrayList<String>();
            data.add(HttpClient.ADDRESS+"checkUserInfo.jsp");

            String param = "email=" + user_email + "&nickname=" + nickname;
            data.add(param);

            result =  new ConnectServer().execute(data).get();

        }catch (Exception e) {
            Log.i("Error", "checkUserInfo Error");
        }

        return result;

    }

    //  next 버튼 이후
    public void newUser(String email, String pw, String nickname, String gender) {

        try {

            ArrayList<String> data = new ArrayList<String>();
            data.add(HttpClient.ADDRESS+"newUser.jsp");
            String param = "email=" + email +"&password=" + pw + "&nickname=" + nickname + "&gender=" + gender;
            data.add(param);

            String result =  new ConnectServer().execute(data).get();

        }catch (Exception e) {
            Log.i("Error", "checkUserInfo Error");
        }

    }

    public void clickable(View v) {

        String user_email = email.getText().toString();
        String user_pw = pw.getText().toString();
        String user_pwCheck = pwCheck.getText().toString();
        String user_nickname = nickname.getText().toString();
        sex = (RadioButton)findViewById(radioGroup.getCheckedRadioButtonId());

        if(v.getId() == R.id.nextBtn) {

            if(user_email!=null && user_pw!=null && user_pwCheck!=null && sex!=null) {

                boolean isEqual = user_pw.equals(user_pwCheck);

                if(isEqual) {

                    String result = checkUserInfo(user_email, user_nickname).trim();

                    if(result.equals("notExist")) {

                        String user_gender = sex.getText().toString().substring(0,1).toUpperCase();
                        newUser(user_email, user_pw, user_nickname, user_gender);
                        Intent intent = new Intent(getApplicationContext(), CenterActivity.class);
                        startActivity(intent);

                    } else if(result.equals("email")) {

                        Toast.makeText(JoinInActivity.this, "이미 사용중인 이메일 입니다.", Toast.LENGTH_SHORT).show();

                    } else if(result.equals("nickname")) {

                        Toast.makeText(JoinInActivity.this, "이미 사용중인 닉네임 입니다.", Toast.LENGTH_SHORT).show();

                    }

                } else {

                    Toast.makeText(JoinInActivity.this, "비밀번호 확인이 올바르지 않습니다.", Toast.LENGTH_SHORT).show();

                }

            } else {

                Toast.makeText(JoinInActivity.this, "모든 항목을 작성해주세요.", Toast.LENGTH_SHORT).show();

            }

        } else if(v.getId() == R.id.profile) {

            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType(android.provider.MediaStore.Images.Media.CONTENT_TYPE);
            startActivityForResult(intent, 1);

        }

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        Uri imageUri = data.getData();
        String[] filePathColumn = {MediaStore.Images.Media.DATA};
        Cursor imageCursor = this.getContentResolver().query(imageUri, filePathColumn, null, null, null);
        imageCursor.moveToFirst();

        try {

            Bitmap selectedImage = MediaStore.Images.Media.getBitmap(this.getContentResolver(),imageUri);
            profile.setImageBitmap(selectedImage);

        } catch (Exception e) {
            Toast.makeText(this, " error!", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }

    }

}