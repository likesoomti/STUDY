package com.onthecoffee.hanplace.FRAGMENT;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.onthecoffee.hanplace.DOMAIN.HG_Facility;
import com.onthecoffee.hanplace.HttpClient;
import com.onthecoffee.hanplace.MAP.MapActivity2;
import com.onthecoffee.hanplace.R;
import com.onthecoffee.hanplace.UTIL.MapAPI;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by Soomti on 2017. 5. 29..
 */

public class map_food extends Fragment {

    ArrayList<HG_Facility> hg; // 객체 저장

    GoogleMap googleMap; // 구글 맵 객체

    MapFragment mapFragment;

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ) {
        View view = inflater.inflate( R.layout.fragment_bycle, container, false );
        return view;
    }


}