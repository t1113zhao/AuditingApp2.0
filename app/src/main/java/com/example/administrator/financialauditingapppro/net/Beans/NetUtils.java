package com.example.administrator.financialauditingapppro.net.Beans;

import com.google.gson.Gson;
//import com.lovehome.android.aijiazhusou.bean.BeanBase;

import java.lang.reflect.Type;


public class NetUtils {
	
	public static BeanBase getResponseBean(Type tp, String str){
		Gson gson = new Gson();
		BeanBase beanBase = null;
		try {
			beanBase= gson.fromJson(str, tp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return beanBase;
	}

}
