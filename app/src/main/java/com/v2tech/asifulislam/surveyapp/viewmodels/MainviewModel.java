package com.v2tech.asifulislam.surveyapp.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.v2tech.asifulislam.surveyapp.models.SurveyData;
import com.v2tech.asifulislam.surveyapp.repository.RepositoryApi;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainviewModel extends AndroidViewModel {
    Application application;
    RepositoryApi repository;
    public MutableLiveData<List<SurveyData>>  defSurveyData;
    public MainviewModel(@NonNull Application application) {
        super(application);
        this.application=application;
        repository=new RepositoryApi(application);
        defSurveyData=new MutableLiveData<>();
        setSurveyData();
    }
    private void setSurveyData() {
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat dfurl = new SimpleDateFormat("dd-MM-yyyy");
        final String formattedDateurl = dfurl.format(c);

        defSurveyData=repository.getSurveyData(formattedDateurl);
    }
    public MutableLiveData<List<SurveyData>>  getSurveyDataformviewmodel(){
        return defSurveyData;
    }


}
