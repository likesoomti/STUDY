package com.onthecoffee.hanplace.UTIL;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


/**
 * Created by BAEJJI on 2017-01-17.
 */

public class ConnectServer extends AsyncTask<ArrayList<String>, Void, String> {

    String receiveMsg, sendMsg;

    @Override
    protected String doInBackground(ArrayList<String>... data) {

        try {

            String getData;
            URL url = new URL(data[0].get(0));
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestMethod("POST");
            OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());

            if(data[0].size() > 1) { //파라미터가 있다면,

                sendMsg = data[0].get(1);

                osw.write(sendMsg);

            }

            osw.flush();

            if (conn.getResponseCode() == conn.HTTP_OK) {

                InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "UTF-8");
                BufferedReader reader = new BufferedReader(tmp);
                StringBuffer buffer = new StringBuffer();

                while ((getData = reader.readLine()) != null) {

                    buffer.append(getData + "\n");

                }

                reader.close();
                receiveMsg = buffer.toString();

            } else {
                Log.i("통신 결과", conn.getResponseCode() + "에러");
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return receiveMsg;

    }

}
