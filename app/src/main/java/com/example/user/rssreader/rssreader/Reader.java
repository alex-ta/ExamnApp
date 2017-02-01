package com.example.user.rssreader.rssreader;

import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.net.URL;
import java.text.DateFormat;
import java.util.LinkedList;

/**
 * Created by User on 31.01.2017.
 */

public class Reader {

    private URL url;

    public Reader(URL url){
        this.url = url;
        try {
            this.url = new URL("http://www.w3schools.com/xml/rss_tag_title_link_description_item.asp");
        } catch (Exception e){
            e.printStackTrace();
        }
    }


    public void start() {

    }

    public void stop(){}


}