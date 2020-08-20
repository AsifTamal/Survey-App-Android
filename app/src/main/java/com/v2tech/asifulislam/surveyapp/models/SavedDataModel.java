package com.v2tech.asifulislam.surveyapp.models;

public class SavedDataModel {
    String DropdownQuestion,DropdownAnswar,CheckQuestion,CheckAnswar,TextQuestion,TextAnswar,
            numberQuestion,numberAnswar,MultipleChoiceQuestion,MultipleChoiceAnswar;

    public SavedDataModel(String dropdownQuestion, String dropdownAnswar, String checkQuestion, String checkAnswar, String textQuestion, String textAnswar, String numberQuestion, String numberAnswar,
                          String multipleChoiceQuestion, String multipleChoiceAnswar) {
        DropdownQuestion = dropdownQuestion;
        DropdownAnswar = dropdownAnswar;
        CheckQuestion = checkQuestion;
        CheckAnswar = checkAnswar;

        TextQuestion = textQuestion;
        TextAnswar = textAnswar;
        this.numberQuestion = numberQuestion;
        this.numberAnswar = numberAnswar;
        MultipleChoiceQuestion = multipleChoiceQuestion;
        MultipleChoiceAnswar = multipleChoiceAnswar;
    }

    public SavedDataModel() {
    }

    public String getDropdownQuestion() {
        return DropdownQuestion;
    }

    public void setDropdownQuestion(String dropdownQuestion) {
        DropdownQuestion = dropdownQuestion;
    }

    public String getDropdownAnswar() {
        return DropdownAnswar;
    }

    public void setDropdownAnswar(String dropdownAnswar) {
        DropdownAnswar = dropdownAnswar;
    }

    public String getCheckQuestion() {
        return CheckQuestion;
    }

    public void setCheckQuestion(String checkQuestion) {
        CheckQuestion = checkQuestion;
    }

    public String getCheckAnswar() {
        return CheckAnswar;
    }

    public void setCheckAnswar(String checkAnswar) {
        CheckAnswar = checkAnswar;
    }



    public String getTextQuestion() {
        return TextQuestion;
    }

    public void setTextQuestion(String textQuestion) {
        TextQuestion = textQuestion;
    }

    public String getTextAnswar() {
        return TextAnswar;
    }

    public void setTextAnswar(String textAnswar) {
        TextAnswar = textAnswar;
    }

    public String getNumberQuestion() {
        return numberQuestion;
    }

    public void setNumberQuestion(String numberQuestion) {
        this.numberQuestion = numberQuestion;
    }

    public String getNumberAnswar() {
        return numberAnswar;
    }

    public void setNumberAnswar(String numberAnswar) {
        this.numberAnswar = numberAnswar;
    }

    public String getMultipleChoiceQuestion() {
        return MultipleChoiceQuestion;
    }

    public void setMultipleChoiceQuestion(String multipleChoiceQuestion) {
        MultipleChoiceQuestion = multipleChoiceQuestion;
    }

    public String getMultipleChoiceAnswar() {
        return MultipleChoiceAnswar;
    }

    public void setMultipleChoiceAnswar(String multipleChoiceAnswar) {
        MultipleChoiceAnswar = multipleChoiceAnswar;
    }
}
