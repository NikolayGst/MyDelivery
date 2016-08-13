package ru.mydelivery.Login.Model;


import android.os.Handler;

public class LoginInteractorImpl implements LoginInteractor<String> {

    @Override
    public void loginUser(final String login, final String password, final OnLoginListener<String> onLoginListener) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(login.equals("nicols") && password.equals("123456")){
                    onLoginListener.onSuccess("done");
                }else {
                    onLoginListener.onSuccess("not found");
                }
            }
        },3000);
    }

}
