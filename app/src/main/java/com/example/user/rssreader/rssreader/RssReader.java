package com.example.user.rssreader.rssreader;

import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.net.URL;
import java.text.DateFormat;
import java.util.LinkedList;

/**
 * Created by User on 31.01.2017.
 */

public class RssReader {

    private ArrayAdapter<RssHolder> adapter;
    private ProgressBar bar;

    public RssReader(ArrayAdapter<RssHolder> adapter, ProgressBar bar){
        this.adapter = adapter;
        this.bar = bar;
    }
    public void start(URL url) {
        new AsyncReader(adapter, bar).execute(url);
    }

}