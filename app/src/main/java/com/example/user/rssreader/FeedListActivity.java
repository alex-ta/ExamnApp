package com.example.user.rssreader;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ListViewCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;


import com.example.user.rssreader.dialog.RssDialog;
import com.example.user.rssreader.rssreader.AsyncReader;
import com.example.user.rssreader.rssreader.Reader;
import com.example.user.rssreader.rssreader.RssHolder;
import com.example.user.rssreader.rssreader.RssListviewAdapter;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * An activity representing a list of Feeds. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link FeedDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class FeedListActivity extends AppCompatActivity {

    private RssDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_list);
        dialog = new RssDialog(this);

        RssHolder h = new RssHolder();
        h.setDescription("Some Description");
        h.setLink("www.google.de");
        h.setTitle("Some Title");
        new AsyncReader().execute("http://rss.nytimes.com/services/xml/rss/nyt/Politics.xml");

        final RssListviewAdapter adapter = new RssListviewAdapter(this);
        adapter.add(h);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });
        toolbar.setLogo(R.mipmap.ic_launcher);

        ListView listview = (ListView) findViewById(R.id.feed_listview);
        listview.setAdapter(adapter);


        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                RssHolder holder = adapter.getItem(position);
                Intent inte = new Intent(FeedListActivity.this, FeedDetailActivity.class);
                inte.putExtra(getApplicationContext().getString(R.string.intent_list_key),holder);
                startActivity(inte);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.rss_menu){
                dialog.show();
        }
        return super.onOptionsItemSelected(item);
    }
}