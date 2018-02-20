package com.example.froggy.dentistofficetracker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by froggy on 2/19/18.
 */

public class activity_admin extends AppCompatActivity {

    Admin admin;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        admin = new Admin();

        try {
            Gson gson = new Gson();
            InputStream is = getAssets().open(getIntent().getExtras().getString("username") + ".txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));

            admin = gson.fromJson(reader, Admin.class);
        } catch(Exception e){
            Log.e("onCreate admin_activity", e.getMessage());
        }

    }
}
