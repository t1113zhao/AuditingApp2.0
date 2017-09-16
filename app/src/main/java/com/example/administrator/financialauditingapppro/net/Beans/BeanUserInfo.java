package com.example.administrator.financialauditingapppro.net.Beans;

import android.text.TextUtils;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Administrator on 6/7/2017.
 */

public class BeanUserInfo {

    @SerializedName("cityid")
    public String cityid;

    @SerializedName("customerid")
    public String customerid;

    @SerializedName("customerpic")
    public String customerpic;

    @SerializedName("foremanid")
    public String foremanid;

    @SerializedName("foremanname")
    public String foremanname;

    @SerializedName("foremanpic")
    public String foremanpic;

    @SerializedName("isopenlock")
    public int isopenlock;

    @SerializedName("isorder")
    public int isorder;

    @SerializedName("isreservation")
    public String isreservation;

    @SerializedName("logonname")
    public String logonname;

    @SerializedName("phone")
    public String phone;

    @SerializedName("projectcount")
    public int projectcount;

    @SerializedName("projectid")
    public int projectid;

    @SerializedName("realname")
    public String realname;

    @SerializedName("registerationid")
    public String registerationid;

    @SerializedName("rolename")
    public String rolename;

    @SerializedName("stars")
    public int stars;

    @SerializedName("telephone")
    public String telephone;
}
