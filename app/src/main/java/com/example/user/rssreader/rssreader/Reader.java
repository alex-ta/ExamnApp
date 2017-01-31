package com.example.user.rssreader.rssreader;

import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.net.URL;

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

        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(false);
            XmlPullParser xpp = factory.newPullParser();
            xpp.setInput(url.openStream(), "UTF_8");

            boolean insideItem = false;

            // Returns the type of current event: START_TAG, END_TAG, etc..
            int eventType = xpp.getEventType();
            Log.d("read","readsdasd");
            while (eventType != XmlPullParser.END_DOCUMENT) {
                Log.d("Read", xpp.getName()+" "+xpp.getText());
                eventType = xpp.next(); //move to next element
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void stop(){}


}