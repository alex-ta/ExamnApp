package com.example.user.rssreader.dialog;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.user.rssreader.R;

/**
 * Created by User on 31.01.2017.
 */

public class RssSharedPreferences {

    private Context ctx;
    private SharedPreferences prv;

    public RssSharedPreferences(Context ctx){
        this.ctx = ctx;
        this.prv = ctx.getSharedPreferences(ctx.getString(R.string.preference_shared_key), Context.MODE_PRIVATE);
    }

    public String readUrl(){
        return prv.getString(ctx.getString(R.string.preference_rss_key), "");
    }

    public void saveUrl(String url){
        prv.edit().putString(ctx.getString(R.string.preference_rss_key), url);
    }

}
