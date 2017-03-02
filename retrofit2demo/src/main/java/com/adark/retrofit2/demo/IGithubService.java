package com.adark.retrofit2.demo;


import com.adark.retrofit2.demo.model.Follower;
import com.adark.retrofit2.demo.model.Repo;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Shelly on 2017-3-1.
 */

public interface IGithubService {

    @GET("users/{user}/followers")
    Call<List<Follower>> listFollowers(@Path("user") String user);





}
