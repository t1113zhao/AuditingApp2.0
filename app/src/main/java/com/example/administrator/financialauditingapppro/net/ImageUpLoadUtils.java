//package com.example.administrator.financialauditingapppro.net;
//
//import android.app.Activity;
//import android.util.Log;
//import android.widget.Toast;
//
////import com.android.core.util.ToastUtil;
//import com.android.volley.Response;
//import com.android.volley.Response.ErrorListener;
//import com.android.volley.Response.Listener;
//import com.android.volley.VolleyError;
//import com.example.administrator.financialauditingapppro.net.Beans.BeanBase;
//import com.example.administrator.financialauditingapppro.net.Beans.NetUtils;
//import com.example.administrator.financialauditingapppro.net.util.ToastUtil;
//import com.google.gson.Gson;
//import com.google.gson.reflect.TypeToken;
////import com.lovehome.android.aijiazhusou.AijiaApplication;
////import com.lovehome.android.aijiazhusou.bean.BeanBase;
////import com.lovehome.android.aijiazhusou.common.staticfield.StaticStringField;
////import com.lovehome.android.net.BaseStringRequset;
////import com.lovehome.android.net.MultipartRequest;
////import com.lovehome.android.net.NetUtils;
//import com.nostra13.universalimageloader.utils.L;
//
//import java.io.File;
//import java.lang.reflect.Type;
//import java.util.ArrayList;
//import java.util.Map;
//
//public class ImageUpLoadUtils {
//
//	private static final String imageUpLoadUrl = StaticStringField.BASE_URL+"/app/com.uploadfiles";
//
//	public static void upLoadPicture(final ErrorListener errorListener,
//                                     final Response.Listener<String> listener, String filePartName,
//                                     ArrayList<File> files, Map<String, String> params,
//                                     final String[] urlParams,
//                                     final String[] paramsTwos,
//                                     Activity cActivity) {
//
//		MultipartRequest request = new MultipartRequest(imageUpLoadUrl,
//				new ErrorListener() {
//					@Override
//					public void onErrorResponse(VolleyError error) {
//						VolleyErrorUtils.showError(error);
//						AijiaApplication.getInstance().dismissLoadingDialog();
//						ToastUtil.show("上传失败");
//					}
//				}, new Response.Listener<String>() {
//					@Override
//					public void onResponse(String response) {
////						AijiaApplication.getInstance().dismissLoadingDialog();
//						L.i("response == " + response);
//						BeanBase<ArrayList<PicInfo>> beanBase = new BeanBase<ArrayList<PicInfo>>();
//						Type tp = new TypeToken<BeanBase<ArrayList<PicInfo>>>() {
//						}.getType();
//						beanBase = NetUtils.getResponseBean(tp, response);
//						if(beanBase != null){
//							if ("200".equals(beanBase.code)) {
//								upLoadPicture(beanBase.data,listener,errorListener,urlParams,paramsTwos);
//							} else {
//								ToastUtil.show(beanBase.msg);
//							}
//						}else{
//							ToastUtil.show("上传失败");
//						}
//					}
//				}, filePartName, files, params);
//		AijiaApplication.getInstance().runVolleyRequest(request);
//		AijiaApplication.getInstance().showLoadingDialog("图片正在上传", cActivity);
//	}
//
//	/**
//	 * @param picInfos
//	 * @param paramsTwos 参数按顺序填写customerid，version，progressid，stageid，stagename
//	 * @param
//	 */
//	private static void upLoadPicture(ArrayList<PicInfo> picInfos,
//                                      Response.Listener<String> listener, ErrorListener errorListener,
//                                      String[] urlParams,
//                                      String... paramsTwos) {
//		if (picInfos != null && !picInfos.isEmpty()) {
//			ArrayList<String> pictureNames = new ArrayList<String>();
//			for (PicInfo picInfo : picInfos) {
//				pictureNames.add(picInfo.picname);
//			}
//			BeanUpLoadPicture bPicture = new BeanUpLoadPicture();
//			bPicture.customerid = paramsTwos[0];
//			bPicture.version = paramsTwos[1];
//			// bPicture.progressid = mItemData.progressid+"";
//			bPicture.progressid = paramsTwos[2];
//			bPicture.stageid = paramsTwos[3];
//			bPicture.stagename = paramsTwos[4];
//			bPicture.pictures = pictureNames;
//			Gson gson = new Gson();
//			String jsonString = gson.toJson(bPicture);
//
//			BaseStringRequset requset = BaseStringRequset.requsetByPost(
//					jsonString, null, listener, new ErrorListener() {
//						public void onErrorResponse(VolleyError error) {
//							VolleyErrorUtils.showError(error);
//							ToastUtil.show("上传失败");
//						}
//					}, urlParams);
//			AijiaApplication.getInstance().runVolleyRequest(requset);
//		}
//	}
//
//	public static void upLoadNoticePicture(final ErrorListener errorListener,
//                                           final Response.Listener<String> listener, String filePartName,
//                                           ArrayList<File> files, Map<String, String> params,
//                                           final String[] urlParams,
//                                           final String[] paramsTwos) {
//		MultipartRequest request = new MultipartRequest(imageUpLoadUrl,
//				errorListener, new Response.Listener<String>() {
//					@Override
//					public void onResponse(String response) {
//						L.i("response == " + response);
//						BeanBase<ArrayList<PicInfo>> beanBase = new BeanBase<ArrayList<PicInfo>>();
//						Type tp = new TypeToken<BeanBase<ArrayList<PicInfo>>>() {
//						}.getType();
//						beanBase = NetUtils.getResponseBean(tp, response);
//						if ("200".equals(beanBase.code)) {
//								upLoadNoticePicture(beanBase.data,listener,errorListener,urlParams,paramsTwos);
//						} else {
//							Log.d("FAILURE",beanBase.msg);
//						}
//					}
//				}, filePartName, files, params);
////		AijiaApplication.getInstance().runVolleyRequest(request); TODO get a way to call this
//	}
//
//	/**
//	 *
//	 * @param data
//	 * @param listener
//	 * @param errorListener
//	 * @param urlParams
//	 * @param paramsTwos 参数按顺序填写customerid，version，checkid，title
//	 */
//	protected static void upLoadNoticePicture(ArrayList<PicInfo> data,
//                                              Listener<String> listener, ErrorListener errorListener,
//                                              String[] urlParams, String[] paramsTwos) {
//
//		if (data != null && !data.isEmpty()) {
//			ArrayList<String> pictureNames = new ArrayList<String>();
//			for (PicInfo picInfo : data) {
//				pictureNames.add(picInfo.picname);
//			}
//			BeanUpLoadPicture bPicture = new BeanUpLoadPicture();
//			bPicture.customerid = paramsTwos[0];
//			bPicture.version = paramsTwos[1];
//			// bPicture.progressid = mItemData.progressid+"";
//			bPicture.checkid = paramsTwos[2];
//			bPicture.title = paramsTwos[3];
//			bPicture.pictures = pictureNames;
//			Gson gson = new Gson();
//			String jsonString = gson.toJson(bPicture);
//
//			BaseStringRequset requset = BaseStringRequset.requsetByPost(
//					jsonString, null, listener, errorListener, urlParams);
//			AijiaApplication.getInstance().runVolleyRequest(requset);
//		}
//	}
//
//	static class PicInfo {
//		public String picname;
//		public String picurl;
//	}
//
//	static class BeanUpLoadPicture {
//		public String version;
//		public String customerid;
//		public String progressid;
//		public String stageid;
//		public String stagename;
//		public ArrayList<String> pictures;
//
//		public String checkid;
//		public String title;
//	}
//}
