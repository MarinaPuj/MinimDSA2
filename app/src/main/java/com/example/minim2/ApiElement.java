package com.example.minim2;

import com.example.minim2.Element;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Url;

public interface ApiElement {

    @GET("pag-ini/{num1}/pag-fi/{Num2}")   //relative URL
    Call<Museums> getComments(@Path("num1")String num1,
                                    @Path("num2")String num2);
}
