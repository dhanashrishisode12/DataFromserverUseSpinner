package com.example.datafromserverusespinner;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
public interface MyInterface {
    String JSONURL = "http://spartans.freevar.com/plametion/admin_login/";

    @GET("get_services.php")
    Call<List<ServiceData>> get_services();
}


