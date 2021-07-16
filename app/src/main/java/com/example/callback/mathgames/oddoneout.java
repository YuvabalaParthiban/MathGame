package com.example.callback.mathgames;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class oddoneout extends AppCompatActivity {
    private int counts;
    TextView number,score,question;
    EditText getAns;
    Button submit;
    int scoreval = 0;
    int counter = 0;
    int num1=0,num2=0,num3=0,num4=0,start=0,plus=0,rand=0;
    String ans="",name;

    DbScore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oddoneout);

        name=getIntent().getExtras().getString("name");
        db=new DbScore(this);

        submit = (Button) findViewById(R.id.submit);
        number = (TextView) findViewById(R.id.number);
        question = (TextView) findViewById(R.id.question);
        score = (TextView) findViewById(R.id.score);


        final TextView counttime = findViewById(R.id.counttime);
        new CountDownTimer(40000, 1000) {
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
       submit.setOnClickListener((View.OnClickListener) new View.OnClickListener() {
            public final void onClick(View it) {
                getAns = (EditText) findViewById(R.id.answer);
                if (getAns.getText().toString().equalsIgnoreCase(ans)) {
                    scoreval+=1;
                    score.setText("Score:" + scoreval);
                }
                getAns.setText("");
                counts++;
                setNumber();
            }

        });
    }
    private final void setNumber() {
        start = 2 + new Random().nextInt(8);
        plus = 3 + new Random().nextInt(15);
        rand = 1 + new Random().nextInt(4);
        num1 = start+plus;
        num2 = num1+plus;
        num3 = num2+plus;
        num4 = num3+plus;
        if(rand==1)
        {
            num1-=start;
            ans=Integer.toString(num1);
        }
        else if(rand==2)
        {
            num2-=start;
            ans=Integer.toString(num2);
        }
        else if(rand==3)
        {
            num3-=start;
            ans=Integer.toString(num3);
        }
        else
        {
            num4-=start;
            ans=Integer.toString(num4);
        }
        question.setText(Integer.toString(start)+" "+Integer.toString(num1)+" "+Integer.toString(num2)+" "+Integer.toString(num3)+" "+
               Integer.toString(num4));
    }
    private void alertbox()
    {
        AlertDialog.Builder alert=new AlertDialog.Builder(oddoneout.this);
        Boolean regResult = db.insertOddoneoutScore(name,scoreval);
        if(regResult==true)
        {

            //Toast.makeText(this,"Score inserted "+name+" "+scoreval,Toast.LENGTH_SHORT).show();

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