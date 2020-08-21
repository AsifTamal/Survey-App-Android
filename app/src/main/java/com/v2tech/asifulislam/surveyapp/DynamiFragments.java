package com.v2tech.asifulislam.surveyapp;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.v2tech.asifulislam.surveyapp.api.Conn;
import com.v2tech.asifulislam.surveyapp.models.SurveyData;

import java.util.ArrayList;
import java.util.List;

public class DynamiFragments extends Fragment {
    View view;
    SurveyData sSurvaydata;
    RadioGroup rgrp;
    EditText editTextnumber,editTextText;
    LinearLayout llchk;
    TextView txtqustn;
    Spinner spnr;
    CheckBox chkyes,chkno;
    RadioButton[] rb;
    String chk="";

    public DynamiFragments() {

        this.sSurvaydata=new SurveyData();

        Log.d("DynamiFragments", "DynamiFragments: surveyData");
    }
    public DynamiFragments(SurveyData surveyData) {
        Log.d("DynamiFragments", "DynamiFragments: ");
        this.sSurvaydata=surveyData;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.common_fragment_layout, container, false);
        txtqustn=(TextView)view.findViewById(R.id.txtQuestion);
        rgrp=(RadioGroup)view.findViewById(R.id.radiogrp) ;
        editTextnumber=(EditText)view.findViewById(R.id.edtnumber);
        editTextText=(EditText)view.findViewById(R.id.edttext);
        llchk=  (LinearLayout)view.findViewById(R.id.llchk);
        spnr=(Spinner)view.findViewById(R.id.spnrdropdwn);
        chkno=(CheckBox)view.findViewById(R.id.checkbox_no);
        chkyes=(CheckBox)view.findViewById(R.id.checkbox_yes);
        loadview();
        return view;
    }

    private void loadview() {
        if(sSurvaydata.getType()!=null){


        txtqustn.setText(sSurvaydata.getQuestion());
        switch (sSurvaydata.getType()){

            case "dropdown":

                spnr.setVisibility(View.VISIBLE);
                loadspinner();
                savespinerdata();
             //   Toast.makeText(getActivity(), "dropdown", Toast.LENGTH_SHORT).show();
                break;
            case "number":
                editTextnumber.setVisibility(View.VISIBLE);
                savenumbervalue();
              //  Toast.makeText(getActivity(), "number", Toast.LENGTH_SHORT).show();
                break;
            case "Checkbox":
                llchk.setVisibility(View.VISIBLE);
                setcheckbox();
              //  Toast.makeText(getActivity(), "Checkbox", Toast.LENGTH_SHORT).show();
                break;
            case "text":
                editTextText.setVisibility(View.VISIBLE);
                savetextvalue();
                //Toast.makeText(getActivity(), " text", Toast.LENGTH_SHORT).show();
                break;
            case "multiple choice":
                rgrp.setVisibility(View.VISIBLE);
                loadradio();
                saveradio();
                //Toast.makeText(getActivity(), " multiple choice", Toast.LENGTH_SHORT).show();
                break;
        }
        }
    }

    private void savetextvalue() {

        editTextText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                SharedPrefManager.getInstance(getActivity()).saveText(sSurvaydata.getQuestion(),s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
    private void savenumbervalue() {
        editTextnumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                SharedPrefManager.getInstance(getActivity()).savenumber(sSurvaydata.getQuestion(),s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
    private void saveradio() {
        rgrp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int selectedId = rgrp.getCheckedRadioButtonId();

                // find the radiobutton by returned id
                RadioButton  radioButton = (RadioButton) view.findViewById(selectedId);
                String selectedrdioanswer=radioButton.getText().toString();
                SharedPrefManager.getInstance(getActivity()).saveMultipleChoice(sSurvaydata.getQuestion(),selectedrdioanswer);
            }
        });

    }

    private void savespinerdata() {
        spnr.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SharedPrefManager.getInstance(getActivity()).saveDropdown(sSurvaydata.getQuestion(),spnr.getItemAtPosition(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void setcheckbox() {
        SharedPrefManager.getInstance(getActivity()).saveCheck(sSurvaydata.getQuestion(),chk);
        chkno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(chkyes.isChecked()){
                    chkyes.setChecked(false);
                }
                if(chkno.isChecked()){
                    chk="No";
                }
                SharedPrefManager.getInstance(getActivity()).saveCheck(sSurvaydata.getQuestion(),chk);
            }
        });
        chkyes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chkno.isChecked()){
                    chkno.setChecked(false);
                }
                if(chkyes.isChecked()){
                    chk="Yes";
                }
                SharedPrefManager.getInstance(getActivity()).saveCheck(sSurvaydata.getQuestion(),chk);
            }
        });
    }

    private void loadradio() {
        String[] arrayString = sSurvaydata.getOptions().split(",");

      rb = new RadioButton[arrayString.length];

      //create the RadioGroup
        rgrp.setOrientation(RadioGroup.VERTICAL);//or RadioGroup.VERTICAL
        for(int i=0; i<arrayString.length; i++){
            rb[i]  = new RadioButton(getActivity());
            rb[i].setText(arrayString[i].toString());
            rb[i].setId(i + 100);
            if(i==0){
                rb[i].setChecked(true);
            }
            rgrp.addView(rb[i]);

        }
    }

    private void loadspinner() {
        String[] arrayString = sSurvaydata.getOptions().split(",");

        List<String> list = new ArrayList<String>();


        for(int i=0;i<=arrayString.length-1;i++){
            list.add(arrayString[i]);
        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnr.setAdapter(dataAdapter);
    }
}
