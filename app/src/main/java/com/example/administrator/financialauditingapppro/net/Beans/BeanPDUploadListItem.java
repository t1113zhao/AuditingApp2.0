package com.example.administrator.financialauditingapppro.net.Beans;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Administrator on 6/19/2017.
 */

public class BeanPDUploadListItem implements Serializable {
    @SerializedName("faqentity")
    public Faqentity faqentity;
    @SerializedName("progresspiclist")
    public ArrayList<ProgressPicList> progresspiclist;
    @SerializedName("stageid")
    public int stageid;
    @SerializedName("progressid")
    public int progressid;
    @SerializedName("stagetitle")
    public String stagetitle;//标题
    @SerializedName("id")
    public int id;

    public class Faqentity implements Serializable{
        @SerializedName("descriptions")
        public String descriptions;
        @SerializedName("id")
        public int id;
        @SerializedName("displayorder")
        public int displayorder;
        @SerializedName("iscover")
        public int iscover;
        @SerializedName("picurl")
        public String picurl;
        @SerializedName("prompttext")
        public String prompttext;
        @SerializedName("stageid")
        public int stageid;
        @SerializedName("title")
        public String title;
        @SerializedName("faqpiccount")
        public int faqpiccount;
    }


}
