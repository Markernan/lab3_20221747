package com.example.lab3_20221747;

import com.google.gson.annotations.SerializedName;

public class Movie {
    @SerializedName("Title")
    private String title;

    @SerializedName("Year")
    private String year;

    @SerializedName("Response")
    private String response;

    @SerializedName("Error")
    private String error;

    public String getTitle() { return title; }
    public String getYear() { return year; }
    public String getResponse() { return response; }
    public String getError() { return error; }
}