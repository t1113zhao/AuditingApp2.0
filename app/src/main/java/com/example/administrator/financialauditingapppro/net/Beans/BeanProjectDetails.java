package com.example.administrator.financialauditingapppro.net.Beans;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Administrator on 6/15/2017.
 */

public class BeanProjectDetails {

    @SerializedName ("address")
    public String address;
    @SerializedName ("buildingarea")
    public double buildingarea;
    @SerializedName ("corpid")
    public String corpid;
    @SerializedName ("corpname")
    public String corpname;
    @SerializedName ("corpphone")
    public String corpphone;
    @SerializedName ("customerid")
    public String customerid;
    @SerializedName ("designername")
    public String designername;
    @SerializedName ("discountMoney")
    public double discountMoney;
    @SerializedName ("exmaterialmoney")
    public double exmaterialmoney;
    @SerializedName ("exprojectmoney")
    public double exprojectmoney;
    @SerializedName ("furpkgmoney")
    public double furpkgmoney;
    @SerializedName ("housestructure")
    public String housestructure;
    @SerializedName ("id")
    public int id;

    public class Transaction implements Serializable{

        @SerializedName ("fundation")
        public double fundation;
        @SerializedName ("paytime")
        public String paytime;
        @SerializedName ("requesttype")
        public String requesttype;
    }

    @SerializedName ("incomeactivity")
    public Transaction[] incomeactivity;


    @SerializedName ("incrementtotalmoney")
    public double incrementtotalmoney;
    @SerializedName ("logpic")
    public String logpic;
    @SerializedName ("name")
    public String name;
    @SerializedName ("packagetotalmoney")
    public double packagetotalmoney;
    @SerializedName ("pcmaterialmoney")
    public double pcmaterialmoney;
    @SerializedName ("personalexmaterialmoney")
    public double personalexmaterialmoney;
    @SerializedName ("personalexprojectmoney")
    public double personalexprojectmoney;
    @SerializedName ("phone")
    public String phone;
    @SerializedName ("pkgoutexprojectmanagemoney")
    public double pkgoutexprojectmanagemoney;
    @SerializedName ("pkgoutexprojectmoney")
    public double pkgoutexprojectmoney;
    @SerializedName ("procode")
    public String procode;
    @SerializedName ("progressid")
    public int progressid;
    @SerializedName ("progressname")
    public String progressname;
    @SerializedName ("projectdays")
    public int projectdays;
    @SerializedName ("projectdelaydays")
    public int projectdelaydays;
    @SerializedName ("projectendtime")
    public String projectendtime;
    @SerializedName ("projectstarttime")
    public String projectstarttime;
    @SerializedName ("projectstate")
    public String projectstate;
    @SerializedName ("projecttotalmoney")
    public double projecttotalmoney;
    @SerializedName ("requestmoney")
    public double[] requestmoney;
    @SerializedName ("totalmoney")
    public double totalmoney;
}
