package com.example.callback.mathgames;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class TopScore extends AppCompatActivity {

    TextView divisible,oddone;
    DbScore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_score);

        String name=getIntent().getExtras().getString("name");
        db=new DbScore(this);

        int div=db.getDivisibility(name);
        int odd=db.getOddoneoutScore(name);

        divisible = (TextView) findViewById(R.id.divisibility);
        oddone = (TextView) findViewById(R.id.oddoneout);

        divisible.setText(Integer.toString(div));
        oddone.setText(Integer.toString(odd));
    }
}