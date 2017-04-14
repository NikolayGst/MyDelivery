package ru.mydelivery.Network;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import ru.mydelivery.Model.Detail.JobForUser;
import ru.mydelivery.Model.Detail.Status;
import ru.mydelivery.Model.Login.Login;
import ru.mydelivery.Model.Main.Jobs;

public interface API {

    @FormUrlEncoded
    @POST("login.php")
    Call<Login> loginUser(@Field("login") String login,
                          @Field("password") String password);

    @FormUrlEncoded
    @POST("getListJobs.php")
    Call<List<Jobs>> getListJobs(@Field("id") Integer id);

    @FormUrlEncoded
    @POST("jobForUser.php")
    Call<JobForUser> getJobForUser(@Field("id") String id,
                                   @Field("jobs_id") String jobId);

    @FormUrlEncoded
    @POST("setStatus.php")
    Call<Status> setStatus(@Field("id") String id,
                           @Field("jobs_id") String jobId,
                           @Field("status") String status,
                           @Field("status_text") String statusText,
                           @Field("date") String date);

}
