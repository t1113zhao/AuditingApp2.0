package com.example.administrator.financialauditingapppro.net.Beans;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Administrator on 6/29/2017.
 */

public class BeanCheckComment implements Serializable {
    @SerializedName("contents")
    public String contents;
    @SerializedName("createtime")
    public String createtime;
    @SerializedName("displayorder")
    public int displayorder;
    @SerializedName("id")
    public int id;
    @SerializedName("progressid")
    public int progressid;
    @SerializedName("score")
    public float score;
    @SerializedName("evaluationpiclist")
    public ArrayList<EvaluationPicture> evaluationpiclist;//评论图列表

}
