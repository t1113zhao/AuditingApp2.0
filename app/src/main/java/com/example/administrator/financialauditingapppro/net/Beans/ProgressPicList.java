package com.example.administrator.financialauditingapppro.net.Beans;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Administrator on 6/9/2017.
 */

public class ProgressPicList implements Serializable {
    @SerializedName("id")
    public int id ;
    @SerializedName("progressid")
    public int progressid;
    @SerializedName("stageid")
    public int stageid;
    @SerializedName("picurl")
    public String picurl;
    @SerializedName("state")
    public int state;
    @SerializedName("auditingman")
    public String auditingman;
    @SerializedName("auditingtime")
    public String auditingtime;
    @SerializedName("title")
    public String title;
    @SerializedName("displayorder")
    public String displayorder;
}