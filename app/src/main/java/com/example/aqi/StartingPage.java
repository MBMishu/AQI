package com.example.aqi;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class StartingPage extends AppCompatActivity {
    SwipeRefreshLayout swipeRefreshLayout;
    FirebaseAuth firebaseAuth;

    TextView box1;
    TextView box2;
    TextView box3;
    TextView box4;
    TextView box5;
    Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting_page);

        box1 = findViewById(R.id.tvView1);
        box2 = findViewById(R.id.tvView2);
        box3 = findViewById(R.id.tvView3);
        box4 = findViewById(R.id.tvView4);
        box5 = findViewById(R.id.tvView5);
        //button2 = findViewById(R.id.button2);
        button2 = findViewById(R.id.button2);

        swipeRefreshLayout = findViewById(R.id.Swipe);
//call method

            RecieveData();
            //refresh layout
            swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
//call method
                    RecieveData();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            swipeRefreshLayout.setRefreshing(false);
                        }
                    },3500);
                }
            });

button2.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent registerIntent = new Intent(StartingPage.this, secondpage.class);

        startActivity(registerIntent);
        finish();
    }
});

    }

    private void RecieveData() {
        DatabaseReference value1 = FirebaseDatabase.getInstance().getReference().child("ioSys").child("sensors").child("temp");
        value1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String v1 = (String) dataSnapshot.getValue();
                box1.setText(v1+"c");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        DatabaseReference value2 = FirebaseDatabase.getInstance().getReference().child("ioSys").child("sensors").child("humiduty");
        value2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String v1 = (String) dataSnapshot.getValue();
                box2.setText(v1+"%");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        DatabaseReference value3 = FirebaseDatabase.getInstance().getReference().child("ioSys").child("sensors").child("carbon");
        value3.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String v1 = (String) dataSnapshot.getValue();

                box3.setText(v1+"ppm");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        DatabaseReference value4 = FirebaseDatabase.getInstance().getReference().child("ioSys").child("sensors").child("dust");
        value4.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String v1 = (String) dataSnapshot.getValue();
                box4.setText(v1+"ppm");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        DatabaseReference value5 = FirebaseDatabase.getInstance().getReference().child("ioSys").child("sensors").child("sound");
        value5.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String v1 = (String) dataSnapshot.getValue();
                box5.setText(v1+"db");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        //RecieveData();
    }
}
