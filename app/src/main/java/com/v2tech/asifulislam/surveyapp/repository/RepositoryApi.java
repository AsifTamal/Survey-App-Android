package com.v2tech.asifulislam.surveyapp.repository;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.v2tech.asifulislam.surveyapp.MainActivity;
import com.v2tech.asifulislam.surveyapp.api.Conn;
import com.v2tech.asifulislam.surveyapp.models.MyArray;
import com.v2tech.asifulislam.surveyapp.models.SurveyData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RepositoryApi {

    private MutableLiveData<List<SurveyData>> surveyDataResponse ;
    List<SurveyData> servaydata;
    Application application;
    public RepositoryApi(Application application) {
        this.application=application;
        surveyDataResponse = new MutableLiveData<>();

    }

    public MutableLiveData<List<SurveyData>> getSurveyData(String timestamp){
        servaydata=null;
        servaydata= new ArrayList<>();
        Call<ResponseBody> call = Conn
                .getRetroConnecion().getApiService()
                .getSurveyData();

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {

                    String json= response.body().string();

                    JSONArray jsonArray = new JSONArray(json);
                    for(int index = 0;index < jsonArray.length(); index++) {
                        SurveyData singlesuvey= new SurveyData();
                        JSONObject jsonObject = jsonArray.getJSONObject(index);

                        singlesuvey.setQuestion(String.valueOf(jsonObject.getString("question")));
                        singlesuvey.setOptions(String.valueOf(jsonObject.getString("options")));
                        singlesuvey.setRequired(String.valueOf(jsonObject.getString("required")));
                        singlesuvey.setType(String.valueOf(jsonObject.getString("type")));
                        Log.d("cccccc", String.valueOf(jsonObject.getString("type")));
                        servaydata.add(singlesuvey);
                    }
                    surveyDataResponse.setValue(servaydata);
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                    Log.d("eeeeeeee", String.valueOf(e.getMessage()));
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
      return surveyDataResponse;

    }
}
