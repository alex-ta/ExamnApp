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

    public AsyncReader(ArrayAdapter<RssHolder> adapter, ProgressBar bar){
        this.adapter = adapter;
        this.progress = bar;
    }

    @Override
    protected List<RssHolder> doInBackground(URL... params){
        LinkedList<RssHolder> list = new LinkedList<RssHolder>();
        try {
            URL url = params[0];
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(false);
            XmlPullParser xpp = factory.newPullParser();
            xpp.setInput(url.openStream(), "UTF_8");

            int eventType = xpp.getEventType();
            RssHolder holder = null;
            String text = "";
            while (eventType != XmlPullParser.END_DOCUMENT) {

                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        if (xpp.getName().equalsIgnoreCase("item")) {
                            holder = new RssHolder();
                        }
                        break;
                    case XmlPullParser.TEXT:
                        text = xpp.getText();
                        break;
                    case XmlPullParser.END_TAG:
                        if(holder != null) {
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
                eventType = xpp.next();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    @Override
    protected void onPostExecute(List<RssHolder> rssHolders) {
        super.onPostExecute(rssHolders);
        adapter.clear();
        adapter.addAll(rssHolders);
        adapter.notifyDataSetChanged();
        progress.setVisibility(View.GONE);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progress.setVisibility(View.VISIBLE);
    }
}
