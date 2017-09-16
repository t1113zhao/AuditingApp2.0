package com.example.administrator.financialauditingapppro.net.Beans;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Administrator on 6/9/2017.
 */

public class CheckPicture implements Serializable {
    @SerializedName("checkid")
    public int checkid;
    @SerializedName("displayorder")
    public int displayorder;
    @SerializedName("id")
    public int id;
    @SerializedName("picurl")
    public String picurl;
    @SerializedName("title")
    public String title;

}
