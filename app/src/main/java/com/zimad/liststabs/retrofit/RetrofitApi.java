package com.zimad.liststabs.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by igor on 09.03.18.
 */

public interface RetrofitApi {
    @GET("xim/api.php")
    Call<QueryModel> getData(@Query("query") String categoryName);
}
