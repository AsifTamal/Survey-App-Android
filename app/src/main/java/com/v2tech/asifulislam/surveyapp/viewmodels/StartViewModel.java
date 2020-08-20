package com.v2tech.asifulislam.surveyapp.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.v2tech.asifulislam.surveyapp.models.SavedDataModel;

import java.util.List;

public class StartViewModel extends AndroidViewModel {
    Application application;
    MutableLiveData<List<SavedDataModel>> listMutableLiveData;

    public StartViewModel(@NonNull Application application) {
        super(application);
        this.application=application;
        listMutableLiveData= new MutableLiveData<>();
    }

    public void setlistForRV(List<SavedDataModel> listSurvay) {
        listMutableLiveData.setValue(listSurvay);
    }
    public  MutableLiveData<List<SavedDataModel>> getlistforRV(){
        return listMutableLiveData;
    }
}
