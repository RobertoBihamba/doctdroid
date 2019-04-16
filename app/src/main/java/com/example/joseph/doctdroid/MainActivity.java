package com.example.joseph.doctdroid;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;

public class MainActivity extends AppCompatActivity {


    CardView gridView;
    CardView gridView1;
    CardView gridView2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        gridView=(CardView) findViewById(R.id.card_diag);
        gridView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,com.example.joseph.doctdroid.Diagnostic.class);
                startActivity(intent);
            }
        });
        gridView1=(CardView) findViewById(R.id.menu_prof);
        gridView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,com.example.joseph.doctdroid.Profil.class);
                startActivity(intent);
            }
        });
        gridView1=(CardView) findViewById(R.id.menumedoc);
        gridView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,com.example.joseph.doctdroid.Medicament.class);
                startActivity(intent);
            }
        });

    }

}
