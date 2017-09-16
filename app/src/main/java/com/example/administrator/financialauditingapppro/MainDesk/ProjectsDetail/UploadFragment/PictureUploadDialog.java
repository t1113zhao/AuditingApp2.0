package com.example.administrator.financialauditingapppro.MainDesk.ProjectsDetail.UploadFragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.provider.MediaStore;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.administrator.financialauditingapppro.R;
import com.example.administrator.financialauditingapppro.net.StaticConstants;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Administrator on 6/22/2017.
 */

public class PictureUploadDialog extends Dialog implements RadioGroup.OnCheckedChangeListener{
    HashMap<Integer, Integer> hashMap = new HashMap<>();
    PictureUploadDialogListener pictureUploadDialogListener;

    public int selected;
    public PictureUploadDialog(@NonNull Context context) {
        super(context);
        init();
    }

    private void init() {
        setDialog();

        ArrayList<String> strings = new ArrayList<>();
        strings.add("Take Photo");
        strings.add("Choose Photo");
        strings.add("Cancel");

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.uploadPictureDialogRadioGroup);
        radioGroup.setGravity(Gravity.CENTER);


        for (int i =0; i<strings.size();i++){
            setDialogRadioButtons(radioGroup,hashMap,i, strings);
        }
        radioGroup.setOnCheckedChangeListener(this);
    }

    private void setDialog() {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.setContentView(R.layout.layout_upload_picture_dialog);
        Window window = this.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.width = WindowManager.LayoutParams.MATCH_PARENT;
        wlp.gravity = Gravity.BOTTOM;
        window.setAttributes(wlp);
    }

    private void setDialogRadioButtons( RadioGroup radioGroup, HashMap<Integer, Integer> hashMap, int i, ArrayList<String> strings) {
        RadioButton radioButton = new RadioButton(getContext());
        radioButton.setButtonDrawable(null);
        radioButton.setText(strings.get(i));
        radioButton.setGravity(Gravity.CENTER);
        radioButton.setMinimumWidth(500);

        radioGroup.addView(radioButton);

        hashMap.put(radioButton.getId(),i);
    }

    public void setPictureUploadDialogListener (PictureUploadDialogListener listener){
        this.pictureUploadDialogListener = listener;
    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        selected = hashMap.get(checkedId);
        if (PictureUploadDialog.this.pictureUploadDialogListener!=null){
            this.pictureUploadDialogListener.onDialogPress();
        }
    }
}
