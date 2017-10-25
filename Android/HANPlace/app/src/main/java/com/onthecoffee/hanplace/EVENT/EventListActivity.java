package com.onthecoffee.hanplace.EVENT;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.onthecoffee.hanplace.CenterActivity;
import com.onthecoffee.hanplace.HttpClient;
import com.onthecoffee.hanplace.DOMAIN.HG_Event;
import com.onthecoffee.hanplace.MAP.MapActivity;
import com.onthecoffee.hanplace.MAP.MapActivity0;
import com.onthecoffee.hanplace.R;
import com.onthecoffee.hanplace.USER.MyMenuActivity;
import com.onthecoffee.hanplace.USER.QNAActivity;
import com.onthecoffee.hanplace.UTIL.ConnectServer;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by BAEJJI on 2017-04-11.
 */

public class EventListActivity extends AppCompatActivity {
    ArrayList<HG_Event> eventList = new ArrayList<HG_Event>();
    TextView event_title, event_start_date, event_loc;

    TextView action_title;
    ImageButton menu_bar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_list);

        //action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.argb(255,255,255,255)));

        ArrayList<HG_Event> eventList = getEventList();

        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build()); //activity_main 수정 시 필요

        if(eventList.size() != 0) {

            eventView(eventList);

        } else {

            Toast.makeText(EventListActivity.this, "현재 진행중인 이벤트가 없습니다.", Toast.LENGTH_SHORT).show();

        }


    }

    public ArrayList<HG_Event> getEventList() {

        try {

            ArrayList<String> data = new ArrayList<String>();
            data.add(HttpClient.ADDRESS+"eventList.jsp");
            String result =  new ConnectServer().execute(data).get();

            JSONArray jsonEventList = new JSONArray(result);

            for(int i=0; i<jsonEventList.length(); i++) {

                JSONObject jsonEvent = jsonEventList.getJSONObject(i);

                HG_Event event = new HG_Event();
                event.setEvent_id(jsonEvent.getInt("event_id"));
                event.setEvent_title(jsonEvent.get("event_title").toString());
                event.setEvent_content(jsonEvent.get("event_content").toString());
                event.setEvent_picture(jsonEvent.get("event_picture").toString());
                event.setEvent_loc(jsonEvent.get("event_loc").toString());
                event.setEvent_start_date(jsonEvent.get("event_start_date").toString());
                event.setEvent_end_date(jsonEvent.get("event_end_date").toString());
                eventList.add(event);

            }

        }catch (Exception e) {
            Log.i("Error", "getEventList Error");
        }

        return eventList;

    }

    public void eventView(ArrayList<HG_Event> eventList) {

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new RecyclerAdapter(getApplicationContext(), eventList, R.layout.event_list));

    }

    public Bitmap getEventImage(String url) {

        Bitmap bitmap = null;

        try {

            URL imageUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection)imageUrl.openConnection();
            conn.setDoInput(true);
            conn.connect();
            BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());
            bitmap= BitmapFactory.decodeStream(bis);
            bis.close();

        } catch (IOException e) {
            Log.i("Error", "GetEventImage Error");
        }

        return bitmap;

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
        action_title = (TextView)actionbar.findViewById(R.id.title);
        action_title.setText("Han Place");

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
                        Intent intent = new Intent(EventListActivity.this, CenterActivity.class);
                        intent.putExtra("category_no", 0);
                        startActivity(intent);
                        finish();
                        break;

                    case R.id.map:
                        intent = new Intent(EventListActivity.this, MapActivity0.class);
                        intent.putExtra("category_no", 0);
                        startActivity(intent);
                        finish();
                        break;
                    case R.id.event:
                        intent = new Intent(EventListActivity.this, EventListActivity.class);
                        startActivity(intent);
                        finish();
                        break;
                    case R.id.mymenu:
                        intent = new Intent(EventListActivity.this, MyMenuActivity.class);
                        startActivity(intent);
                        finish();
                        Toast.makeText(EventListActivity.this, "MY MENU", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.logout:
                        Toast.makeText(EventListActivity.this, "Logout", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.chat:
                        intent = new Intent(EventListActivity.this, QNAActivity.class);
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