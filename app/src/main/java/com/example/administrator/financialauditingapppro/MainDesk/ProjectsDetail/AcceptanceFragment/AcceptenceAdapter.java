package com.example.administrator.financialauditingapppro.MainDesk.ProjectsDetail.AcceptanceFragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.financialauditingapppro.R;
import com.example.administrator.financialauditingapppro.net.Beans.BeanPDAcceptance;
import com.example.administrator.financialauditingapppro.net.Beans.ProjectNotice;
import com.example.administrator.financialauditingapppro.net.DateUtil;

import java.util.ArrayList;

/**
 * Created by Administrator on 6/29/2017.
 */

public class AcceptenceAdapter extends BaseAdapter {
    private LayoutInflater inflater =null;
    BeanPDAcceptance pdAcceptance;
    Context context;
    ArrayList<ProjectNotice> notices = new ArrayList<>();

    public class Holder {
        String title;
        String Message;
        String DateValue;
        TextView acceptanceTitleTV;
        TextView acceptanceNoticeContentsTV;
        TextView acceptanceDateTV;
//        DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");
    }

    public AcceptenceAdapter(Context context, BeanPDAcceptance beanPDAcceptance){
        this.context = context;
        this.pdAcceptance = beanPDAcceptance;
        addItems();

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    private void addItems() {
        if(pdAcceptance.checknoticelist != null && pdAcceptance.checknoticelist.size()>0){
            notices.addAll(pdAcceptance.checknoticelist);
        }
        if(pdAcceptance.finishnoticelist != null && pdAcceptance.finishnoticelist.size()>0){
            notices.addAll(pdAcceptance.finishnoticelist);
        }
        if(pdAcceptance.fixnoticelist != null && pdAcceptance.fixnoticelist.size()>0){
            notices.addAll(pdAcceptance.fixnoticelist);
        }
        if(pdAcceptance.picturenopassnoticelist != null && pdAcceptance.picturenopassnoticelist.size()>0){
            notices.addAll(pdAcceptance.picturenopassnoticelist);
        }
        if(pdAcceptance.refuseprojectnoticelist != null && pdAcceptance.refuseprojectnoticelist.size()>0){
            notices.addAll(pdAcceptance.refuseprojectnoticelist);
        }

    }

    @Override
    public int getCount() {
        return  notices.size();
    }

    @Override
    public Object getItem(int position) {
        return notices.get(position);
    }

    @Override
    public long getItemId(int position) {
        return notices.get(position).id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View acceptanceListItem;
        ProjectNotice listItem = notices.get(position);



        acceptanceListItem = inflater.inflate(R.layout.layout_acceptance_list_item,null);
        final Holder holder = new Holder();
        holder.title = notices.get(position).title;
        holder.Message = notices.get(position).noticecontents;
        holder.DateValue = DateUtil.parseTimeToDate(listItem.createtime);

        holder.acceptanceTitleTV = (TextView) acceptanceListItem.findViewById(R.id.acceptanceTitleTV);
        holder.acceptanceNoticeContentsTV = (TextView) acceptanceListItem.findViewById(R.id.acceptanceNoticeContentsTV);
        holder.acceptanceDateTV = (TextView) acceptanceListItem.findViewById(R.id.acceptanceDateTV);

        holder.acceptanceTitleTV.setText(notices.get(position).title);
        holder.acceptanceNoticeContentsTV.setText(holder.Message);
        holder.acceptanceDateTV.setText(holder.DateValue);

        return acceptanceListItem;
    }
}
