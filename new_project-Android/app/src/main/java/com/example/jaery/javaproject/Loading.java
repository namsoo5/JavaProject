package com.example.jaery.javaproject;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class Loading extends AppCompatActivity {

    private ImageView imgAndroid;
    private Animation anim;
    DBOpenHelper mydb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        mydb=new DBOpenHelper(this);
        mydb.open();

        if(mydb.findauto()==1)
            mydb.UpdateAuto(0);


        initView();

        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                imgAndroid.setAnimation(null);

                //여기에 딜레이 후 시작할 작업들을 입력

                new Handler().postDelayed(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        Intent intent=new Intent(Loading.this,MainActivity.class);
                        startActivity(intent);
                        finish();
                        //여기에 딜레이 후 시작할 작업들을 입력
                    }
                }, 1300);// 3.5초 정도 딜레이를 준 후 시작

            }
        }, 2500);// 3.5초 정도 딜레이를 준 후 시작
    }

    private void initView() {
        imgAndroid = (ImageView) findViewById(R.id.loading_image);
        anim = AnimationUtils.loadAnimation(this, R.anim.loading);
        imgAndroid.setAnimation(anim);
    }
}
