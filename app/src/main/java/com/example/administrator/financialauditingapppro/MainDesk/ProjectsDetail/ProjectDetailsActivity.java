package com.example.administrator.financialauditingapppro.MainDesk.ProjectsDetail;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.support.annotation.IdRes;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.example.administrator.financialauditingapppro.MainDesk.Default_Request;
import com.example.administrator.financialauditingapppro.MainDesk.ProjectsDetail.AcceptanceFragment.AcceptanceFragment;
import com.example.administrator.financialauditingapppro.MainDesk.ProjectsDetail.CompleteFragment.ProjectDetailsCompleteFragment;
import com.example.administrator.financialauditingapppro.MainDesk.ProjectsDetail.UploadFragment.ImageGenerals.ImageActivity;
import com.example.administrator.financialauditingapppro.MainDesk.ProjectsDetail.UploadFragment.UploadFragment;
import com.example.administrator.financialauditingapppro.R;
import com.example.administrator.financialauditingapppro.net.Beans.BeanBase;
import com.example.administrator.financialauditingapppro.net.Beans.BeanBench;
import com.example.administrator.financialauditingapppro.net.Beans.BeanProjectDetails;
import com.example.administrator.financialauditingapppro.net.Beans.BeanProjectFragment;
import com.example.administrator.financialauditingapppro.net.Beans.BeanUserInfo;
import com.example.administrator.financialauditingapppro.net.ProjectURLS;
import com.example.administrator.financialauditingapppro.net.SharedPreferencesUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;

//import static com.example.administrator.financialauditingapppro.R.id.pDHRG;
import static com.example.administrator.financialauditingapppro.net.StaticConstants.TIMEOUTMS;

public class ProjectDetailsActivity extends ImageActivity implements RadioGroup.OnCheckedChangeListener, View.OnClickListener {

//    Fragment[] fragArray = new Fragment[]{
//            new Fragment, new addFragment
//    }
    Fragment currentFragment;
    UploadFragment UploadFragment;
    AcceptanceFragment acceptanceFragment;
    ProjectDetailsCompleteFragment completeFragment;

    BeanBench.UploadPicNotice picNotice;
    BeanProjectFragment.ProjectInformation projectInformation;
    BeanProjectDetails beanProjectDetails;
    String projectID;
    String userID;
    String URL;
    boolean benchSent;
//
//    HorizontalScrollRadioGroup pDHRG ;

    RadioGroup projectDetailsSelect;

    HashMap<String,String> hmap = new HashMap<>();
    RadioButton[] pDButtons = new RadioButton[3];
    int currentSelect =0;

    TextView pDAddress;
    ImageView pDBack;
    FrameLayout pDContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_details);
        init();
       if( ActivityCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
               !=PackageManager.PERMISSION_GRANTED ){
           ActivityCompat.requestPermissions(this,new String[]{
                   android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                   android.Manifest.permission.READ_EXTERNAL_STORAGE,
                   android.Manifest.permission.CAMERA
           },0x100);
       }
    }

    private void init() {

        setURL();
        setIDs();
        setListeners();
        getInfo();
    }

    private void setURL() {
        Intent sentHere = getIntent();

//        benchSent = sentHere.getExtras().getBoolean("SENTFROMBENCH");

        BeanUserInfo beanUserInfo = getBeanUserInfo();
        userID = beanUserInfo.foremanid;
        projectID = sentHere.getExtras().getString("ID");
//        if (benchSent){
////            picNotice = (BeanBench.UploadPicNotice) sentHere.getExtras().getSerializable("PROJECT");
////            projectID = picNotice.projectid +"";
////            userID = sentHere.getExtras().getString("USERID");
//        }
//        else {
////            projectInformation = (BeanProjectFragment.ProjectInformation) sentHere.getExtras().getSerializable("PROJECT");
////            projectID = projectInformation.id +"";
////            userID = projectInformation.corpid;
//        }

        ProjectURLS p = new ProjectURLS();

        URL = p.BASEURL+"app%2Fcomapi/"+p.VERSION_NUMBER+"/" +"com.getprojectdetail/"+userID + "/"+projectID +"?usertype=2";
    }

    private void setIDs() {
        projectDetailsSelect = (RadioGroup) findViewById(R.id.projectDetailsSelect);

        hmap.put(R.id.pDUpload+"",0+"");
        hmap.put(R.id.pDAcceptence+"",1+"");
        hmap.put(R.id.pDInformation+"",2+"");
        pDButtons[0] = (RadioButton) findViewById(R.id.pDUpload);
        pDButtons[1] = (RadioButton) findViewById(R.id.pDAcceptence);
        pDButtons[2] = (RadioButton) findViewById(R.id.pDInformation);

        pDBack = (ImageView) findViewById(R.id.pDBack);
        pDAddress = (TextView) findViewById(R.id.pDAddress);
//        pDHRG = (HorizontalScrollRadioGroup) findViewById(R.id.pDHRG);

        pDContainer = (FrameLayout) findViewById(R.id.pDContainer);
    }

    private void setListeners() {
        projectDetailsSelect.setOnCheckedChangeListener(this);
        pDButtons[currentSelect].setChecked(true);
        pDBack.setOnClickListener(this);
    }

    private void getInfo() {
        Response.Listener<String> listener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                BeanBase<BeanProjectDetails> userInfoBeanBase=new Gson().fromJson(response,new TypeToken<BeanBase<BeanProjectDetails>>(){}.getType());

                String embnbbn = new Gson().toJson(userInfoBeanBase);
                Log.d("BEAN",embnbbn);
                if(userInfoBeanBase.code.equals("200")){

                    if (userInfoBeanBase.data != null){
                        beanProjectDetails = userInfoBeanBase.data;
                        boolean saveSuccess = SharedPreferencesUtil.saveInSharedPreferences(getApplicationContext(),beanProjectDetails,SharedPreferencesUtil.PROJECT_DETAILS);
                        if (saveSuccess){
                            postRequest();
                        }
                    }
                    else{
                        Toast.makeText(ProjectDetailsActivity.this, ""+ userInfoBeanBase.msg, Toast.LENGTH_SHORT).show();
                    }

                }
            }
        };

        Default_Request default_request = new Default_Request(URL, listener, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ProjectDetailsActivity.this, "TIMEOUT ERROR", Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        });

        default_request.setRetryPolicy(
                new DefaultRetryPolicy(
                        TIMEOUTMS,
                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        RequestQueue queue = Volley.newRequestQueue(ProjectDetailsActivity.this);
        queue.add(default_request);
    }

    private void postRequest() {
        pDAddress.setText(beanProjectDetails.address+ ":" + beanProjectDetails.id );
    }

    @Override
    protected void postImageResponse(String response) {
        super.postImageResponse(response);
        int g =0;

        Toast.makeText(this, "Uploaded", Toast.LENGTH_SHORT).show();
        BeanBase<Boolean> beanBase = new Gson().fromJson(response,new TypeToken<BeanBase<Boolean>>(){}.getType());
        if (beanBase.data){
            UploadFragment.onChangeButton();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.pDBack:
                finish();
                break;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        pDButtons[currentSelect].setBackgroundColor(Color.WHITE);
        pDButtons[currentSelect].setTextColor(Color.BLACK);
        currentSelect = Integer.parseInt(hmap.get(checkedId+""));

//        pDHRG.setVisibility(View.VISIBLE);
//        if(currentSelect >1){
//            pDHRG.setVisibility(View.GONE);
//        }

        changeColors();
        changeFragments();
    }

    private void changeColors() {
        pDButtons[currentSelect].setBackgroundColor(Color.RED);
        pDButtons[currentSelect].setTextColor(Color.WHITE);
    }

    private void changeFragments() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (currentFragment != null){
            transaction.hide(currentFragment);
        }


        switch (currentSelect){
            case 0:
                uploadButtonSelected(transaction);
                break;
            case 1:
                acceptanceButtonSelected(transaction);
                break;
            case 2:
                completeButtonSelected(transaction);
                break;
        }

        transaction.commit();

    }
    private void uploadButtonSelected(FragmentTransaction transaction) {
        if(UploadFragment == null){
            UploadFragment = new UploadFragment();
            UploadFragment.setImageActivity( this);
            transaction.add(R.id.pDContainer, UploadFragment);
        }
        else{
            transaction.show(UploadFragment);
        }
        currentFragment = UploadFragment;
    }
    private void acceptanceButtonSelected(FragmentTransaction transaction) {
        if(acceptanceFragment == null){
            acceptanceFragment = new AcceptanceFragment();
            transaction.add(R.id.pDContainer,acceptanceFragment);
        }
        else{
            transaction.show(acceptanceFragment);
        }
        currentFragment = acceptanceFragment;
    }
    private void completeButtonSelected(FragmentTransaction transaction) {
        if(completeFragment == null){
            completeFragment = new ProjectDetailsCompleteFragment();
            transaction.add(R.id.pDContainer,completeFragment);
        }
        else{
            transaction.show(completeFragment);
        }
        currentFragment = completeFragment;
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//    }

    protected void getPDListItem() {
        pdBean = UploadFragment.getCurrentSelectedBeanPD();
    }
}
