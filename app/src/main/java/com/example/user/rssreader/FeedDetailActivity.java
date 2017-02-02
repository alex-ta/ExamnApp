package com.example.user.rssreader;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
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
    private Context ctx;

    /**
     * Displays on rss item
     *
     * */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // read the item from intent
        final RssHolder holder = (RssHolder) this.getIntent().getSerializableExtra(getApplicationContext().getString(R.string.intent_list_key));
        ctx = this;
        setContentView(R.layout.activity_feed_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());
        toolbar.setLogo(R.mipmap.ic_launcher);

        this.date = (TextView) this.findViewById(R.id.detail_date);
        this.title = (TextView) this.findViewById(R.id.detail_title);
        this.description = (TextView) this.findViewById(R.id.detail_description);
        this.link = (TextView) this.findViewById(R.id.detail_link);

        // fill the view with data
        this.date.setText(holder.getDate().toString());
        this.title.setText(holder.getTitle());
        this.description.setText(holder.getDescription());
        this.link.setPaintFlags(this.link.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        this.link.setText(holder.getLink());

        // start browser on link click
        this.link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                link.setTextColor(ContextCompat.getColor(ctx,R.color.linkColorUsed));
                String url = holder.getLink();
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
    }
}
