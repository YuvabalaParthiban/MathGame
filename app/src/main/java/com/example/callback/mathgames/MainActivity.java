package com.example.callback.mathgames;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name=getIntent().getExtras().getString("name");
    }
    public void Divisibile(View view){
        Intent i = new Intent(this,Divisibility.class);
        i.putExtra("name",name);
        startActivity(i);
    }
    public void oddone(View view){
        Intent i = new Intent(this,oddoneout.class);
        i.putExtra("name",name);
        startActivity(i);
    }
    public void topscore(View view){
        Intent i = new Intent(this,TopScore.class);
        i.putExtra("name",name);
        startActivity(i);
    }
}