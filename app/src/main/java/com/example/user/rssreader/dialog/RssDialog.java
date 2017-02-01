package com.example.user.rssreader.dialog;

import android.app.Dialog;
import android.content.Context;
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

    private final EditText edit;
    private final TextView text;
    private final TextView error;
    private final Button ok;
    private final Button cancel;
    private final RssReader reader;

    public RssDialog(final Context ctx, RssReader _reader){
        super(ctx);
        this.reader = _reader;
        this.setTitle(R.string.title_rss_dialog);
        this.setContentView(R.layout.dialog_rss);
        this.text = (TextView) this.findViewById(R.id.dialog_title);
        this.edit = (EditText) this.findViewById(R.id.dialog_text);
        this.error = (TextView) this.findViewById(R.id.dialog_error);
        this.ok = (Button) this.findViewById(R.id.dialog_ok);

        this.ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!edit.getText().toString().isEmpty()) {
                    try {
                        URL url = new URL(edit.getText().toString());
                        error.setVisibility(View.GONE);
                        RssSharedPreferences.getInstance(ctx).saveUrl(edit.getText().toString());
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
        this.cancel = (Button) this.findViewById(R.id.dialog_cancel);
        this.cancel.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                error.setVisibility(View.GONE);
                hide();
            }
        });
    }

    @Override
    public void show() {
        super.show();
    }
}
