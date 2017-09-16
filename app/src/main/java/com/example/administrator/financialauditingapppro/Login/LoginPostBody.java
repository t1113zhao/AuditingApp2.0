package com.example.administrator.financialauditingapppro.Login;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 6/7/2017.
 */

class LoginPostBody {

    @SerializedName("loginname")
    public String loginname;

    @SerializedName("password")
    public String password;

    @SerializedName("smscode")
    public String smscode ="";

    @SerializedName("source")
    public String source="android";

    public LoginPostBody(String loginname, String password) {
        this.loginname = loginname;
        this.password = password;
    }
}
