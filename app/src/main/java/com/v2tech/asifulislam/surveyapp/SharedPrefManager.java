package com.v2tech.asifulislam.surveyapp;

import android.content.Context;
import android.content.SharedPreferences;

import com.v2tech.asifulislam.surveyapp.models.SavedDataModel;


public class SharedPrefManager {

    private static final String SHARED_PREF_NAME = "my_shared_preff";

    private static SharedPrefManager mInstance;
    private Context mCtx;

    private SharedPrefManager(Context mCtx) {
        this.mCtx = mCtx;
    }


    public static synchronized SharedPrefManager getInstance(Context mCtx) {
        if (mInstance == null) {
            mInstance = new SharedPrefManager(mCtx);
        }
        return mInstance;
    }


    public void saveDropdown(String Question, String Answer) {

        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("DropdownQuestion", Question);
        editor.putString("DropdownAnswar", Answer);
        editor.apply();

    }
    public void saveCheck(String Question, String Answer) {

        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("CheckQuestion", Question);
        editor.putString("CheckAnswar", Answer);
        editor.apply();

    }
//    public void saveRadio(String Question, String Answer) {
//
//        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putString("RadioQuestion", Question);
//        editor.putString("RadioAnswar", Answer);
//        editor.apply();
//
//    }
    public void saveText(String Question, String Answer) {

        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("TextQuestion", Question);
        editor.putString("TextAnswar", Answer);
        editor.apply();

    }
    public void savenumber(String Question, String Answer) {

        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("numberQuestion", Question);
        editor.putString("numberAnswar", Answer);
        editor.apply();

    }
    public void saveMultipleChoice(String Question, String Answer) {

        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("MultipleChoiceQuestion", Question);
        editor.putString("MultipleChoiceAnswar", Answer);
        editor.apply();

    }
    public SavedDataModel getAllData() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);


        return new SavedDataModel(

                sharedPreferences.getString("DropdownQuestion", ""),
                sharedPreferences.getString("DropdownAnswar", ""),
                sharedPreferences.getString("CheckQuestion", ""),
                sharedPreferences.getString("CheckAnswar", ""),
                sharedPreferences.getString("TextQuestion", ""),
                sharedPreferences.getString("TextAnswar", ""),
                sharedPreferences.getString("numberQuestion", ""),
                sharedPreferences.getString("numberAnswar", ""),
                sharedPreferences.getString("MultipleChoiceQuestion", ""),
                sharedPreferences.getString("MultipleChoiceAnswar", "")

        );

    }
    public  void  setListData(String listJson){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("ListKey", listJson);

        editor.apply();
    }

    public String getListData(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        String listdata=sharedPreferences.getString("ListKey", "");

        return listdata;
    }
    public void clear() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("numberQuestion", "");
        editor.putString("numberAnswar", "");
        editor.putString("MultipleChoiceQuestion", "");
        editor.putString("MultipleChoiceAnswar", "");
        editor.putString("TextQuestion", "");
        editor.putString("TextAnswar", "");
        editor.putString("CheckQuestion", "");
        editor.putString("CheckAnswar", "");
        editor.putString("DropdownQuestion", "");
        editor.putString("DropdownAnswar", "");
        editor.apply();
    }

}
