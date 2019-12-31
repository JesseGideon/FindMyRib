package com.example.hpuser.findmyrib;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Slidepager extends PagerAdapter {


    Context context;
    LayoutInflater layoutInflater;

    public Slidepager(Context context){
        this.context = context;
    }

    //Array of images
    public int[] slideimage={
            R.drawable.connect,
            R.drawable.loveup,
            R.drawable.myjoy
    };

    public String[] headingtext={
            "Find Your Match",
            "True Happiness",
            "God's Plan"
    };
public String[] Desacription={
  "Connect with genuine single christian ladies & guys in your area. Make new friends, send messages & photos",
  "Experience true happiness,love is that condition in which the happiness of another person is essential to your own",
  "Get to meet that true person for you, in order to understand and  bring God's plan in cognizance for you two"
};
    @Override
    public int getCount() {
        return headingtext.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view==(RelativeLayout) o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout,container,false);
        ImageView image= view.findViewById(R.id.slideImage);
        TextView headtext = view.findViewById(R.id.header);
        TextView desc = view.findViewById(R.id.description);


        image.setImageResource(slideimage[position]);
        headtext.setText(headingtext[position]);
        desc.setText(Desacription[position]);

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object myobject) {
        container.removeView((RelativeLayout) myobject);
    }
}
