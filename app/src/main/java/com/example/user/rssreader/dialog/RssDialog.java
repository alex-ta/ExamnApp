package com.example.user.rssreader.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.user.rssreader.R;

/**
 * Created by User on 31.01.2017.
 */

public class RssDialog extends Dialog{

    private final EditText edit;
    private final TextView text;
    private final Button ok;
    private final Button cancel;


    public RssDialog(final Context ctx){
        super(ctx);
        this.setTitle(R.string.title_rss_dialog);
        this.setContentView(R.layout.dialog_rss);
        this.text = (TextView) this.findViewById(R.id.dialog_title);
        this.edit = (EditText) this.findViewById(R.id.dialog_text);
        this.ok = (Button) this.findViewById(R.id.dialog_ok);

        this.ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new RssSharedPreferences(ctx).saveUrl(edit.getText().toString());
                hide();
            }
        });
        this.cancel = (Button) this.findViewById(R.id.dialog_cancel);
        this.cancel.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                hide();
            }
        });
    }

    @Override
    public void show() {
        super.show();
    }
}
