package com.v2tech.asifulislam.surveyapp.api;

import com.v2tech.asifulislam.surveyapp.models.SurveyData;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface iApiurls {

    @GET("getSurvey")
    Call<ResponseBody> getSurveyData(
            ///@Query("timestamp") String timestamp
    );
}
