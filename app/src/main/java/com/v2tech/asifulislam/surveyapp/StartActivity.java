package com.v2tech.asifulislam.surveyapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.v2tech.asifulislam.surveyapp.databinding.ActivityStartBinding;
import com.v2tech.asifulislam.surveyapp.models.SavedDataModel;
import com.v2tech.asifulislam.surveyapp.viewmodels.MainviewModel;
import com.v2tech.asifulislam.surveyapp.viewmodels.StartViewModel;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class StartActivity extends AppCompatActivity {
    private static final int ADD_NOTE_REQUEST = 1;
    StartViewModel startViewModel;
             ActivityStartBinding activityStartBinding;
    Gson gson;
    List<SavedDataModel> listSurvay;
    RecyclerAdapter dataadapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityStartBinding = DataBindingUtil.setContentView(this,R.layout.activity_start);
        startViewModel = ViewModelProviders.of(this).get(StartViewModel.class);
        activityStartBinding.setStartviewModel(startViewModel);
        activityStartBinding.setLifecycleOwner(this);
        gson=new Gson();
        activityStartBinding.btnfloat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivityForResult(intent, ADD_NOTE_REQUEST);
            }
        });
        setdata() ;
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        activityStartBinding.reclvw.setLayoutManager(linearLayoutManager);
        activityStartBinding.reclvw.setHasFixedSize(true);
        dataadapter = new RecyclerAdapter(this);
        activityStartBinding.reclvw.setAdapter(dataadapter);
        startViewModel.getlistforRV().observe(this, new Observer<List<SavedDataModel>>() {
            @Override
            public void onChanged(List<SavedDataModel> savedDataModels) {
                if(savedDataModels.size()>0)
                dataadapter.setWordlist(savedDataModels);
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_NOTE_REQUEST && resultCode == RESULT_OK) {
            try {
                setdata();


            } catch (JsonSyntaxException e) {
                e.printStackTrace();
            }
            Toast.makeText(this, "Survey saved", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Survey not saved", Toast.LENGTH_SHORT).show();
        }
    }

    private void setdata() {

        try {
            listSurvay=new ArrayList<>();
            String json = SharedPrefManager.getInstance(this).getListData();
            Type type = new TypeToken<List<SavedDataModel>>() {}.getType();
            if(!json.equalsIgnoreCase("")){
                listSurvay= gson.fromJson(json, type);
            }
            startViewModel.setlistForRV(listSurvay);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
    }

}