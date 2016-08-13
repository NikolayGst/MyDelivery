package ru.mydelivery.network;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import ru.mydelivery.network.Model.Login.Login;

public interface API {

    @POST("/login.php")
    @FormUrlEncoded
    Call<Login> loginUser(@Field("login") String login, @Field("password") String password);

}
