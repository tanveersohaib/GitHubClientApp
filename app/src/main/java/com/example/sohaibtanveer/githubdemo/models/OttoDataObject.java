package com.example.sohaibtanveer.githubdemo.models;

public class OttoDataObject {

    private String typeOfData;
    private Object obj;

    public void setTypeOfData(String type){
        typeOfData = type;
    }

    public String getTypeOfData() {
        return typeOfData;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public Object getObj() {
        return obj;
    }
}
