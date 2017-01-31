package com.example.user.rssreader;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.user.rssreader.rssreader.RssHolder;

/**
 * An activity representing a single Feed detail screen. This
 * activity is only used narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link FeedListActivity}.
 */
public class FeedDetailActivity extends AppCompatActivity {

    private TextView date, title, description, link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RssHolder holder = (RssHolder) this.getIntent().getSerializableExtra(getApplicationContext().getString(R.string.intent_list_key));
        setContentView(R.layout.activity_feed_detail);
        this.date = (TextView) this.findViewById(R.id.detail_date);
        this.title = (TextView) this.findViewById(R.id.detail_title);
        this.description = (TextView) this.findViewById(R.id.detail_description);
        this.link = (TextView) this.findViewById(R.id.detail_link);

        this.date.setText(holder.getDate().toString());
        this.title.setText(holder.getTitle());
        this.description.setText(holder.getDescription());
        this.link.setText(holder.getLink());
    }
}
