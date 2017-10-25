package com.onthecoffee.hanplace.EVENT;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.onthecoffee.hanplace.CenterActivity;
import com.onthecoffee.hanplace.MAP.MapActivity0;
import com.onthecoffee.hanplace.MAP.MapActivity2;
import com.onthecoffee.hanplace.R;
import com.onthecoffee.hanplace.DOMAIN.HG_Event;
import com.onthecoffee.hanplace.USER.MyMenuActivity;
import com.onthecoffee.hanplace.USER.QNAActivity;
import com.tsengvn.typekit.Typekit;
import com.tsengvn.typekit.TypekitContextWrapper;

/**
 * Created by BAEJJI on 2017-04-07.
 */

public class EventViewActivity extends AppCompatActivity  {
    TextView event_title, event_content, event_loc, event_start_date, event_end_date;

    TextView action_title;
    ImageButton menu_bar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_read);

        Bundle bundle = getIntent().getExtras();
        HG_Event event = (HG_Event) bundle.getParcelable("event");
        readEvent(event);

        /*Typekit.getInstance()
                .addNormal(Typekit.createFromAsset(this, "font/NotoSansCJKkr-Regular.otf"))
                .addBold(Typekit.createFromAsset(this, "font/NotoSansCJKkr-Bold.otf"));*/


        //action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.argb(255,255,255,255)));



    }

    @Override
    protected void attachBaseContext(Context newBase) {

        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));

    }

    public void readEvent(HG_Event event) {

         event_title = (TextView) findViewById(R.id.event_title);
         event_content = (TextView) findViewById(R.id.event_content);
         event_loc = (TextView) findViewById(R.id.event_loc);
         event_start_date = (TextView) findViewById(R.id.event_start_date);
         event_end_date = (TextView) findViewById(R.id.event_end_date);

        event_title.setText(event.getEvent_title());
        event_content.setText(event.getEvent_content());
        event_loc.setText(event.getEvent_loc());
        event_start_date.setText(event.getEvent_start_date());
        event_end_date.setText(event.getEvent_end_date());

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
                        Intent intent = new Intent(EventViewActivity.this, CenterActivity.class);
                        intent = new Intent(EventViewActivity.this, QNAActivity.class);
                        startActivity(intent);
                        finish();
                        break;
                    case R.id.map:
                        intent = new Intent(EventViewActivity.this, EventListActivity.class);
                        intent.putExtra("category_no", 0);
                        startActivity(intent);
                        finish();
                        break;
                    case R.id.event:
                        intent = new Intent(EventViewActivity.this, EventListActivity.class);
                        startActivity(intent);
                        finish();
                        break;
                    case R.id.mymenu:
                        intent = new Intent(EventViewActivity.this, MyMenuActivity.class);
                        startActivity(intent);
                        finish();
                        Toast.makeText(EventViewActivity.this, "MY MENU", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.logout:
                        Toast.makeText(EventViewActivity.this, "Logout", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.chat:
                        intent = new Intent(EventViewActivity.this, QNAActivity.class);
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
