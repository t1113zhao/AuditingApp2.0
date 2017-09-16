package com.example.administrator.financialauditingapppro.net.Beans;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Administrator on 6/9/2017.
 */

public class ProjectNotice implements
//        Comparable<ProjectNotice>,
        Serializable {
    @SerializedName("id")
    public int id;
    @SerializedName("stageid")
    public int stageid;
    @SerializedName("checkid")
    public int checkid;
    @SerializedName("progressid")
    public int progressid;
    @SerializedName("creatorid")
    public String creatorid;
    @SerializedName("templateid")
    public int templateid;
    @SerializedName("projectid")
    public int projectid;
    @SerializedName("typeid")
    public int typeid;
    @SerializedName("address")
    public String address;
    @SerializedName("stagename")
    public String stagename;
    @SerializedName("createtime")
    public String createtime;
    @SerializedName("noticecontents")
    public String noticecontents;
    @SerializedName("realname")
    public String realname;
    @SerializedName("title")
    public String title;
    @SerializedName("checkpiclist")
    public ArrayList<CheckPicture> checkpiclist;
    @SerializedName("picturenopasslist")
    public ArrayList<ProgressPicList>	picturenopasslist;
    @SerializedName("refusepaypiclist")
    public String[]	refusepaypiclist;



//    @Override
//    public int compareTo(ProjectNotice another) {
//        long time2=DateUtil.parseDateTime(another.createtime).getTime();
//        long time1=DateUtil.parseDateTime(createtime).getTime();
//        if(time2>time1)return 1;
//        else if(time2<time1)return  -1;
//        else return  0;
////		return (int)(time2 - time1);
//    }

}
