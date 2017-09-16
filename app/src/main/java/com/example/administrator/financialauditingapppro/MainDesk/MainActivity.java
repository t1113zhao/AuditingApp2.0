package com.example.administrator.financialauditingapppro.MainDesk;

import android.graphics.Color;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.administrator.financialauditingapppro.MainDesk.Bench.MainBenchFragment;
import com.example.administrator.financialauditingapppro.MainDesk.Profile.MainProfileFragment;
import com.example.administrator.financialauditingapppro.MainDesk.Projects.MainProjectsFragment;
import com.example.administrator.financialauditingapppro.R;

public class MainActivity extends AppCompatActivity {
    Fragment currentFragment;
    Fragment benchFragment;
    Fragment projectsFragment;
    Fragment profileFragment;

    RadioGroup mainButtonsGroup;

    RadioButton mainProjectsButton;
    RadioButton mainBenchButton;
    RadioButton mainProfileButton;

    FrameLayout mainFragmentContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {

        setIDs();
        setBackgroundResources();
        createOnCheckedChangeListener();
        mainBenchButton.setChecked(true);
    }

    private void setBackgroundResources() {
        mainProjectsButton.setBackgroundResource(R.drawable.x_radiobutton_color_selector);
        mainBenchButton.setBackgroundResource(R.drawable.x_radiobutton_color_selector);
        mainProfileButton.setBackgroundResource(R.drawable.x_radiobutton_color_selector);
    }


    private void setIDs() {

        mainProjectsButton = (RadioButton) findViewById(R.id.mainProjectsButton);
        mainBenchButton = (RadioButton) findViewById(R.id.mainBenchButton);
        mainProfileButton = (RadioButton) findViewById(R.id.mainProfileButton);

        mainButtonsGroup = (RadioGroup) findViewById(R.id.mainButtonsGroup);

        mainFragmentContainer = (FrameLayout) findViewById(R.id.mainFragmentContainer);
    }

    private void createOnCheckedChangeListener() {
        mainButtonsGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

                if (currentFragment != null){
                    transaction.hide(currentFragment);
                }

                switch (checkedId){
                    case R.id.mainProjectsButton:
                        mainProjectsButtonSelected(transaction);
                        break;
                    case R.id.mainBenchButton:
                        mainBenchButtonSelected(transaction);
                        break;
                    case R.id.mainProfileButton:
                        mainProfileButtonSelected(transaction);
                        break;
                }
                transaction.commit();
//                changeColors();
//                mainProjectsButton.setTextColor(getResources().g);
            }

            private void mainProjectsButtonSelected(FragmentTransaction transaction) {
                if (projectsFragment == null){
                    projectsFragment = new MainProjectsFragment();
                    transaction.add(R.id.mainFragmentContainer,projectsFragment);
                }
                else {
                    transaction.show(projectsFragment);
                }
                currentFragment = projectsFragment;
            }

            private void mainBenchButtonSelected(FragmentTransaction transaction) {
                if (benchFragment == null){
                    benchFragment = new MainBenchFragment();
                    transaction.add(R.id.mainFragmentContainer,benchFragment);
                }
                else {
                    transaction.show(benchFragment);
                }
                currentFragment = benchFragment;
            }

            private void mainProfileButtonSelected(FragmentTransaction transaction) {
                if (profileFragment == null){
                    profileFragment = new MainProfileFragment();
                    transaction.add(R.id.mainFragmentContainer,profileFragment);
                }
                else {
                    transaction.show(profileFragment);
                }
                currentFragment = profileFragment;
            }
        });
    }

    private void changeColors() {
        switch (mainButtonsGroup.getCheckedRadioButtonId()){
            case R.id.mainProjectsButton:
                mainProjectsButton.setBackgroundColor(Color.RED);
                mainProjectsButton.setTextColor(Color.WHITE);

                mainBenchButton.setBackgroundColor(Color.WHITE);
                mainBenchButton.setTextColor(Color.BLACK);

                mainProfileButton.setBackgroundColor(Color.WHITE);
                mainProfileButton.setTextColor(Color.BLACK);
                break;
            case R.id.mainBenchButton:
                mainProjectsButton.setBackgroundColor(Color.WHITE);
                mainProjectsButton.setTextColor(Color.BLACK);

                mainBenchButton.setBackgroundColor(Color.RED);
                mainBenchButton.setTextColor(Color.WHITE);

                mainProfileButton.setBackgroundColor(Color.WHITE);
                mainProfileButton.setTextColor(Color.BLACK);
                break;
            case R.id.mainProfileButton:
                mainProjectsButton.setBackgroundColor(Color.WHITE);
                mainProjectsButton.setTextColor(Color.BLACK);

                mainBenchButton.setBackgroundColor(Color.WHITE);
                mainBenchButton.setTextColor(Color.BLACK);

                mainProfileButton.setBackgroundColor(Color.RED);
                mainProfileButton.setTextColor(Color.WHITE);
                break;
        }
    }


}
