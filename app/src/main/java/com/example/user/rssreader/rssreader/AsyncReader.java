package com.example.user.rssreader.rssreader;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by User on 31.01.2017.
 */

public class AsyncReader extends AsyncTask<URL,URL,List<RssHolder>> {

    private ArrayAdapter<RssHolder> adapter;
    private ProgressBar progress;

    /**
     * AsyncTask updating the Arrayadapter
     * */

    public AsyncReader(ArrayAdapter<RssHolder> adapter, ProgressBar bar){
        this.adapter = adapter;
        this.progress = bar;
    }

    @Override
    protected List<RssHolder> doInBackground(URL... params){
        // empty entry list
        LinkedList<RssHolder> list = new LinkedList<RssHolder>();
        try {
            URL url = params[0];
            // get url and create parser
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(false);
            XmlPullParser xpp = factory.newPullParser();
            xpp.setInput(url.openStream(), "UTF_8");

            // read the first element tag
            int eventType = xpp.getEventType();
            RssHolder holder = null;
            String text = "";
            // run loop until xml ends
            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    // xml elements starts check if its an item
                    case XmlPullParser.START_TAG:
                        if (xpp.getName().equalsIgnoreCase("item")) {
                            holder = new RssHolder();
                        }
                        break;
                    // read the text value of the element
                    case XmlPullParser.TEXT:
                        text = xpp.getText();
                        break;
                    // set the textvalue to the coresponging element if it belongs to an item (holder not null)
                    case XmlPullParser.END_TAG:
                        if(holder != null) {
                            // add holder if item ends or add elements of the item to the holder
                            if(xpp.getName().equalsIgnoreCase("item")){
                                list.add(holder);
                                holder = null;
                            } else if(xpp.getName().equalsIgnoreCase("title")){
                                holder.setTitle(text);
                            } else if(xpp.getName().equalsIgnoreCase("link")){
                                holder.setLink(text);
                            } else if(xpp.getName().equalsIgnoreCase("description")){
                                holder.setDescription(text);
                            }else if(xpp.getName().equalsIgnoreCase("pubdate")){
                                holder.setDate(text);
                            }else if(xpp.getName().equalsIgnoreCase("author")){
                                holder.setAuthor(text);
                            }else if(xpp.getName().equalsIgnoreCase("category")){
                                holder.setCategory(text);
                            }else if(xpp.getName().equalsIgnoreCase("comments")){
                                holder.setComments(text);
                            }else if(xpp.getName().equalsIgnoreCase("enclosure")){
                                holder.setEnclosure(text);
                            }else if(xpp.getName().equalsIgnoreCase("guid")){
                                holder.setGuid(text);
                            }else if(xpp.getName().equalsIgnoreCase("source")){
                                holder.setSource(text);
                            }
                        }
                        break;
                    default:
                        break;
                }
                // read next element tag
                eventType = xpp.next();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        // return the full list
        return list;
    }

    @Override
    protected void onPostExecute(List<RssHolder> rssHolders) {
        super.onPostExecute(rssHolders);
        // update listview and hide progressbar
        adapter.clear();
        adapter.addAll(rssHolders);
        adapter.notifyDataSetChanged();
        progress.setVisibility(View.GONE);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        // view progressbar on the ui thread
        progress.setVisibility(View.VISIBLE);
    }
}
