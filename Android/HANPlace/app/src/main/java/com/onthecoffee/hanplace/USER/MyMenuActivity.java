package com.onthecoffee.hanplace.USER;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gc.materialdesign.views.Button;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.onthecoffee.hanplace.CenterActivity;
import com.onthecoffee.hanplace.DOMAIN.HG_Facility;
import com.onthecoffee.hanplace.DOMAIN.HG_User;
import com.onthecoffee.hanplace.EVENT.EventListActivity;
import com.onthecoffee.hanplace.HttpClient;
import com.onthecoffee.hanplace.MAP.MapActivity;
import com.onthecoffee.hanplace.MAP.MapActivity0;
import com.onthecoffee.hanplace.R;
import com.onthecoffee.hanplace.UTIL.ConnectServer;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Soomti on 2017. 5. 20..
 */


public class MyMenuActivity extends AppCompatActivity{

//actionbar

    TextView title;
    ImageButton menu_bar;


//사진 업로드 몰라서 안했음 사진 업데이트 하게 만들어야함

    ImageView profile;
    TextView nickname;
    TextView introduce;

    HG_User user;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mymenu);


        //actionbar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.argb(255,255,255,255)));

        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());

        profile = (ImageView) findViewById(R.id.mymenu_profile);
        nickname = (TextView)findViewById(R.id.mymenu_nickname);
        introduce = (TextView)findViewById(R.id.mymenu_introduce);
        btn = (Button)findViewById(R.id.mymenu_update);

        ArrayList<String> data = new ArrayList<String>();
        String result = null;
        try {

            data.add(HttpClient.ADDRESS + "/my_menu.jsp");
            SharedPreferences preferences = getSharedPreferences("login_session", MODE_PRIVATE);
            String login = "id=" + preferences.getString("user_id", "");
            data.add(login);

            result = new ConnectServer().execute(data).get();

        }catch (Exception e){

        }finally {

        }
        Gson gson = new Gson();
        JsonObject fac_json = new JsonParser().parse(result).getAsJsonObject();
        user = gson.fromJson(fac_json, HG_User.class);

        //??
        Bitmap user_profile = new EventListActivity().getEventImage(user.getUser_profile());
        Log.i("profile", user.getUser_profile().toString());
        profile.setImageBitmap(user_profile);
        nickname.setText(user.getUser_nickname());
        introduce.setText(user.getUser_introduce());


        btn.setOnClickListener(update_btn);

        profile.setOnClickListener(profile_click);
    }

    Button.OnClickListener update_btn = new View.OnClickListener() {
        public void onClick(View v) {
            ArrayList<String> data = new ArrayList<String>();
            try {
                data.add(HttpClient.ADDRESS + "/my_menu_update.jsp");
                String query = "id=" +user.getUser_id()+"&nickname="+nickname.getText()+"&intro="+introduce.getText();
                data.add(query);

                Log.e("data", "onClick: "+data);
                new ConnectServer().execute(data).get();

            }catch (Exception e){

            }finally {

            }
            Intent intent = new Intent(MyMenuActivity.this, CenterActivity.class);
            startActivity(intent);
            finish();

        }

    };

    ImageView.OnClickListener profile_click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(MyMenuActivity.this, "click", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        ActionBar actionBar = getSupportActionBar();

        // Custom Actionbar를 사용하기 위해 CustomEnabled을 true 시키고 필요 없는 것은 false 시킨다
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(false);            //액션바 아이콘을 업 네비게이션 형태로 표시합니다.
        actionBar.setDisplayShowTitleEnabled(false);        //액션바에 표시되는 제목의 표시유무를 설정합니다.
        actionBar.setDisplayShowHomeEnabled(false);            //홈 아이콘을 숨김처리합니다.


        //layout을 가지고 와서 actionbar에 포팅을 시킵니다.
        LayoutInflater inflater = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
        View actionbar = inflater.inflate(R.layout.actionbar_main, null);
        title = (TextView)actionbar.findViewById(R.id.title);
        title.setText("Han Place");

        menu_bar = (ImageButton)actionbar.findViewById(R.id.menu_bar);
        menu_bar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                popupMenu(v);

                Toast.makeText(MyMenuActivity.this, "SETTING", Toast.LENGTH_SHORT).show();
            }
        });
        actionBar.setCustomView(actionbar);



        //액션바 양쪽 공백 없애기
        Toolbar parent = (Toolbar)actionbar.getParent();
        parent.setContentInsetsAbsolute(0,0);

        return true;
    }

    public void popupMenu(View v){
        PopupMenu popup = new PopupMenu(this, v);//v는 클릭된 뷰를 의미
        getMenuInflater().inflate(R.menu.main_menu, popup.getMenu());

        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {//눌러진 MenuItem의 Item Id를 얻어와 식별

                    case R.id.home:
                        Intent intent = new Intent(MyMenuActivity.this, CenterActivity.class);
                        intent.putExtra("category_no", 0);
                        startActivity(intent);
                        finish();
                        break;

                    case R.id.map:
                        intent = new Intent(MyMenuActivity.this, MapActivity0.class);
                        intent.putExtra("category_no", 0);
                        startActivity(intent);
                        finish();
                        break;
                    case R.id.event:
                        intent = new Intent(MyMenuActivity.this, EventListActivity.class);
                        startActivity(intent);
                        finish();
                        break;
                    case R.id.mymenu:
                        break;
                    case R.id.logout:
                        Toast.makeText(MyMenuActivity.this, "Logout", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.chat:
                        intent = new Intent(MyMenuActivity.this, QNAActivity.class);
                        startActivity(intent);
                        finish();
                        break;
                }
                return false;
            }
        });
        popup.show();//Popup Menu 보이기
    }

}