package com.onthecoffee.hanplace.JOIN;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.onthecoffee.hanplace.MainActivity;
import com.onthecoffee.hanplace.R;


/**
 * Created by BAEJJI on 2017-04-13.
 */

public class JoinCheckActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.join_check);

    }

    public void backToHome() {

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);

    }

}
