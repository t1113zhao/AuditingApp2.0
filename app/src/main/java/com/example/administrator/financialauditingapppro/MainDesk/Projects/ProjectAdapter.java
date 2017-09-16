package com.example.administrator.financialauditingapppro.MainDesk.Projects;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.financialauditingapppro.MainDesk.ProjectsDetail.ProjectDetailsActivity;
import com.example.administrator.financialauditingapppro.R;
import com.example.administrator.financialauditingapppro.net.Beans.BeanProjectFragment;

import java.util.ArrayList;

import static android.R.attr.start;

/**
 * Created by Administrator on 6/14/2017.
 */

public class ProjectAdapter extends BaseAdapter implements View.OnClickListener{
    ArrayList<BeanProjectFragment.ProjectInformation> projectInformations;

    BeanProjectFragment.ProjectInformation information;
    Context mContext;
    private LayoutInflater inflater = null;

    public ProjectAdapter(Context context ,ArrayList<BeanProjectFragment.ProjectInformation> projectInformations) {
        this.projectInformations = projectInformations;
        mContext = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return projectInformations.size();
    }

    @Override
    public Object getItem(int position) {
        return projectInformations.get(position);
    }

    @Override
    public long getItemId(int position) {
        return projectInformations.get(position).id;
    }


    public class Holder{
        ImageView pFListItemIV;

        LinearLayout projectFragmentListItem;

        TextView pFProIDTV;
        TextView pFLIOwnerTV;
        TextView pFLIAddressTV;
        TextView pFLICorpNameTV;
        TextView pFLIDesignerNameTV;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final Holder holder = new Holder();
        View projectAddListItem;
        BeanProjectFragment.ProjectInformation projectInformation = projectInformations.get(position);

        projectAddListItem = inflater.inflate(R.layout.layout_project_fragment_list_item,null);

        holder.pFListItemIV = (ImageView) projectAddListItem.findViewById(R.id.pFListItemIV);
        holder.projectFragmentListItem = (LinearLayout) projectAddListItem.findViewById(R.id.projectFragmentListItem);
        holder.pFProIDTV = (TextView) projectAddListItem.findViewById(R.id.pFProIDTV);
        holder.pFLIOwnerTV = (TextView) projectAddListItem.findViewById(R.id.pFLIOwnerTV);
        holder.pFLIAddressTV = (TextView) projectAddListItem.findViewById(R.id.pFLIAddressTV);
        holder.pFLICorpNameTV = (TextView) projectAddListItem.findViewById(R.id.pFLICorpNameTV);
        holder.pFLIDesignerNameTV = (TextView) projectAddListItem.findViewById(R.id.pFLIDesignerNameTV);

        holder.pFProIDTV.setText("ProID: "+projectInformation.procode+"");
        holder.pFLIOwnerTV.setText("Owner: " + projectInformation.name+"");
        holder.pFLIAddressTV.setText("Address: "+projectInformation.address+"");
        holder.pFLICorpNameTV.setText("Corp Name: "+projectInformation.corpname+"");
        holder.pFLIDesignerNameTV.setText("Designer Name: "+projectInformation.designername+"");

        information = projectInformation;
        holder.projectFragmentListItem.setTag(projectInformation.id);
        holder.projectFragmentListItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ProjectDetailsActivity.class);
                intent.putExtra("ID", holder.projectFragmentListItem.getTag().toString());
//                intent.putExtra("SENTFROMBENCH",false);
//                intent.putExtra("PROJECT",information );
//                Log.d("WTF", information.id+"");
//        Toast.makeText(mContext, ""+information.id + ":" + information.address, Toast.LENGTH_SHORT).show();
                mContext.startActivity(intent);
            }
        });


        return projectAddListItem;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(mContext, ProjectDetailsActivity.class);
        intent.putExtra("SENTFROMBENCH",false);
        intent.putExtra("PROJECT",information );
        Log.d("WTF", information.id+"");
//        Toast.makeText(mContext, ""+information.id + ":" + information.address, Toast.LENGTH_SHORT).show();
        mContext.startActivity(intent);
//        Toast.makeText(mContext, "THIS DONT DO NOTHING YET", Toast.LENGTH_SHORT).show();
    }
}
