package ru.mydelivery.network;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import ru.mydelivery.network.Model.Login.Login;
import ru.mydelivery.network.Model.Main.JobsForUser;

public interface API {

    @FormUrlEncoded
    @POST("/login.php")
    Call<Login> loginUser(@Field("login") String login, @Field("password") String password);

    @FormUrlEncoded
    @POST("/getListJobs.php")
    Call<List<JobsForUser>> getListJobs(@Field("id") Integer id);

}
