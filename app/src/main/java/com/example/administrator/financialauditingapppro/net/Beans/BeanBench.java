package com.example.administrator.financialauditingapppro.net.Beans;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Administrator on 6/9/2017.
 */

public class BeanBench {
    public class UploadPicNotice implements Serializable{

        @SerializedName("address")
        public String address;
        @SerializedName("id")
        public int id;
        @SerializedName("msg")
        public String msg;
        @SerializedName("noticetime")
        public String noticetime;
        @SerializedName("projectid")
        public int projectid;
        @SerializedName("stageid")
        public int stageid;
        @SerializedName("stagename")
        public String stagename;
    }

    @SerializedName("picnotice")
    public ArrayList<UploadPicNotice> picnotice;
    @SerializedName("projectnotices")
    public ArrayList<ProjectNotice> projectnotices;
    @SerializedName("picturenopasslist")
    public ArrayList<ProjectNotice> picturenopasslist;
}
