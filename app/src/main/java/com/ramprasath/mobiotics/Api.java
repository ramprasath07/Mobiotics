package com.ramprasath.mobiotics;

import com.ramprasath.mobiotics.Model.VideoPlayer;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Belal on 10/2/2017.
 */

public interface Api {

    String BASE_URL = "https://interview-e18de.firebaseio.com/";

    @GET("media.json")
    Call<List<VideoPlayer>> getVideos();

}