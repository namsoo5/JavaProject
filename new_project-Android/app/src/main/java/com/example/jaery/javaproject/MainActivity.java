package com.example.jaery.javaproject;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import cn.trinea.android.view.autoscrollviewpager.AutoScrollViewPager;

public class MainActivity extends AppCompatActivity {

    AutoScrollViewPager autoViewPager;
    EditText editText;
    boolean express = false;
    View view;

    /*
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<WebToonItem> myDataset;

    */
    DBOpenHelper mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        express = false;// 처음에는 검색창이 확장되어 있지않음

        mydb=new DBOpenHelper(this);
        mydb.open();
        editText = findViewById(R.id.search_webtoon);
        view = findViewById(R.id.inflate);
////// View pager


        ArrayList<Integer> data = new ArrayList<>(); //이미지 url를 저장하는 arraylist
        data.add(R.drawable.main_image1);
        data.add(R.drawable.main_image2);


        autoViewPager = (AutoScrollViewPager)findViewById(R.id.main_viewpager);
        AutoScrollAdapter scrollAdapter = new AutoScrollAdapter(this, data);
        autoViewPager.setAdapter(scrollAdapter); //Auto Viewpager에 Adapter 장착
        autoViewPager.setInterval(5000); // 페이지 넘어갈 시간 간격 설정
        autoViewPager.startAutoScroll(); //Auto Scroll 시작






        ///////////////
        editText.setOnClickListener(new View.OnClickListener(

        ) {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public void onClick(View v) {

                if (express) {

                    editText.post(new Runnable() {
                        @Override
                        public void run() {
                            editText.setFocusableInTouchMode(true);
                            editText.requestFocus();
                            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.showSoftInput(editText, 0);

                        }
                    });

                } else {

                    express = true;
                    TextView textView = findViewById(R.id.Project_title);
                    textView.setVisibility(View.GONE);
                    editText.clearFocus();
                    RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) editText.getLayoutParams();
                    //    layoutParams.removeRule(RelativeLayout.ALIGN_LEFT);
                    layoutParams.addRule(RelativeLayout.ALIGN_LEFT, R.id.inflate);
                    editText.setLayoutParams(layoutParams);

                    layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
                    ValueAnimator animator = ValueAnimator.ofInt(layoutParams.leftMargin, 0);
                    final RelativeLayout.LayoutParams finalLayoutParams = layoutParams;
                    animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator valueAnimator) {
                            finalLayoutParams.leftMargin = (Integer) valueAnimator.getAnimatedValue();

                            view.setLayoutParams(finalLayoutParams);
                        }
                    });
                    animator.setDuration(300);
                    animator.start();
                }
            }
        });

        editText.setOnFocusChangeListener(new View.OnFocusChangeListener()

        {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) editText.getLayoutParams();
                    //    layoutParams.removeRule(RelativeLayout.ALIGN_LEFT);
                    layoutParams.addRule(RelativeLayout.ALIGN_LEFT, R.id.notinflate);
                    editText.setLayoutParams(layoutParams);
                    express = false;
                    editText.setText("");
                    editText.setFocusable(false);
                    layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
                    layoutParams.leftMargin = 250;
                    view.setLayoutParams(layoutParams);
                }
            }
        });

/*
        mRecyclerView = (RecyclerView)
                findViewById(R.id.recycle);
        mLayoutManager = new
                LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        myDataset = new ArrayList<>();
        mAdapter = new
                CardViewAdapter(myDataset);
        mRecyclerView.setAdapter(mAdapter);

        myDataset.add(new

                WebToonItem(R.layout.webtoon_list_item, "신과함께1", "작가1", "2018.10.2", BitmapFactory.decodeResource(getResources(), R.drawable.webtoon_test)));
        myDataset.add(new

                WebToonItem(R.layout.webtoon_list_item, "신과함께1", "작가1", "2018.10.2", BitmapFactory.decodeResource(getResources(), R.drawable.webtoon_test)));

        myDataset.add(new

                WebToonItem(R.layout.webtoon_list_item, "신과함께1", "작가1", "2018.10.2", BitmapFactory.decodeResource(getResources(), R.drawable.webtoon_test)));

        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(), mRecyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(getApplicationContext(), position + "번 째 아이템 클릭", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongItemClick(View view, int position) {
                //  Toast.makeText(getApplicationContext(), position + "번 째 아이템 롱 클릭", Toast.LENGTH_SHORT).show();

            }
        }));
*/

    }

    public void gotoMypage(View v)
    {
        if(mydb.findauto()==0||mydb.findauto()==-1) {
            Intent intent = new Intent(MainActivity.this, Register.class);
            startActivity(intent);
        }else
        {
            Toast.makeText(MainActivity.this,"로그인 상태",Toast.LENGTH_LONG).show();
        }
    }


}
