package com.example.draqo.weathertoday;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // TEXTVIEW
    public static TextView weather;
    public static TextView weather1;
    public static TextView weather2;
    public static TextView weather3;

    //IMAGE FILES

    public static ImageView imageStatusNow;
    public static ImageView imageStatusOne;
    public static ImageView imageStatusTwo;
    public static ImageView imageStatusThree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        weather = (TextView) findViewById(R.id.weather);
        weather1 = (TextView) findViewById(R.id.weather1);
        weather2 = (TextView) findViewById(R.id.weather2);
        weather3 = (TextView) findViewById(R.id.weather3);

        imageStatusNow = (ImageView) findViewById(R.id.imageNow);
        imageStatusOne = (ImageView) findViewById(R.id.imageNight);
        imageStatusTwo = (ImageView) findViewById(R.id.imangeMorning);
        imageStatusThree = (ImageView) findViewById(R.id.imageDay);



        ConstraintLayout constraintLayout = (ConstraintLayout) findViewById(R.id.constraint);

        constraintLayout.setBackgroundResource(R.drawable.weather1);
        update(getCurrentFocus());
    }


    public void update(View view) {
        MyTask myTask =  new MyTask();
        myTask.execute();
    }
}
