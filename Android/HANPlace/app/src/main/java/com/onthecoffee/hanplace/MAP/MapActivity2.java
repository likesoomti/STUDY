package com.onthecoffee.hanplace.MAP;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.onthecoffee.hanplace.CenterActivity;
import com.onthecoffee.hanplace.DOMAIN.HG_Facility;
import com.onthecoffee.hanplace.EVENT.EventListActivity;
import com.onthecoffee.hanplace.HttpClient;
import com.onthecoffee.hanplace.R;
import com.onthecoffee.hanplace.USER.MyMenuActivity;
import com.onthecoffee.hanplace.USER.QNAActivity;
import com.onthecoffee.hanplace.UTIL.MapAPI;

import java.util.concurrent.ExecutionException;

/**
 * Created by Soomti on 2017. 4. 16..
 */

public class MapActivity2 extends AppCompatActivity {
    HG_Facility data;
    TextView title;
    ImageView profile;
    TextView phone;
    TextView detail;
    TextView address;

    ImageButton menu_bar;

    Button ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map2);


        Intent intent = getIntent();
        String id = intent.getStringExtra("id");


        try
        {
            String result = new MapAPI(HttpClient.ADDRESS+"map_fac_id_one.jsp?id="+id).execute("success").get(); // 값 String 으로 받기

            JsonObject fac_json = new JsonParser().parse(result).getAsJsonObject();
            Gson gson = new Gson();

            data = gson.fromJson(fac_json, HG_Facility.class);

            Log.e("hi", "onCreate: "+data);

        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        } catch (ExecutionException e)
        {
            e.printStackTrace();
        }

        title =(TextView)findViewById(R.id.fac_title);
        profile =(ImageView)findViewById(R.id.fac_profile);
        phone = (TextView)findViewById(R.id.fac_phone);
        address = (TextView)findViewById(R.id.fac_address);
        detail = (TextView)findViewById(R.id.fac_detail);
        ok = (Button)findViewById(R.id.fac_dialogue_btn);


        title.setText(data.getFAC_name());

        detail.setText(data.getFAC_Place_detail());
        phone.setText(data.getFAC_phone());




        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        String url = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSRH987RQXBwNSZkfDGL7QI3_5Zq6QphqqECQ18O7X_fG9HueQO";

        Glide.with(this)
                .load(url)
                .into(profile);


        //action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.argb(255,255,255,255)));



    }



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
                        Intent intent = new Intent(MapActivity2.this, CenterActivity.class);
                        intent.putExtra("category_no", 0);
                        startActivity(intent);
                        finish();
                        break;

                    case R.id.map:
                        intent = new Intent(MapActivity2.this, MapActivity0.class);
                        intent.putExtra("category_no", 0);
                        startActivity(intent);
                        finish();
                        break;
                    case R.id.event:
                        intent = new Intent(MapActivity2.this, EventListActivity.class);
                        startActivity(intent);
                        finish();
                        break;
                    case R.id.mymenu:
                        intent = new Intent(MapActivity2.this, MyMenuActivity.class);
                        startActivity(intent);
                        finish();
                        Toast.makeText(MapActivity2.this, "MY MENU", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.logout:
                        Toast.makeText(MapActivity2.this, "Logout", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.chat:
                        intent = new Intent(MapActivity2.this, QNAActivity.class);
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