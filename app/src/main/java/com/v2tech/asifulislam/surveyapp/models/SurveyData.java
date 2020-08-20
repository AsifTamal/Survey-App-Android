package com.v2tech.asifulislam.surveyapp.models;

import java.util.List;

public class SurveyData {

    private String question;

    private String options;

    private String type;

    private String required;

    public String getQuestion ()
    {
        return question;
    }

    public void setQuestion (String question)
    {
        this.question = question;
    }

    public String getOptions ()
    {
        return options;
    }

    public void setOptions (String options)
    {
        this.options = options;
    }

    public String getType ()
    {
        return type;
    }

    public void setType (String type)
    {
        this.type = type;
    }

    public String getRequired ()
    {
        return required;
    }

    public void setRequired (String required)
    {
        this.required = required;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [question = "+question+", options = "+options+", type = "+type+", required = "+required+"]";
    }
}
