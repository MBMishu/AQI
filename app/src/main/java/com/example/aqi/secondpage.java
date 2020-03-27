package com.example.aqi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class secondpage extends AppCompatActivity {
    SwipeRefreshLayout swipeRefreshLayout;
    FirebaseAuth firebaseAuth;

    Button button1;
    Button button2;
    TextView textView;
    TextView textView2;
    TextView textView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondpage);
        button1 = findViewById(R.id.button3);
        button2 = findViewById(R.id.button8);
        textView = findViewById(R.id.button);
        textView2 = findViewById(R.id.button7);
        //textView3 = findViewById(R.id.textView);
        swipeRefreshLayout = findViewById(R.id.Swipe2);

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
                }, 3500);
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(secondpage.this, opening.class);

                startActivity(registerIntent);
                finish();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(secondpage.this, "AQI Device Is Not Installed Yet\n at BRAC University.", Toast.LENGTH_LONG).show();
            }
        });

    }

    private void RecieveData() {
        DatabaseReference value3 = FirebaseDatabase.getInstance().getReference().child("ioSys").child("sensors").child("carbon");
        value3.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String v1 = (String) dataSnapshot.getValue();
                //box3.setText(v1);

                if (v1.compareTo("15") == 1) {
                    //textView3.setText(v1);
//                    textView3.setBackgroundColor(Color.R);
//                    button2.setText("dangerous");
                    textView.setBackgroundResource(R.drawable.red);
                }else{
                    //textView3.setText(v1);
//                    textView3.setBackgroundColor(Color.GREEN);
////                    button2.setText("dangerous");
                    textView.setBackgroundResource(R.drawable.yellow);
                }
//                if(v1.compareTo("10") == -1){
//                    textView3.setText(v1);
//                    textView.setBackgroundResource(R.drawable.red);
//                }
//
//                else if(v1.compareTo("10") == 1 && v1.compareTo("15") == -1) {
//                    textView3.setText(v1);
//                    textView3.setBackgroundColor(Color.GRAY);
//                    textView.setBackgroundResource(R.drawable.orange);
//                }else if(v1.compareTo("15") == 1 && v1.compareTo("20") == -1) {
//                    textView3.setText(v1);
//                    textView3.setBackgroundColor(Color.YELLOW);
//                    textView.setBackgroundResource(R.drawable.yellow);
//                }else if(v1.compareTo("20") == 1){
//                    textView3.setText(v1);
//                    textView3.setBackgroundColor(Color.RED);
//                    textView.setBackgroundResource(R.drawable.red);
//                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //RecieveData();
    }
}
