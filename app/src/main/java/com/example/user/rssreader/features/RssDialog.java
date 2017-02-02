package com.example.user.rssreader.features;

import android.app.Activity;
import android.app.Dialog;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.user.rssreader.R;
import com.example.user.rssreader.rssreader.RssReader;

import java.net.URL;

/**
 * Created by User on 31.01.2017.
 */

public class RssDialog extends Dialog{

    private EditText edit;
    private TextView text;
    private TextView error;
    private Button ok;
    private Button cancel;
    private RssReader reader;
    private ReadSites read;
    private RssSharedPreferences prev;

    /**
     * Dialog prompt for url
     * */

    public RssDialog(final Activity ctx, final ReadSites read, RssReader _reader){
        super(ctx);

        // set dialog layout
        this.read = read;
        this.reader = _reader;
        this.setTitle(R.string.title_rss_dialog);
        this.setContentView(R.layout.dialog_rss);
        this.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        this.prev = RssSharedPreferences.getInstance(ctx);

        this.text = (TextView) this.findViewById(R.id.dialog_title);
        this.edit = (EditText) this.findViewById(R.id.dialog_text);

        // set existing url
        String url = prev.getUrl();
        if(!url.isEmpty()) {
            this.edit.setText(url);
        }

        this.error = (TextView) this.findViewById(R.id.dialog_error);
        this.ok = (Button) this.findViewById(R.id.dialog_ok);

        // check url and set it in preferences
        this.ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!edit.getText().toString().isEmpty()) {
                    try {
                        URL url = new URL(edit.getText().toString());
                        error.setVisibility(View.GONE);
                        if(!edit.getText().toString().equals(prev.getUrl())) {
                            read.clearReadTitles();
                        }
                        prev.saveUrl(edit.getText().toString());
                        ((TextView)ctx.findViewById(R.id.empty_list_view)).setText(ctx.getText(R.string.emptyList));
                        reader.start(url);
                        hide();
                    } catch (Exception e) {
                        e.printStackTrace();
                        error.setText(R.string.dialog_error_malformedurl);
                        error.setVisibility(View.VISIBLE);
                    }
                } else{
                    error.setText(R.string.error_empty_string);
                    error.setVisibility(View.VISIBLE);
                }

            }
        });
        // exits dialog and prompts toast on url error
        this.cancel = (Button) this.findViewById(R.id.dialog_cancel);
        this.cancel.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                error.setVisibility(View.GONE);
                hide();
                if(prev.getUrl().isEmpty()){
                    new RssToast(ctx).setText(ctx.getString(R.string.cancel_dialog)).show();
                }
                // extra check
                try{
                    URL url = new URL(edit.getText().toString());
                } catch (Exception e){
                    e.printStackTrace();
                    new RssToast(ctx).setText(ctx.getString(R.string.dialog_warning_malformed)).show();
                }

            }
        });
    }
}
