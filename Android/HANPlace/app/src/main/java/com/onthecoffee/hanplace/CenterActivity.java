package com.onthecoffee.hanplace;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
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
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.onthecoffee.hanplace.DOMAIN.HG_Event;
import com.onthecoffee.hanplace.EVENT.EventListActivity;
import com.onthecoffee.hanplace.EVENT.EventViewActivity;
import com.onthecoffee.hanplace.MAP.MapActivity0;
import com.onthecoffee.hanplace.USER.MyMenuActivity;
import com.onthecoffee.hanplace.USER.QNAActivity;
import com.onthecoffee.hanplace.UTIL.PermissionUtil;

import java.util.ArrayList;

import static com.facebook.FacebookSdk.getApplicationContext;
import static com.onthecoffee.hanplace.R.id.event;

import static com.onthecoffee.hanplace.R.id.list;
import static com.onthecoffee.hanplace.R.id.main_event4;

/**
 * Created by Soomti on 2017. 5. 4..
 */

public class CenterActivity  extends AppCompatActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {
    //구글 API
    TextView title, main_event1, main_event2, main_event3, main_event4;
    GoogleMap googleMap;
    private GoogleApiClient mGoogleApiClient; // For get my location

    ImageButton menu_bar;
    MapFragment mapFragment;
    LocationManager locationManager;
    LinearLayout boxMap;
    //나의 위도 경도 고도
    private Location mLastKnownLocation;
    //double mLatitude;  //위도
    //double mLongitude; //경도

    private final LatLng mDefaultLocation = new LatLng(37.5107162,126.9796832);
    private static final int DEFAULT_ZOOM = 13;
    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    private boolean mLocationPermissionGranted;
    private CameraPosition mCameraPosition;
    private ArrayList<HG_Event> eventList;
    private int listSize;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_center);

        boxMap = (LinearLayout)findViewById(R.id.center);

        //LocationManager
        locationManager = (LocationManager)getSystemService(LOCATION_SERVICE);


        // Build the Play services client for use by the Fused Location Provider and the Places API.
        // Use the addApi() method to request the Google Places API and the Fused Location Provider.
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */,
                        this /* OnConnectionFailedListener */)
                .addConnectionCallbacks(this)
                .addApi(LocationServices.API)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .build();
        mGoogleApiClient.connect();


       // googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mDefaultLocation, 10));

        //action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.argb(255,255,255,255)));

        getEventList();

    }
    @Override
    public void onConnected(Bundle connectionHint) {
        // Build the map.

        // google map view
        mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult result) {
        // Refer to the reference doc for ConnectionResult to see what error codes might
        // be returned in onConnectionFailed.
        Log.e("T", "Play services connection failed: ConnectionResult.getErrorCode() = "
                + result.getErrorCode());
    }
    @Override
    public void onConnectionSuspended(int cause) {
        Log.d("hi", "Play services connection suspended");
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[],
                                           @NonNull int[] grantResults) {
        mLocationPermissionGranted = false;
        switch (requestCode) {
            case PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mLocationPermissionGranted = true;
                }
            }
        }
        updateLocationUI();
    }
    private void updateLocationUI() {
        if (googleMap == null) {
            return;
        }
        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mLocationPermissionGranted = true;
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        }

        if (mLocationPermissionGranted) {
            googleMap.setMyLocationEnabled(true);
            googleMap.getUiSettings().setMyLocationButtonEnabled(true);
        } else {
            googleMap.setMyLocationEnabled(false);
            googleMap.getUiSettings().setMyLocationButtonEnabled(false);
            mLastKnownLocation = null;
        }
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        //지도타입 - 일반
      //  this.googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        //나의 위치 설정
        LatLng position = new LatLng(37.5107162 , 126.9796832);
        this.googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mDefaultLocation, DEFAULT_ZOOM));
        //find my location
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            this.googleMap.setMyLocationEnabled(true);
        } else {
            this.googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mDefaultLocation, DEFAULT_ZOOM));
        }
    }

    private void getDeviceLocation() {
        /*
         * Request location permission, so that we can get the location of the
         * device. The result of the permission request is handled by a callback,
         * onRequestPermissionsResult.
         */
        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mLocationPermissionGranted = true;
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        }
        /*
         * Get the best and most recent location of the device, which may be null in rare
         * cases when a location is not available.
         */
        if (mLocationPermissionGranted) {
            mLastKnownLocation = LocationServices.FusedLocationApi
                    .getLastLocation(mGoogleApiClient);
        }

        // Set the map's camera position to the current location of the device.
        if (mCameraPosition != null) {
            googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(mCameraPosition));
        } else if (mLastKnownLocation != null) {
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                    new LatLng(mLastKnownLocation.getLatitude(),
                            mLastKnownLocation.getLongitude()), DEFAULT_ZOOM));
        } else {
            Log.e("getdevice", "Current location is null. Using defaults.");
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mDefaultLocation, DEFAULT_ZOOM));
            googleMap.getUiSettings().setMyLocationButtonEnabled(false);
        }
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

                    case R.id.map:
                        Intent intent = new Intent(CenterActivity.this, MapActivity0.class);
                        intent.putExtra("category_no", 0);
                        startActivity(intent);
                        finish();
                        break;
                    case R.id.event:
                        intent = new Intent(CenterActivity.this, EventListActivity.class);
                        startActivity(intent);
                        finish();
                        break;
                    case R.id.mymenu:
                        intent = new Intent(CenterActivity.this, MyMenuActivity.class);
                        startActivity(intent);
                        finish();
                        break;
                    case R.id.logout:
                        Log.e("adsfsdfasdf","1"); Log.e("adsfsdfasdf","1"); Log.e("adsfsdfasdf","1");
                        new LoginActivity().loginSessionOut();

                        Log.e("adsfsdfasdf","RN꾸까깎ㄲ깎꾸깎까ㅏ");
                        Log.e("adsfsdfasdf","RN꾸까깎ㄲ깎꾸깎까ㅏ");
                        Log.e("adsfsdfasdf","RN꾸까깎ㄲ깎꾸깎까ㅏ");

                        Log.e("adsfsdfasdf","RN꾸까깎ㄲ깎꾸깎까ㅏ");
                        Log.e("adsfsdfasdf","RN꾸까깎ㄲ깎꾸깎까ㅏ");
                        Log.e("adsfsdfasdf","RN꾸까깎ㄲ깎꾸깎까ㅏ");

                        intent = new Intent(CenterActivity.this, LoginActivity.class);
                        Log.e("onMenuItemClick","2");
                        startActivity(intent);
                        Log.e("onMenuItemClick","3");
                        finish();
                        break;
                    case R.id.chat:
                        intent = new Intent(CenterActivity.this, QNAActivity.class);
                        startActivity(intent);
                        finish();
                        break;
                }
                return false;
            }
        });
        popup.show();//Popup Menu 보이기
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // NavUtils.navigateUpFromSameTask(this);
                finish();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void getEventList() {

        eventList = new EventListActivity().getEventList();
        listSize = eventList.size();
        Log.e("listSize", "getEventList: "+listSize );
        main_event1 = (TextView)findViewById(R.id.main_event1);
        main_event1.setText(eventList.get(listSize-1).getEvent_title());
        main_event1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), EventViewActivity.class);
                intent.putExtra("event", eventList.get(listSize-1));
                v.getContext().startActivity(intent);
            }
        });

        main_event2 = (TextView)findViewById(R.id.main_event2);
        main_event2.setText(eventList.get(listSize-2).getEvent_title());
        main_event2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), EventViewActivity.class);
                intent.putExtra("event", eventList.get(listSize-2));
                v.getContext().startActivity(intent);
            }
        });

        main_event3 = (TextView)findViewById(R.id.main_event3);
        main_event3.setText(eventList.get(listSize-3).getEvent_title());
        main_event3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), EventViewActivity.class);
                intent.putExtra("event", eventList.get(listSize-3));
                v.getContext().startActivity(intent);
            }
        });
        main_event4 = (TextView)findViewById(R.id.main_event4);
        main_event4.setText(eventList.get(listSize-4).getEvent_title());
        main_event4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), EventViewActivity.class);
                intent.putExtra("event", eventList.get(listSize-4));
                v.getContext().startActivity(intent);
            }
        });
    }
}
