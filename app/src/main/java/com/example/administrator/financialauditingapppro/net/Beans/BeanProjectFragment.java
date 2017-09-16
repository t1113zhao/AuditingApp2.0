package com.example.administrator.financialauditingapppro.net.Beans;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Administrator on 6/13/2017.
 */

public class BeanProjectFragment {

//"currentpageindex":1,
//        "hasnextpage":false,
//        "haspreviouspage":false,
//        "pagesize":10,
//        "totalitemcount":7,
//        "totalpagecount":1,
//
//        "pagedata":[{
//        "address":"上海上海徐汇区平福路188",
//                "corpid":"0fc56498-3fb5-43d2-a8d0-54ab0b10df81",
//                "corpname":"wjp更换的施工方",
//                "customerid":"7070CDF0-09BB-49D2-913C-5DD387BA764C",
//                "designername":"签约设计师7",
//                "id":1038,
//                "name":"订制品验收测试06",
//                "procode":"0021267453",
//                "progressid":0,
//                "progressname":null,
//                "projectstate":"0"},

    @SerializedName("currentpageindex")
    public int currentpageindex;
    @SerializedName("hasnextpage")
    public boolean hasnextpage;
    @SerializedName("haspreviouspage")
    public boolean haspreviouspage;
    @SerializedName("pagesize")
    public int pagesize;
    @SerializedName("totalitemcount")
    public int totalitemcount;
    @SerializedName("totalpagecount")
    public int totalpagecount;
    @SerializedName("pagedata")
    public ArrayList<ProjectInformation> pagedata;



    public class ProjectInformation implements Serializable{
        @SerializedName("address")
        public String address;
        @SerializedName("corpid")
        public String corpid;
        @SerializedName("corpname")
        public String corpname;
        @SerializedName("customerid")
        public String customerid;
        @SerializedName("designername")
        public String designername;
        @SerializedName("id")
        public int id;
        @SerializedName("name")
        public String name;
        @SerializedName("procode")
        public int procode;
        @SerializedName("progressid")
        public int progressid;
        @SerializedName("progressname")
        public String progressname;
        @SerializedName("projectstate")
        public int projectstate;

    }



}
