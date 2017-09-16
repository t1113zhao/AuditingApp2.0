package com.example.administrator.financialauditingapppro.Login;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.example.administrator.financialauditingapppro.MainDesk.MainActivity;
import com.example.administrator.financialauditingapppro.R;
import com.example.administrator.financialauditingapppro.net.Beans.BeanBase;
import com.example.administrator.financialauditingapppro.net.Beans.BeanUserInfo;
import com.example.administrator.financialauditingapppro.net.ProjectURLS;
import com.example.administrator.financialauditingapppro.net.SharedPreferencesUtil;
import com.example.administrator.financialauditingapppro.net.StaticConstants;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    EditText loginUsernameET;
    EditText loginPasswordET;

    Button signInButton;

    final int TIMEOUTMS = StaticConstants.TIMEOUTMS;
//    String username;
//    String password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        boolean loggedIn = SharedPreferencesUtil.getUserLoginState(this);
        if(loggedIn)
            openMainActivity();
        else
            init();
    }

    private void init() {

        setContentView(R.layout.activity_login);

        setIds();
        setOnKeyListener();
        setOnClickListener();
        setFormatting();
    }



    private void setIds() {
        loginUsernameET = (EditText) findViewById(R.id.loginUsernameET);
        loginPasswordET = (EditText) findViewById(R.id.loginPasswordET);

        signInButton = (Button) findViewById(R.id.signInButton);
    }

    private void setOnKeyListener() {
        loginPasswordET.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
                    InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    if (inputMethodManager.isActive()) {
                        inputMethodManager.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);
                    }
                    return true;
                }
                return false;
            }
        });
    }

    private void setOnClickListener() {
        signInButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.signInButton:
                checkLoginStrings();
                break;
        }
    }

    private void checkLoginStrings() {
        String username = loginUsernameET.getText().toString().trim();
        String password = loginPasswordET.getText().toString().trim();

        Response.Listener<String> listener =new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                BeanBase<BeanUserInfo> userInfoBeanBase=new Gson().fromJson(response,new TypeToken<BeanBase<BeanUserInfo>>(){}.getType());

                String embnbbn = new Gson().toJson(userInfoBeanBase);
                Log.d("BEAN",embnbbn);
                if(userInfoBeanBase.code.equals("200")){

                    if (userInfoBeanBase.data != null){
                        BeanUserInfo beanUserInfo = userInfoBeanBase.data;
                        boolean saveSuccess = SharedPreferencesUtil.saveInSharedPreferences(getApplicationContext(),beanUserInfo,SharedPreferencesUtil.USER_INFO);
                        SharedPreferencesUtil.saveUserLoginState(getApplicationContext(),true);

                        if (saveSuccess){
                            openMainActivity();
                        }
                    }
                    else{
                        Toast.makeText(LoginActivity.this, ""+ userInfoBeanBase.msg, Toast.LENGTH_SHORT).show();
                    }

                }
            }
        };

        LoginRequest loginRequest = new LoginRequest(new LoginPostBody(username, password),new ProjectURLS().getloginURL(), listener, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(LoginActivity.this, "TIMEOUT ERROR", Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        });

        loginRequest.setRetryPolicy(
                new DefaultRetryPolicy(
                    TIMEOUTMS,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
        queue.add(loginRequest);

    }
    private void setFormatting() {

    }
    private void openMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);

        startActivity(intent);
    }


}
