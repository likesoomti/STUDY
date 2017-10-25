package com.onthecoffee.hanplace.MAP;


import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
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
import java.util.Map;
import java.util.concurrent.ExecutionException;


public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    static final String[] AREA_LIST = {"잠실한강공원", "광나루한강공원", "뚝섬한강공원", "잠원한강공원",
            "반포한강공원", "이촌한강공원", "여의도한강공원", "양화한강공원",
            "망원한강공원"};

    //커스텀 마크
    View marker_root_view;//커스텀 마크
    ImageView map_marker; //커스텀 마크 안에 텍스트

    //프래그먼트 뷰
    Button MAP_food_btn;
    Button MAP_toilet_btn;
    Button MAP_bycyle_btn;
    Button MAP_sport_btn;

    //구글 API
    GoogleMap googleMap; // 구글 맵 객체
    ArrayList<HG_Facility> hg; // 객체 저장
    MapFragment mapFragment;

    TextView title;
    ImageButton menu_bar;

    int category_no;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        //button connect

        MAP_food_btn = (Button) findViewById(R.id.MAP_food_btn);
        MAP_bycyle_btn = (Button) findViewById(R.id.MAP_bycyle_btn);
        MAP_sport_btn = (Button) findViewById(R.id.MAP_sport_btn);
        MAP_toilet_btn = (Button) findViewById(R.id.MAP_toilet_btn);

        hg = new ArrayList<>();


        Intent intent = getIntent();
        category_no = intent.getIntExtra("category_no", 0);
        //지도 정보 가져오기
        getData(category_no);


        // google map view
        mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.argb(255, 255, 255, 255)));


        MAP_food_btn.setOnClickListener(map_click);
        MAP_sport_btn.setOnClickListener(map_click);

        MAP_toilet_btn.setOnClickListener(map_click);


        MAP_bycyle_btn.setOnClickListener(map_click);


    }



    View.OnClickListener map_click = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MapActivity.this, MAP_BTN.class);

            switch( v.getId() ) {
                default:
                case R.id.MAP_food_btn:{

                    intent.putExtra("category_no",category_no);
                    intent.putExtra("place_no",1);
                    startActivity(intent);
                    break;
                }
                case R.id.MAP_bycyle_btn:{
                    intent.putExtra("category_no",category_no);
                    intent.putExtra("place_no",3);
                    startActivity(intent);
                    break;
                }
                case R.id.MAP_sport_btn:{
                    intent.putExtra("category_no",category_no);
                    intent.putExtra("place_no",4);
                    startActivity(intent);
                    break;

                }
                case R.id.MAP_toilet_btn:{
                    intent.putExtra("category_no",category_no);
                    intent.putExtra("place_no",2);
                    startActivity(intent);
                    break;
                }
            }


        }
    };

    //db에서 지도 정보 가져온다
    public void getData(int district) {
        try {

            String result;
            String hi;
            //지역에 따라 값 호출
            if (district == 0) {
                result = new MapAPI(HttpClient.ADDRESS + "map_category_list.jsp").execute("success").get(); // 값 String 으로 받기
                hi = "if";
            } else {
                result = new MapAPI(HttpClient.ADDRESS + "map_category_list.jsp?id=" + district).execute("success").get(); // 값 String 으로 받기
                hi = "else";
            }

            Log.e("result", "getData: " + hi);
            JsonObject root = new JsonParser().parse(result).getAsJsonObject();
            JsonArray fac_json = root.get("fac").getAsJsonArray();
            Gson gson = new Gson();

            for (int i = 0; i < fac_json.size(); i++) {

                HG_Facility hangang = gson.fromJson(fac_json.get(i), HG_Facility.class);
                hg.add(hangang);

            }


        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
    //db에서 카테고리 분류해서 가져온다

    public void getData(int place_no, int category_no) {
        try {
            //  Log.e("district", "getData: "+district );
            hg = new ArrayList<>();

            String result;
            String hi, hi2;

            //id = place_no ,
            result = new MapAPI(HttpClient.ADDRESS + "map_button_list.jsp?place_no="
                    + place_no + "&category_no=" + category_no).execute("success").get(); // 값 String 으로 받기
            hi = "d" + place_no + "c" + category_no;
            hi2 = HttpClient.ADDRESS + "map_button_list.jsp?place_no=" + place_no + "&category_no=" + category_no;

            //전체 값을 호출한다. 지역 전체 버튼 전체
            Log.e("result", "getData: " + hi);
            Log.e("address", "getData: " + hi2);
            JsonObject root = new JsonParser().parse(result).getAsJsonObject();
            JsonArray fac_json = root.get("fac").getAsJsonArray();
            Gson gson = new Gson();

            for (int i = 0; i < fac_json.size(); i++) {

                HG_Facility hangang = gson.fromJson(fac_json.get(i), HG_Facility.class);
                Log.e("hg", "hgobj: " + hangang);
                hg.add(hangang);

            }
            onMapReady(googleMap);


        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void onMapReady(GoogleMap map) {
        googleMap = map;
        UiSettings uiSettings = map.getUiSettings();
        uiSettings.setZoomControlsEnabled(true);
        for (HG_Facility h : hg) {

            LatLng marker = new LatLng(h.getFAC_LOC_Longi(), h.getFAC_LOC_Lati());
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(marker);
            markerOptions.title(String.valueOf(h.getFAC_id()));
            map.addMarker(markerOptions);
            map.moveCamera(CameraUpdateFactory.newLatLng(marker));
            map.animateCamera(CameraUpdateFactory.zoomTo(12));

        }
        if(hg == null){
            map.animateCamera(CameraUpdateFactory.zoomTo(12));
            LatLng marker = new LatLng(37.5107162,126.9796832);
            map.moveCamera(CameraUpdateFactory.newLatLng(marker));
            Toast.makeText(this, "찾으시는 건물이 없습니다", Toast.LENGTH_SHORT).show();

        }
        // 마커클릭 이벤트 처리
        // GoogleMap 에 마커클릭 이벤트 설정 가능.
        googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            // 마커 클릭시 호출되는 콜백 메서드
            @Override
            public boolean onMarkerClick(Marker marker) {

                //다이얼로그 안대 엉엉
                Intent intent = new Intent(MapActivity.this, MapActivity2.class);
                intent.putExtra("id", marker.getTitle());

                startActivity(intent);

                return false;
            }
        });


    }


    private void getSampleMarkerItems() {

        //데이터 마커에 저장
        for (HG_Facility markerItem : hg) {
            addMarker(markerItem);
        }

    }
    //먼저 위에서 커스텀마커를 위해 만들어둔 xml을 가져오기

    private void setCustomMarkerView() {

        marker_root_view = LayoutInflater.from(this).inflate(R.layout.marker_layout, null);
        map_marker = (ImageView) marker_root_view.findViewById(R.id.map_marker);
    }

    private Marker addMarker(HG_Facility markerItem) {


        LatLng position = new LatLng(markerItem.getFAC_LOC_Lati(), markerItem.getFAC_LOC_Longi());

        map_marker.setBackgroundResource(R.drawable.running);

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(position);
        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(createDrawableFromView(this, marker_root_view)));


        return googleMap.addMarker(markerOptions);

    }

    // View를 Bitmap으로 변환
    private Bitmap createDrawableFromView(Context context, View view) {

        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        view.measure(displayMetrics.widthPixels, displayMetrics.heightPixels);
        view.layout(0, 0, displayMetrics.widthPixels, displayMetrics.heightPixels);
        view.buildDrawingCache();
        Bitmap bitmap = Bitmap.createBitmap(view.getMeasuredWidth(), view.getMeasuredHeight(), Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(bitmap);
        view.draw(canvas);

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
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View actionbar = inflater.inflate(R.layout.actionbar_main, null);
        title = (TextView) actionbar.findViewById(R.id.title);
        title.setText("Han Place");

        menu_bar = (ImageButton) actionbar.findViewById(R.id.menu_bar);
        menu_bar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                popupMenu(v);

            }
        });
        actionBar.setCustomView(actionbar);


        //액션바 양쪽 공백 없애기
        Toolbar parent = (Toolbar) actionbar.getParent();
        parent.setContentInsetsAbsolute(0, 0);

        return true;
    }

    public void popupMenu(View v) {
        PopupMenu popup = new PopupMenu(this, v);//v는 클릭된 뷰를 의미
        getMenuInflater().inflate(R.menu.main_menu, popup.getMenu());

        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {//눌러진 MenuItem의 Item Id를 얻어와 식별

                    case R.id.home:
                        Intent intent = new Intent(MapActivity.this, CenterActivity.class);
                        intent.putExtra("category_no", 0);
                        startActivity(intent);
                        finish();
                        break;

                    case R.id.map:
                        intent = new Intent(MapActivity.this, MapActivity0.class);
                        intent.putExtra("category_no", 0);
                        startActivity(intent);
                        finish();
                        break;
                    case R.id.event:
                        intent = new Intent(MapActivity.this, EventListActivity.class);
                        startActivity(intent);
                        finish();
                        break;
                    case R.id.mymenu:
                        intent = new Intent(MapActivity.this, MyMenuActivity.class);
                        startActivity(intent);
                        finish();
                        Toast.makeText(MapActivity.this, "MY MENU", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.logout:
                        Toast.makeText(MapActivity.this, "Logout", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.chat:
                        intent = new Intent(MapActivity.this, QNAActivity.class);
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


