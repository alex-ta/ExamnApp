package com.example.user.rssreader;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.example.user.rssreader.features.RssDialog;
import com.example.user.rssreader.features.RssSharedPreferences;
import com.example.user.rssreader.features.ReadSites;
import com.example.user.rssreader.rssreader.RssHolder;
import com.example.user.rssreader.rssreader.RssReader;

import java.net.URL;

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
    private ReadSites readSites;
    private RssReader reader;
    private RssListviewAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_list);
        // create improtent instances
        // readSites stores the clicked items
        // adapter is the custom arrayadapter that creates the current list
        // progressbar shows when feeds load
        // reader reads the rss feeds
        // dialog enables url change

        this.readSites = new ReadSites();
        this.adapter = new RssListviewAdapter(this, readSites);
        ProgressBar spinner=(ProgressBar)findViewById(R.id.progressBar);
        this.reader = new RssReader(adapter, spinner);
        dialog = new RssDialog(this, readSites, reader);

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


        // the list of rss feeds and the empty view
        ListView listview = (ListView) findViewById(R.id.feed_listview);
        listview.setEmptyView(findViewById(R.id.empty_list_view));
        listview.setAdapter(adapter);

        // intent to the dateil view
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                RssHolder holder = adapter.getItem(position);
                Log.d("out", position+" "+holder.getTitle());
                readSites.addReadTitle(holder.getTitle());
                Log.d("s", readSites.getReadSitesBuffer().size()+"");
                Intent inte = new Intent(FeedListActivity.this, FeedDetailActivity.class);
                inte.putExtra(getApplicationContext().getString(R.string.intent_list_key),holder);
                startActivity(inte);
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
        readSites.setReadSitesBuffer(RssSharedPreferences.getInstance(this).getOpenedSites());
        checkEmptyUrl();
        if(!RssSharedPreferences.getInstance(this).getUrl().isEmpty()){
            ((TextView)this.findViewById(R.id.empty_list_view)).setText(this.getText(R.string.emptyList));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(adapter != null){
            adapter.notifyDataSetChanged();
        }
        if(!RssSharedPreferences.getInstance(this).getUrl().isEmpty()){
            ((TextView)this.findViewById(R.id.empty_list_view)).setText(this.getText(R.string.emptyList));
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        RssSharedPreferences.getInstance(this).saveOpenedSites(readSites.getReadSitesBuffer());
    }

    @Override
    protected void onStop() {
        super.onStop();
        RssSharedPreferences.getInstance(this).saveOpenedSites(readSites.getReadSitesBuffer());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RssSharedPreferences.getInstance(this).saveOpenedSites(readSites.getReadSitesBuffer());
    }

    private void checkEmptyUrl(){
        // checks if the url is empty, if so it shows the dialog after that load the data
        if(RssSharedPreferences.getInstance(this).getUrl().isEmpty()){
            dialog.show();
        }
        try {
            reader.start(new URL(RssSharedPreferences.getInstance(this).getUrl()));
        } catch (Exception e){
            e.printStackTrace();
        }
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