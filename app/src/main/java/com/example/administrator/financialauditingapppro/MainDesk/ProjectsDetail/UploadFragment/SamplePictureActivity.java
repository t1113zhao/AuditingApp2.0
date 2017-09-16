package com.example.administrator.financialauditingapppro.MainDesk.ProjectsDetail.UploadFragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.example.administrator.financialauditingapppro.MainDesk.Default_Request;
import com.example.administrator.financialauditingapppro.MainDesk.ProjectsDetail.UploadFragment.ImageGenerals.ImageActivity;
import com.example.administrator.financialauditingapppro.R;
import com.example.administrator.financialauditingapppro.net.Beans.BeanBase;
import com.example.administrator.financialauditingapppro.net.Beans.BeanPDUploadListItem;
import com.example.administrator.financialauditingapppro.net.ProjectURLS;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import static com.example.administrator.financialauditingapppro.net.StaticConstants.TIMEOUTMS;

public class SamplePictureActivity extends ImageActivity implements View.OnClickListener{

    TextView pictureViewUploadTV;
    TextView pictureViewTitleTV;
    ImageView pictureViewBackIV;
    ListView pictureViewLV;
    BeanPDUploadListItem pdUploadListItem;
    int stageID;
    ArrayList<BeanPDUploadListItem.Faqentity> faqentities;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_view);
        init();

    }

    private void init() {
        setIds();
        setOnClickListeners();
        Intent sentHere = getIntent();
        stageID = sentHere.getExtras().getInt("IMAGE_VIEW");
        pdUploadListItem = (BeanPDUploadListItem) sentHere.getExtras().get("BEAN");

        pictureViewTitleTV.setText("Examples");
        pictureViewUploadTV.setText("Upload");

        getInfo();
    }

    private void setIds() {
        pictureViewUploadTV = (TextView) findViewById(R.id.pictureViewUploadTV);
        pictureViewTitleTV = (TextView) findViewById(R.id.pictureViewTitleTV);
        pictureViewBackIV = (ImageView) findViewById(R.id.pictureViewBackIV);
        pictureViewLV = (ListView) findViewById(R.id.pictureViewLV);
    }

    private void setOnClickListeners() {
        pictureViewUploadTV.setOnClickListener(this);
        pictureViewTitleTV.setOnClickListener(this);
        pictureViewBackIV.setOnClickListener(this);
    }

    private void getInfo() {
        setUrl();

        Response.Listener<String> listener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                BeanBase<ArrayList<BeanPDUploadListItem.Faqentity>> beans=new Gson().fromJson(response,new TypeToken<BeanBase<ArrayList<BeanPDUploadListItem.Faqentity>>>(){}.getType());

                String embnbbn = new Gson().toJson(beans);
                Log.d("BEAN",embnbbn);
                if(beans.code.equals("200")){

                    if (beans.data != null){
                        faqentities = beans.data;
                        postRequest();
                    }
                    else{
                        Toast.makeText(getApplicationContext(), ""+ beans.msg, Toast.LENGTH_SHORT).show();
                    }

                }
            }
        };

        Default_Request request = new Default_Request(url, listener, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "TIMEOUT ERROR", Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        });

        request.setRetryPolicy(
                new DefaultRetryPolicy(
                        TIMEOUTMS,
                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(request);
    }

    private void setUrl() {
        ProjectURLS urls = new ProjectURLS();
        url = urls.BASEURL + urls.APP_API_EXTENSION + urls.VERSION_NUMBER +"/" + urls.GETSAMPLEPICTURE_EXTENSION + stageID;
    }

    private void postRequest(){
        adapter = new SampleImageAdapter(this,faqentities,pdUploadListItem);
        adapter.mActivity = this;
        pictureViewLV.setAdapter(adapter);
        pictureViewLV.setVisibility(View.VISIBLE);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.pictureViewBackIV:
                finish();
                break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent information) {
        super.onActivityResult(requestCode, resultCode, information);

    }

    @Override
    protected void postImageResponse(String response) {
        super.postImageResponse(response);

        Toast.makeText(this, "Uploaded", Toast.LENGTH_SHORT).show();
//        ImageView iv = (ImageView) pictureViewLV.getChildAt(adapter.getLocalArrayPosition()).findViewById(R.id.takeImageIV);
//        iv.setImageBitmap();
    }

    protected void getPDListItem() {
        pdBean = pdUploadListItem;
    }
}
