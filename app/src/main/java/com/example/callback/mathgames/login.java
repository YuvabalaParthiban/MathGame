package com.example.callback.mathgames;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class login extends AppCompatActivity {

    EditText getName;
    Button entergame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getName = (EditText) findViewById(R.id.namebox);
        entergame = (Button) findViewById(R.id.enter);


        entergame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = getName.getText().toString();
                Intent intent = new Intent(login.this, MainActivity.class);
                intent.putExtra("name", user);
                startActivity(intent);
            }
        });
    }

}

