package ru.mydelivery.Activities.Login.Model;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.mydelivery.network.Model.Login.Login;
import ru.mydelivery.network.Request;

public class OnlineLoginInteractor implements LoginInteractor<Login> {

    @Override
    public void loginUser(String login, String password, final OnLoginListener<Login> onLoginListener) {
        Request.getInstance().loginUser(login, password).enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {
                if (response.body() != null) {
                    Login login = response.body();
                    if (login.isError()) {
                        onLoginListener.onSuccess(null);
                        return;
                    }
                    else onLoginListener.onSuccess(login);
                }else {
                    onLoginListener.onFailure();
                }
            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {
                onLoginListener.onFailure();
            }
        });
    }
}
