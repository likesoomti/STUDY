package com.onthecoffee.hanplace.MAP;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
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

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by Soomti on 2017. 5. 1..
 */

public class MapActivity0 extends AppCompatActivity implements OnMapReadyCallback {

    static final String[] AREA_LIST = {"전체","잠실한강공원", "광나루한강공원", "뚝섬한강공원","잠원한강공원",
            "반포한강공원","이촌한강공원","여의도한강공원","양화한강공원",
            "망원한강공원"} ;

    //구글 API
    GoogleMap googleMap; // 구글 맵 객체
    Spinner map_spinner;
    ArrayList<HG_Facility> hg; // 객체 저장

    //actionbar
    TextView title;
    ImageButton menu_bar;

    int spinner_lock =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map0);

        hg = new ArrayList<>();

        mapAPI();

        // google map view
        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        map_spinner= (Spinner)findViewById(R.id.map_spinner); // 지도
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, AREA_LIST);
        map_spinner.setAdapter(adapter);

        //스피너 이벤트 발생
        map_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //각 항목 클릭시 포지션값을 토스트에 띄운다.

                if(spinner_lock == 0 ){
                    spinner_lock = 1;
                }
                else
                {

                    Intent intent = new Intent(MapActivity0.this, MapActivity.class);
                    intent.putExtra("category_no", position);
                    Log.e("position", "getData: "+position);

                    startActivity(intent);

                    Toast.makeText(getApplicationContext(), Integer.toString(position), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        //action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.argb(255,255,255,255)));



    }
    public void mapAPI(){
        try
        {
            //  Log.e("district", "getData: "+district );

            String result;

            result = new MapAPI(HttpClient.ADDRESS+"/map_category_list.jsp").execute("success").get(); // 값 String 으로 받기

            JsonObject root = new JsonParser().parse(result).getAsJsonObject();
            JsonArray fac_json = root.get("fac").getAsJsonArray();
            Gson gson = new Gson();

            for(int i = 0 ; i < fac_json.size() ; i ++)
            {

                HG_Facility hangang = gson.fromJson(fac_json.get(i), HG_Facility.class);
                hg.add(hangang);

            }


        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        } catch (ExecutionException e)
        {
            e.printStackTrace();
        }
    }
    @Override
    public void onMapReady(final GoogleMap map) {
        this.googleMap = map;
        UiSettings uiSettings = map.getUiSettings();
        uiSettings.setZoomControlsEnabled(true);


        for(HG_Facility h : hg){

            LatLng marker = new LatLng(h.getFAC_LOC_Longi(),h.getFAC_LOC_Lati());
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(marker);
            markerOptions.title(String.valueOf(h.getFAC_id()));
            map.addMarker(markerOptions);

            map.getUiSettings().setZoomGesturesEnabled(true);
            map.moveCamera(CameraUpdateFactory.newLatLng(marker));
            map.animateCamera(CameraUpdateFactory.zoomTo(10));

        }
        //현재위치
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            map.setMyLocationEnabled(true);
        } else {
            // Show rationale and request permission.
        }
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(37.5107162,126.9796832), 13));


        // 마커클릭 이벤트 처리
        // GoogleMap 에 마커클릭 이벤트 설정 가능.
        googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            // 마커 클릭시 호출되는 콜백 메서드
            @Override
            public boolean onMarkerClick(Marker marker) {

                //다이얼로그 안대 엉엉
                Intent intent = new Intent(MapActivity0.this, MapActivity2.class);
                intent.putExtra("id",marker.getTitle());
                Log.e("gettitle", "getData: "+marker.getTitle());


                startActivity(intent);
                //finish(); //인텐트 종료~, 인텐트는 이동해도 그전 class가 켜져있으므로 꺼줍니다

                Toast.makeText(getApplicationContext(),
                        marker.getTitle() + " 클릭했음:"+marker.getId()
                        , Toast.LENGTH_SHORT).show();
                return false;
            }
        });

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
                        Intent intent = new Intent(MapActivity0.this, CenterActivity.class);
                        intent.putExtra("category_no", 0);
                        startActivity(intent);
                        finish();
                        break;

                    case R.id.map:
                        break;
                    case R.id.event:
                        intent = new Intent(MapActivity0.this, EventListActivity.class);
                        startActivity(intent);
                        finish();
                        break;
                    case R.id.mymenu:
                        intent = new Intent(MapActivity0.this, MyMenuActivity.class);
                        startActivity(intent);
                        finish();
                        Toast.makeText(MapActivity0.this, "MY MENU", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.logout:
                        Toast.makeText(MapActivity0.this, "Logout", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.chat:
                        intent = new Intent(MapActivity0.this, QNAActivity.class);
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
