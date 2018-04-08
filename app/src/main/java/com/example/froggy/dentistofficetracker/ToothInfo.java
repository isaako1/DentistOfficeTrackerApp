package com.example.froggy.dentistofficetracker;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApiNotAvailableException;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import com.google.gson.Gson;

import java.util.Map;

public class ToothInfo extends Activity  {// this change lets the requestWindowFeature(Window.FEATURE_NO_TITLE) to work
    //public class ToothInfo extends AppCompatActivity {

    private TextView teethNum; // it receives the value from previews activity
    private TextView textFacesDistal;
    private TextView textProcedureDistal;
    private TextView textDiagnosticDistal;

    private String pieceNum;
    private String username;


private DatabaseReference mDatabase;


    // This map will hold all the info from Firebase
    //Map<String, Tooth> teeth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); // this removes the title
        setContentView(R.layout.activity_tooth_info);

        // sets by intent the number on top of the piece diagram
        TextView teethNum = (TextView) findViewById(R.id.pieceNumber);

        // sets  the value from previews activity to textView
        teethNum.setText(getIntent().getExtras().getString("teethNumber"));

        // current piece been working on
        pieceNum = (getIntent().getExtras().getString("teethNumber"));
        username = getIntent().getExtras().getString("username");

        textFacesDistal = (TextView) findViewById(R.id.textFaceDistal);
        textDiagnosticDistal = findViewById(R.id.textDiagDistal);

        getToothInfo();

    }

    private void getToothInfo(){
        textProcedureDistal = findViewById(R.id.textProcedDistal);

        mDatabase = FirebaseDatabase.getInstance().getReference().child(username).child("Odontograma").child(pieceNum);

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if(dataSnapshot.child("Distal").getValue() != null) {
                    textFacesDistal.setText(dataSnapshot.child("Distal").getKey().toString());
                    textDiagnosticDistal.setText(dataSnapshot.child("Distal").child("Diagnostic").child("Diagnostic name").getValue().toString());
                    textProcedureDistal.setText(dataSnapshot.child("Distal").child("Procedure").child("Procedure name").getValue().toString());

                }




            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
    }
}

