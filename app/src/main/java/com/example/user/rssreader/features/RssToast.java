package com.example.user.rssreader.features;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.rssreader.R;

/**
 * Created by User on 01.02.2017.
 */

public class RssToast extends Toast{

    public RssToast(Activity ctx) {
        super(ctx);
        super.setDuration(Toast.LENGTH_LONG);
        this.layout = ctx.getLayoutInflater().inflate(R.layout.toast_rss, null);
        this.setView(layout);//setting the view of custom toast layout
        this.setImage(ContextCompat.getDrawable(ctx, R.mipmap.ic_launcher));
    }

    private View layout;

    public TextView getTextView(){ return ((TextView)this.layout.findViewById(R.id.custom_toast_message));}
    public ImageView getImageView(){ return ((ImageView)this.layout.findViewById(R.id.custom_toast_image));}
    public LinearLayout getLinearLayout(){ return ((LinearLayout)this.layout.findViewById(R.id.custom_toast_layout));}


    public RssToast setText(String s){
        getTextView().setText(s);
        return this;
    }

    public RssToast setImage(Drawable d){
        getImageView().setImageDrawable(d);
        return this;
    }
}
