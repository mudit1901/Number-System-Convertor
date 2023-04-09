package com.example.codeconvertor;



import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private static final int  SPLASH_TIME_OUT=3000;
    ImageView imageView;
   Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        imageView=findViewById(R.id.image);
        animation= AnimationUtils.loadAnimation(this,R.anim.logo);
        imageView.setAnimation(animation);
        animation.setDuration(2000);
        animation.start();



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(MainActivity.this,Final.class);
                startActivity(intent);
                finish();

            }
        },SPLASH_TIME_OUT);




    }
}