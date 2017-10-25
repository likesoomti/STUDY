package com.onthecoffee.hanplace.UTIL;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.onthecoffee.hanplace.DOMAIN.HG_Facility;

import java.util.ArrayList;

/**
 * Created by Soomti on 2017. 3. 29..
 */
public class MapChangeObj {

    public ArrayList<HG_Facility> ChangeOBj(String value) {


        JsonObject root = new JsonParser().parse(value).getAsJsonObject();
        JsonArray subnode = root.get("fac").getAsJsonArray();
        Gson gson = new Gson();

        ArrayList<HG_Facility> result = new ArrayList<HG_Facility>();
        for (int i = 0; i < subnode.size(); i++) {
            HG_Facility hg = gson.fromJson(subnode.get(i), HG_Facility.class);

            result.add(hg);

            //   Log.i("i", "ChangeOBj: " + hg.toString());
        }
        return result;
    }
}
