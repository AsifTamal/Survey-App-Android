package com.v2tech.asifulislam.surveyapp.models;

public class MyArray {
    public String question;
    public String type;
    public String options;
    public boolean required;

    public MyArray(String question, String type, String options, boolean required) {
        this.question = question;
        this.type = type;
        this.options = options;
        this.required = required;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }
}
