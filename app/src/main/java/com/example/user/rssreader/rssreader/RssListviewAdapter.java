package com.example.user.rssreader.rssreader;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.user.rssreader.R;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by User on 31.01.2017.
 */

public class RssListviewAdapter extends ArrayAdapter<RssHolder>{

    private ArrayList<RssHolder> list;
    public RssListviewAdapter(Context context) {
        super(context, R.layout.activity_single_line);
        this.list = new ArrayList<RssHolder>();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.activity_single_line, parent, false);
        }

        RssHolder holder = list.get(position);
        if (holder != null) {
            TextView entry = (TextView) v.findViewById(R.id.rss_feed_line_entry);

            if (entry != null) {
                entry.setText(holder.getTitle());
            }
        }

        return v;
    }

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
