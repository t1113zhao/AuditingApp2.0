package com.example.administrator.financialauditingapppro.MainDesk.ProjectsDetail;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.administrator.financialauditingapppro.R;

public class BitmapDisplay extends AppCompatActivity {

    ImageView bitmapIV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitmap_display);
        bitmapIV = (ImageView) findViewById(R.id.bitmapIV);

        Intent sentHere = getIntent();
        Bitmap bitmap =(Bitmap) sentHere.getExtras().get("picture");

        bitmapIV.setImageBitmap(bitmap);

    }
}
