package com.example.user.rssreader.features;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.user.rssreader.R;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by User on 31.01.2017.
 */

public class RssSharedPreferences {

    /**
     * Preferences for RSS app
     * */

    private Context ctx;
    private SharedPreferences prv;
    private static RssSharedPreferences instance;

    // singelton but dependent on context
    public static RssSharedPreferences getInstance(Context ctx){
        if (instance == null){
            instance = new RssSharedPreferences(ctx);
        }
        return instance;
    }

    private RssSharedPreferences(Context ctx){
        this.ctx = ctx;
        this.prv = ctx.getSharedPreferences(ctx.getString(R.string.preference_shared_key), Context.MODE_PRIVATE);
    }

    public String getUrl(){
        return prv.getString(ctx.getString(R.string.preference_rss_url), "");
    }

    public void saveUrl(String url){
        prv.edit().putString(ctx.getString(R.string.preference_rss_url), url).commit();
    }

    public ArrayList<String> getOpenedSites(){
        return new ArrayList<String>(prv.getStringSet(ctx.getString(R.string.preference_rss_read),new HashSet<String>()));
    }

    public void saveOpenedSites(ArrayList<String> sites){
        prv.edit().putStringSet(ctx.getString(R.string.preference_rss_read), new HashSet<String>(sites)).commit();
    }
}
