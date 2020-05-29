package com.example.homepage.activity;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.homepage.R;

public class NotificationAdapter extends ArrayAdapter<Integer> {
    private final Activity context;
    private final Integer[] title, content;
    private final Integer[] imgid;

    public NotificationAdapter(Activity context, Integer[] title, Integer[] content, Integer[] imgid) {
        super(context, R.layout.notification, title);
        this.context = context;
        this.title = title;
        this.content = content;
        this.imgid = imgid;
    }

    public View getView (int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View row = inflater.inflate(R.layout.notification, null, true);
        TextView tvTitle = row.findViewById(R.id.tvNotiItemTitle);
        TextView tvContent = row.findViewById(R.id.tvNotiItemContent);
        ImageView imgNoti = row.findViewById(R.id.imgNotiItem);
        tvTitle.setText(title[position]);
        tvContent.setText(content[position]);
        imgNoti.setImageResource(imgid[position]);
        return row;
    }
}
