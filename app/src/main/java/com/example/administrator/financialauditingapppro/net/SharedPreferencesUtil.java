package com.example.administrator.financialauditingapppro.net;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

/**
 * Created by Administrator on 6/7/2017.
 */

public class SharedPreferencesUtil {
    public static final String SP_FILE_NAME = "aijiazhushou_sp";

    public static final String USER_INFO = "userInfo";

    public static final String BENCH_INFO = "benchInfo";

    public static final String IMAGEINFORMATION = "pdBeanImageInfo";

    public static final String EMPLOYEE_INFO = "employeeinfo";

    public static final String PROJECT_DETAILS = "projectDetails";

    public static final String PROJECT_ADD_INFO = "projectAddInfo";

    public static final String PROJECT_CONSTRUCT_INFO = "projectConstructInfo";

    public static final String PROJECT_COMPLETE_INFO = "projectCompleteInfo";

    public static final String USER_LOGIN_STATE = "userloginstate";

    public static final String PROJECT_ACCEPT_RECORD = "projectacceptrecord";

    public static boolean saveUserLoginState(Context context, boolean flag){
        SharedPreferences sp= context.getSharedPreferences(SP_FILE_NAME, Context.MODE_PRIVATE);
        return sp.edit().putBoolean(USER_LOGIN_STATE, flag).commit();
    }

    public static boolean getUserLoginState(Context context){
        SharedPreferences sp= context.getSharedPreferences(SP_FILE_NAME, Context.MODE_PRIVATE);
        return sp.getBoolean(USER_LOGIN_STATE, false);
    }

    public static boolean saveInSharedPreferences(Context context,Object object,String key){
        SharedPreferences sp= context.getSharedPreferences(SP_FILE_NAME, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String value = gson.toJson(object);
        return sp.edit().putString(key, value).commit();
    }

    public static boolean saveProjectAcceptInSharedPreferences(Context context,Object object,String key,String fileName){
        SharedPreferences sp= context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String value = gson.toJson(object);
        return sp.edit().putString(key, value).commit();
    }

    public static String getProjectAcceptJsonString(Context context,String key,String fileName){
        SharedPreferences sp= context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        return sp.getString(key, null);
    }

    public static void clearSp(Context context){
        SharedPreferences sp= context.getSharedPreferences(SP_FILE_NAME, Context.MODE_PRIVATE);
        sp.edit().clear().apply();
    }


    public static String getJsonString(Context context,String key){
        SharedPreferences sp= context.getSharedPreferences(SP_FILE_NAME, Context.MODE_PRIVATE);
        return sp.getString(key, null);
    }
}
