package com.example.administrator.financialauditingapppro.net;

/**
 * Created by Administrator on 6/7/2017.
 */

public class ProjectURLS {

    public final static String BASEURL = " http://test9.525j.com.cn/";
    public final static String COMMON_EXTENSION ="common%2Fpassportapi/";
    public final static String APP_API_EXTENSION = "app%2Fassistantapi/";
    public final static  String VERSION_NUMBER = "v1.0";
    public final static String LOGIN_EXTENSION = "passport.login";
    public final static String APP = "app/";
    public final static String ASSISTANT_EXTENSION = "assistantapi";
    public final static String COM_EXTENSION = "comapi";
    public final static String BENCH_EXTENSION = "assistant.projectprogressstagenoticelist/";
    public final static String PROJECTPICTURE_EXTENSION ="assistant.progressstagepicture/";
    public final static String PROJECYTPROGRESSCHECK = "assistant.projectprogressstagechecknotice/";
    public final static String GETSAMPLEPICTURE_EXTENSION = "assistant.getsamplestagepicture/";
    public final static String UPLOADPICTURE_EXTENSION = "assistant.uploadpicture";
    public final static String DELETEPICTURE_EXTENSION = "assistant.deletepicture";
    public final static String PREUPLOAD_EXTENSION ="com.uploadfiles";
//
//    http://test9.525j.com.cn/app/assistantapi/v1.0/assistant.projectprogressstagenoticelist/0fc56498-3fb5-43d2-a8d0-54ab0b10df81/0?needcache=1


    public String getloginURL(){
        return BASEURL + COMMON_EXTENSION + VERSION_NUMBER +"/"  + LOGIN_EXTENSION;
    }

//    public String get

}
