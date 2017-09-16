package com.example.administrator.financialauditingapppro.MainDesk.Bench;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.administrator.financialauditingapppro.MainDesk.MainActivity;
import com.example.administrator.financialauditingapppro.R;
import com.example.administrator.financialauditingapppro.net.Beans.ProjectNotice;

import java.util.ArrayList;

/**
 * Created by Administrator on 6/9/2017.
 */

public class BenchProcessedAdapter extends BaseAdapter{

    ArrayList<ProjectNotice> projectNotices;
    Context activity;
    private static LayoutInflater inflater = null;
    public BenchProcessedAdapter(Context context, ArrayList<ProjectNotice> projectNotices){
        this.activity = context;
        this.projectNotices = projectNotices;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


    }

    @Override
    public int getCount() {
        return projectNotices.size();
    }

    @Override
    public Object getItem(int position) {
        return projectNotices.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View processingListItem;

        processingListItem = inflater.inflate(R.layout.layout_processed_list_item,null);
        return processingListItem;
    }
}
