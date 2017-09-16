package com.example.administrator.financialauditingapppro.MainDesk.ProjectsDetail;

import android.net.Uri;

import com.google.gson.annotations.SerializedName;

import java.net.URI;

import static android.R.attr.password;

/**
 * Created by Administrator on 6/27/2017.
 */

public class UploadPostBody {
//"customerid":"0001c8b8-8a51-4fb9-afd2-9d380033ad4c",
//        "pictures":["20170626155717312718381.jpg"],
//        "progressid":"42603",
//        "stageid":"9",
//        "stagename":"现场成品保护
//    version":"v1.0"

    @SerializedName("customerid")
    public String customerid;

    @SerializedName("pictures")
    public String[] pictures;

    @SerializedName("progressid")
    public int progressid;

    @SerializedName("stageid")
    public int stageid;

    @SerializedName("stagename")
    public String stagename;

    @SerializedName("version")
    public String version;

    public UploadPostBody() {
    }
}
