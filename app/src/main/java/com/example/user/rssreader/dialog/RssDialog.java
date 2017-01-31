package com.example.user.rssreader.dialog;

import android.app.Dialog;
import android.content.Context;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.user.rssreader.R;

/**
 * Created by User on 31.01.2017.
 */

public class RssDialog extends Dialog{

    private EditText edit;
    private TextView text;
    private Button ok;
    private Button cancle;


    public RssDialog(Context ctx){
        super(ctx);
        this.setTitle(R.string.title_rss_dialog);
        this.setContentView(R.layout.dialog_rss);
        this.text = (TextView) this.findViewById(R.id.dialog_title);
        this.edit = (EditText) this.findViewById(R.id.dialog_text);
        this.ok = (Button) this.findViewById(R.id.dialog_ok);
        this.cancle = (Button) this.findViewById(R.id.dialog_cancel);
    }

    @Override
    public void show() {
        super.show();
    }
}
