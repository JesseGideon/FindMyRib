package com.example.hpuser.findmyrib;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

public class SplashScreen extends AppCompatActivity {
private ViewPager mypager;
private LinearLayout mydotlayout;
private  Slidepager slideadapter;
private TextView[] mdots;
private boolean isLastPageSwiped;
Button finshbtn;
private int mcurrentpst;
 SharedPreferences settings;
 Boolean firstTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        finshbtn = (Button) findViewById(R.id.finish);

        mypager = (ViewPager) findViewById(R.id.mypager);
        mydotlayout = (LinearLayout) findViewById(R.id.dots);

        slideadapter= new Slidepager(this);

        mypager.setAdapter(slideadapter);

        mdotindicator(0);
        mypager.addOnPageChangeListener(viewlisterner);


        final SharedPreferences settings=getSharedPreferences("prefs",0);
        boolean firstRun=settings.getBoolean("firstRun",false);

        if(firstRun==false)//if running for first time
        //Splash will load for first time
        {
            finshbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SharedPreferences.Editor editor=settings.edit();
                    editor.putBoolean("firstRun",true);
                    editor.commit();
                    Intent i=new Intent(SplashScreen.this,FindmyRib.class);
                    startActivity(i);
                    finish();
                }
            });

        }
        else{

            Intent a=new Intent(SplashScreen.this,FindmyRib.class);
            startActivity(a);
            finish();
        }






    }

    public void mdotindicator(int position){

        mdots = new TextView[3];
       //to prevent multiple of dots
        mydotlayout.removeAllViews();

        for(int i=0; i< mdots.length; i++){
            mdots[i]= new TextView(this);
            mdots[i].setText(Html.fromHtml("&#8226"));
            mdots[i].setTextSize(35);
            mdots[i].setTextColor(getResources().getColor(R.color.colorAccent));
            mydotlayout.addView(mdots[i]);
        }
        if(mdots.length > 0){
            mdots[position].setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        }

    }

    //effect on the dots to know which page we are in
ViewPager.OnPageChangeListener viewlisterner = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixel) {

        }

        @Override
        public void onPageSelected(int i) {
            mdotindicator(i);
            mcurrentpst=i;

            if(i==0){
                finshbtn.setText("Skip");
            }else if(i==mdots.length -1){
                finshbtn.setText("Finish");

            }else{
                finshbtn.setText("Skip");
            }
        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    };


}
