package com.example.user.rssreader;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.user.rssreader.R;
import com.example.user.rssreader.features.ReadSites;
import com.example.user.rssreader.rssreader.RssHolder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

/**
 * Created by User on 31.01.2017.
 */

public class RssListviewAdapter extends ArrayAdapter<RssHolder>{

    private ArrayList<RssHolder> list;
    private ReadSites read;

    public RssListviewAdapter(Context context, ReadSites read) {
        super(context, R.layout.activity_single_line);
        // base data arraylist with rss feeds
        // object containing read lines
        this.list = new ArrayList<RssHolder>();
        this.read = read;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // set one line on the listview
        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.activity_single_line, parent, false);
        }

        RssHolder holder = list.get(position);
        if (holder != null) {
            TextView entry = (TextView) v.findViewById(R.id.rss_feed_line_entry);
            // set text and design dependen on read lines
            if (entry != null) {
                entry.setText(holder.getTitle());
                if(read.contains(holder.getTitle())){
                    entry.setBackgroundColor(ContextCompat.getColor(v.getContext(), R.color.readSite));
                    entry.setTypeface(Typeface.DEFAULT);
                } else {
                    entry.setBackgroundColor(parent.getDrawingCacheBackgroundColor());
                    entry.setTypeface(Typeface.DEFAULT_BOLD);
                }
            }
        }

        return v;
    }

    // methods changed to match list

    @Nullable
    @Override
    public RssHolder getItem(int position) {
        return this.list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public void add(RssHolder object) {
        list.add(object);
    }

    @Override
    public void addAll(Collection<? extends RssHolder> collection) {
        list.addAll(collection);
    }

    @Override
    public void addAll(RssHolder[] items) {
        for(int i = 0; i < items.length; i++){
            list.add(items[i]);
        }
    }

    @Override
    public void insert(RssHolder object, int index) {
        list.add(index, object);
    }

    @Override
    public void remove(RssHolder object) {
        list.remove(object);
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public void sort(Comparator<? super RssHolder> comparator) {
        Collections.sort(list,comparator);
    }
}
