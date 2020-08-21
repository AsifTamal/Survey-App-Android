package com.v2tech.asifulislam.surveyapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.v2tech.asifulislam.surveyapp.databinding.ActivityMainBinding;
import com.v2tech.asifulislam.surveyapp.models.SavedDataModel;
import com.v2tech.asifulislam.surveyapp.models.SurveyData;
import com.v2tech.asifulislam.surveyapp.viewmodels.MainviewModel;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
        MainviewModel mainviewModel;

        ActivityMainBinding activityMainBinding;
        private ViewPager Viewpager;
        private PagerAdapterForFragments pagerAdapterForFragments;
        private int mCurrentpage,sliderlengh;
        private ArrayList<SavedDataModel> currentTasks;
        Gson gson;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  setContentView(R.layout.activity_main);
        activityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        mainviewModel = ViewModelProviders.of(this).get(MainviewModel.class);
        activityMainBinding.setMainviewModel(mainviewModel);
        activityMainBinding.setLifecycleOwner(this);
        gson=new Gson();
        Viewpager= (ViewPager) findViewById(R.id.Pagerid);
        pagerAdapterForFragments=new PagerAdapterForFragments(getSupportFragmentManager());
        activityMainBinding.prpgrss.setVisibility(View.VISIBLE);

        mainviewModel.getSurveyDataformviewmodel().observe(this, new Observer<List<SurveyData>>() {
            @Override
            public void onChanged(List<SurveyData> surveyData) {
                sliderlengh=surveyData.size();
            for(SurveyData s:surveyData){

                pagerAdapterForFragments.addFragment(new DynamiFragments(s),"g");

            }
                Viewpager.setAdapter(pagerAdapterForFragments);
                Viewpager.addOnPageChangeListener(viewlistiner);
                activityMainBinding.btnnext.setVisibility(View.VISIBLE);
                activityMainBinding.prpgrss.setVisibility(View.GONE);
            }
        });

        activityMainBinding.btnback.setOnClickListener(this);
        activityMainBinding.btnnext.setOnClickListener(this);
        activityMainBinding.btndone.setOnClickListener(this);

    }

    ViewPager.OnPageChangeListener viewlistiner=new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        @Override
        public void onPageSelected(int i) {

            mCurrentpage=i;
            if(i==0){
                activityMainBinding.btnnext.setEnabled(true);
                activityMainBinding.btnback.setEnabled(false);
                activityMainBinding.btndone.setEnabled(false);

                activityMainBinding.btnnext.setText(R.string.NEXT);
                activityMainBinding.btnback.setText("");
                activityMainBinding.btnback.setVisibility(View.GONE);
            }
            else if (i==sliderlengh-1){
                activityMainBinding.btnnext.setEnabled(false);
                activityMainBinding.btnback.setEnabled(true);
                activityMainBinding.btndone.setEnabled(true);
                activityMainBinding.btnback.setVisibility(View.VISIBLE);
                activityMainBinding.btnnext.setVisibility(View.GONE);
                activityMainBinding.btndone.setVisibility(View.VISIBLE);
                activityMainBinding.btndone.setText(R.string.Done);
                activityMainBinding.btnback.setText(R.string.BACK);
            }
            else {
                activityMainBinding.btnnext.setEnabled(true);
                activityMainBinding.btnback.setEnabled(true);
                activityMainBinding.btndone.setEnabled(false);
                activityMainBinding.btnback.setVisibility(View.VISIBLE);
                activityMainBinding.btnnext.setVisibility(View.VISIBLE);
                activityMainBinding.btndone.setVisibility(View.GONE);
                activityMainBinding.btnnext.setText(R.string.NEXT);
                activityMainBinding.btnback.setText(R.string.BACK);
            }
        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    };
    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.btnnext){
            Viewpager.setCurrentItem(mCurrentpage+1);

        }
        else if(v.getId()==R.id.btnback){
            Viewpager.setCurrentItem(mCurrentpage-1);
            if(mCurrentpage==0){
                activityMainBinding.btnnext.setEnabled(true);
                activityMainBinding.btnback.setEnabled(false);
                activityMainBinding.btndone.setEnabled(false);
                activityMainBinding.btnback.setVisibility(View.INVISIBLE);
                activityMainBinding.btnnext.setVisibility(View.VISIBLE);
                activityMainBinding.btndone.setVisibility(View.GONE);
                activityMainBinding.btnnext.setText(R.string.NEXT);
               // activityMainBinding.btnnext.setText("");

            }
            else {
                activityMainBinding.btnnext.setEnabled(true);
                activityMainBinding.btnnext.setVisibility(View.VISIBLE);
                activityMainBinding.btnnext.setText(R.string.NEXT);
                activityMainBinding.btnback.setEnabled(true);
                activityMainBinding.btnback.setVisibility(View.VISIBLE);
                activityMainBinding.btnback.setText(R.string.BACK);
                activityMainBinding.btndone.setEnabled(false);
                activityMainBinding.btndone.setVisibility(View.GONE);


            }
        }

        else if(v.getId()==R.id.btndone){


          SavedDataModel  savedDataModel= SharedPrefManager.getInstance(this).getAllData();
           if(savedDataModel.getCheckAnswar().equalsIgnoreCase("")){
               Toast.makeText(this, "Please Select checkbox value", Toast.LENGTH_SHORT).show();
               return;
           }
            if(savedDataModel.getDropdownAnswar().equalsIgnoreCase("")){
                Toast.makeText(this, "Please Select dropdown value", Toast.LENGTH_SHORT).show();
                return;
            }
            if(savedDataModel.getMultipleChoiceAnswar().equalsIgnoreCase("")){
                Toast.makeText(this, "Please Select Multiple Choice value", Toast.LENGTH_SHORT).show();
                return;
            }
            if(savedDataModel.getNumberAnswar().equalsIgnoreCase("")){
                Toast.makeText(this, "Please Select Phone number value", Toast.LENGTH_SHORT).show();
                return;
            }
            if(savedDataModel.getTextAnswar().equalsIgnoreCase("")){
                Toast.makeText(this, "Please write in the text box value", Toast.LENGTH_SHORT).show();
                return;
            }

            addTask(savedDataModel);

        }
    }

    public void addTask(SavedDataModel t) {


        currentTasks= new ArrayList<SavedDataModel>();
        try {
            String json = SharedPrefManager.getInstance(this).getListData();
            Type type = new TypeToken<List<SavedDataModel>>() {}.getType();
            currentTasks.clear();
            currentTasks= gson.fromJson(json, type);
            if(currentTasks==null){
                currentTasks= new ArrayList<SavedDataModel>();
            }
            currentTasks.add(t);
           // customAdapter.notifyDataSetChanged();
            json = gson.toJson(currentTasks);
            SharedPrefManager.getInstance(this).setListData(json);

            Intent data = new Intent();
            setResult(RESULT_OK, data);
            SharedPrefManager.getInstance(this).clear();
            finish();

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(MainActivity.this,
                    e.getMessage().toString(),Toast.LENGTH_LONG).show();
        }

    }
}