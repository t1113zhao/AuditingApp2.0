package com.example.administrator.financialauditingapppro.MainDesk.ProjectsDetail;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.financialauditingapppro.R;

/**
 * Created by Administrator on 6/16/2017.
 */

public class HorizontalScrollRadioGroup extends HorizontalScrollView implements View.OnClickListener {

    HRG_ChangeButtonListener HRG_ChangeButtonListener;
    private int currentSelected =0;

    public class hRGButton{
        public RelativeLayout relativeLayout;
        public TextView textView;
        public TextView imageView;
        public int selected;
        public int notSelected;
        public String text ="";

        public hRGButton(int relativeLayout, int textView, int imageView, int selected, int notSelected,String text){
            this.relativeLayout = (RelativeLayout) findViewById(relativeLayout);
            this.textView = (TextView) findViewById(textView);
            this.imageView = (TextView) findViewById(imageView);
            this.selected = selected;
            this.notSelected = notSelected;
            this.text = text;
        }
    }

    int[]imageIDs = new int[]{
            R.id.hRGStartIV, R.id.hRGHydroIV, R.id.hRGLandscapingIV,R.id.hRGPaintIV,R.id.hRGQCIV,R.id.hRGCompletedIV
    };

    hRGButton[] buttons;

    public HorizontalScrollRadioGroup(Context context) {
        super(context);
        setHorizontalScrollBarEnabled(false);
        init();
    }

    public HorizontalScrollRadioGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        setHorizontalScrollBarEnabled(false);
        init();
    }

    public HorizontalScrollRadioGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.layout_horizontal_radiogroup, this);
        setButtons();
        onClick(currentSelected);
    }

    private void setButtons() {
         buttons= new hRGButton[]{
                new hRGButton(
                        R.id.hRGStart, R.id.hRGStartTV, R.id.hRGStartIV,
                        R.drawable.workstart_selected, R.drawable.workstart_default,
                        "Start"),
                new hRGButton(
                        R.id.hRGHydro, R.id.hRGHydroTV, R.id.hRGHydroIV,
                        R.drawable.work_hydropower_selected, R.drawable.work_hydropower_default,
                        "Utilities"),
                new hRGButton(
                        R.id.hRGLandscaping, R.id.hRGLandscapingTV, R.id.hRGLandscapingIV,
                        R.drawable.work_mud_wood_selected, R.drawable.work_mud_wood_default,
                        "Landscaping"),
                new hRGButton(
                        R.id.hRGPaint, R.id.hRGPaintTV,R.id.hRGPaintIV,
                        R.drawable.work_paint_selected,R.drawable.work_paint_default,
                        "Paint"),
                new hRGButton(
                        R.id.hRGQC, R.id.hRGQCTV,R.id.hRGQCIV,
                        R.drawable.work_install_selected,R.drawable.work_install_default,
                        "QC"),
                new hRGButton(
                        R.id.hRGCompleted, R.id.hRGCompletedTV,R.id.hRGCompletedIV,
                        R.drawable.work_finish_selected,R.drawable.work_finish_default,
                        "Completed"),

        };
        for(int i =0; i<buttons.length;i++){
            buttons[i].textView.setText(buttons[i].text);
            buttons[i].relativeLayout.setOnClickListener(this);
            buttons[i].relativeLayout.setBackgroundColor(Color.WHITE);
            buttons[i].imageView.setBackgroundResource(buttons[i].notSelected);
            buttons[i].textView.setTextColor(Color.BLACK);
            buttons[i].imageView.setVisibility(VISIBLE);
        }
    }

    public void onClick(int i){
        onClick(buttons[i].relativeLayout);
    }

    @Override
    public void onClick(View v) {
        buttons[currentSelected].imageView.setBackgroundResource(buttons[currentSelected].notSelected);
        buttons[currentSelected].textView.setTextColor(Color.BLACK);

        switch (v.getId()){
            case R.id.hRGStart:
                currentSelected =0;
                break;
            case R.id.hRGHydro:
                currentSelected =1;
                break;
            case R.id.hRGLandscaping:
                currentSelected =2;
                break;
            case R.id.hRGPaint:
                currentSelected =3;
                break;
            case R.id.hRGQC:
                currentSelected =4;
                break;
            case R.id.hRGCompleted:
                currentSelected =5;
                break;
        }
//        ImageView iV2 = (ImageView) findViewById(buttons[currentSelected].imageView);
//        iV2.setImageResource(buttons[currentSelected].selected);
        buttons[currentSelected].imageView.setBackgroundResource(buttons[currentSelected].selected);
        buttons[currentSelected].textView.setTextColor(Color.RED);

        if(HorizontalScrollRadioGroup.this.HRG_ChangeButtonListener != null){
            this.HRG_ChangeButtonListener.onChangeButton();
        }
//        Toast.makeText(getContext(), ""+currentSelected, Toast.LENGTH_SHORT).show();
    }

    public void setCurrentSelected(int set){
        currentSelected = set;
        onClick(currentSelected);
    }

    public int getCurrentSelected() {
        return currentSelected;
    }

    public void setHRG_ChangeButtonListener (HRG_ChangeButtonListener listener){
        this.HRG_ChangeButtonListener = listener;
    }
//

}
