package com.example.administrator.financialauditingapppro.net.Beans;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Administrator on 6/29/2017.
 */

public class BeanPDAcceptance {
    @SerializedName("projectstagestate")
    public ProjectStageState projectstagestate;
    @SerializedName("fixnoticelist")
    public ArrayList<ProjectNotice> fixnoticelist;//整改通知列表
    @SerializedName("checknoticelist")
    public ArrayList<ProjectNotice> checknoticelist;//验收通知列表
    @SerializedName("finishnoticelist")
    public ArrayList<ProjectNotice> finishnoticelist;//完工通知列表
    @SerializedName("checkcommentlist")
    public ArrayList<BeanCheckComment> checkcommentlist;//评论列表,只有一项，放在最上边
    @SerializedName("picturenopassnoticelist")
    public ArrayList<ProjectNotice> picturenopassnoticelist;//传图通知
    @SerializedName("refuseprojectnoticelist")
    public ArrayList<ProjectNotice> refuseprojectnoticelist;//拒绝通知

    //refuseprojectnotices

    /**
     * 项目阶段状态实体
     *
     * @author lufeng
     */
    public class ProjectStageState {
        @SerializedName("auditingstate")
        public int auditingstate;//当前阶段验收记录是否审核通过  0:待审核 1:审核通过 2:不通过
        @SerializedName("checktime")
        public String checktime;//验收时间
        @SerializedName("endtime")
        public String endtime;//
        @SerializedName("finishtime")
        public String finishtime;//结束时间
        @SerializedName("ischeck")
        public int ischeck;//当前阶段用户是否已申请验收 0:否 1:是
        @SerializedName("isfinish")
        public int isfinish;//当前阶段是否已完工 0:否 1:是
        @SerializedName("ispay")
        public int ispay;//当前阶段是否可以请款 0:否 1:是
        @SerializedName("isreview")
        public int isreview;//当前阶段用户是否已评价 0:否 1:是


        /**
         * 0、没有支付
         * 1、已支付
         */
        @SerializedName("paid")
        public int paid;//当前阶段用户是否确认付款 0:否 1:是
        @SerializedName("projectid")
        public int projectid;
        @SerializedName("progressid")
        public int progressid;
        @SerializedName("requestid")
        public int requestid;
        /**
         * 1、验收通过
         * 2、未通过
         * 0、等待审核
         */
        @SerializedName("stageid")
        public int stageid;//代表当前阶段1------6
        @SerializedName("stagestate")
        public int stagestate;// 阶段状态 0 未开始 ； 1 施工中 2 施工完成
        @SerializedName("starttime")
        public String starttime;
        @SerializedName("auditingtime")
        public String auditingtime;//监理最后一次审核时间
        @SerializedName("lastuploadpictime")
        public String lastuploadpictime;//最后一次传图时间
        @SerializedName("requeststate")
        public String requeststate;//用户验收的结果，4为用户拒绝付款
    }
}
