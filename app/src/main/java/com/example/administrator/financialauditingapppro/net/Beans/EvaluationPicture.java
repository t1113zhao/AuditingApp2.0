package com.example.administrator.financialauditingapppro.net.Beans;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Administrator on 6/29/2017.
 */

public class EvaluationPicture implements Serializable {
    /// <summary>
    /// 用户评价图片ID
    /// </summary>
    @SerializedName("id")
    public int id ;

    /// <summary>
    /// 用户评价id
    /// </summary>
    @SerializedName("commentid")
    public int commentid ;

    /// <summary>
    /// 图片地址
    /// </summary> \
    @SerializedName("picurl")
    public String picurl ;

    /// <summary>
    /// 标题
    /// </summary>
    @SerializedName("title")
    public String title ;

    /// <summary>
    /// 排序id
    /// </summary>
    @SerializedName("displayorder")
    public int displayorder ;

}
