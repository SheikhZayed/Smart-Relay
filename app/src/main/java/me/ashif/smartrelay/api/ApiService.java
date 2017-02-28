package me.ashif.smartrelay.api;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by asif on 27/2/17.
 */

public interface ApiService {

    @GET("{relay_id}")
    Call<ResponseBody> toggleRelay(@Path("relay_id") int relay_id, @Query("ac") int account,@Query("value") int value);

}
