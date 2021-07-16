package com.example.callback.mathgames;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Iterator;
import java.util.Random;

public class Divisibility extends AppCompatActivity {
    private int counts;
    TextView number,score,question;
    Button yes,no;
    int scoreval = 0;
    int counter = 0;
    int num=0,mod=0;

    String name;
    DbScore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_divisibility);

        name=getIntent().getExtras().getString("name");
        db=new DbScore(this);

        yes = (Button) findViewById(R.id.button);
        no = (Button) findViewById(R.id.button2);
        number = (TextView) findViewById(R.id.number);
        question = (TextView) findViewById(R.id.question);
        score = (TextView) findViewById(R.id.score);

        final TextView counttime = findViewById(R.id.counttime);
        new CountDownTimer(30000, 1000) {
            @Override

            public void onTick(long millisUntilFinished) {
                counttime.setText(String.valueOf("TIMER:" + counter));
                counter++;
            }

            @Override
            public void onFinish() {
                counttime.setText("Finished");
                alertbox();

            }
        }.start();

        setNumber();
        setupGame();
    }

    private final void setupGame() {
        yes.setOnClickListener((View.OnClickListener) new View.OnClickListener() {
            public final void onClick(View it) {
                if (num % mod == 0) {
                    scoreval+=1;
                    score.setText("Score:" + scoreval);
                }
                counts++;
                setNumber();
            }

        });
        no.setOnClickListener((View.OnClickListener) new View.OnClickListener() {
            public final void onClick(View it) {
                if (num % mod != 0) {
                    scoreval+=1;
                    score.setText("Score:" + scoreval);
                }
                counts++;
                setNumber();
            }

        });

        }
    private final void setNumber() {
        mod = 2 + new Random().nextInt(9);
        num = 10000 + new Random().nextInt(90000);
        question.setText("Divisible by "+Integer.toString(mod)+" ?");
        number.setText(Integer.toString(num));
    }
    private void alertbox()
    {
        AlertDialog.Builder alert=new AlertDialog.Builder(Divisibility.this);

        Boolean regResult = db.insertDivisibility(name,scoreval);
        if(regResult==true)
        {
          //  Toast.makeText(this,"Score inserted "+name+" "+scoreval,Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this,"Score insertion failed ",Toast.LENGTH_SHORT).show();
        }
        alert.setMessage("GAME OVER!\nScore : "+scoreval).setCancelable(false)
                .setPositiveButton("NEW",new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        finish();
                        startActivity(getIntent());
                    }
                }).setNegativeButton("EXIT", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        AlertDialog alert2=alert.create();
        alert2.show();
    }
}