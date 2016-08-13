package ru.mydelivery.Login.Presenter;

import ru.mydelivery.Login.Model.LoginInteractor;
import ru.mydelivery.Login.Model.OfflineLoginInteractor;
import ru.mydelivery.Login.View.LoginView;

public class LoginPresenter implements LoginInteractor.OnLoginListener<String> {
    private LoginView mLoginView;
    private LoginInteractor<String> mLoginInteractor;

    public LoginPresenter(LoginView loginView) {
        mLoginView = loginView;
        mLoginInteractor = new OfflineLoginInteractor();
    }

    public void loginUser(String login, String password){
        mLoginView.showProgressDialog();
        if(login.isEmpty()){
            mLoginView.errorIsEmptyLogin();
            mLoginView.hideProgressDialog();
            return;
        }
        if(password.isEmpty()){
            mLoginView.errorIsEmptyPassword();
            mLoginView.hideProgressDialog();
            return;
        }
        mLoginInteractor.loginUser(login, password, this);
    }

    @Override
    public void onSuccess(String s) {
        if(s.equals("done")) {
            mLoginView.goToMainActivity();
            mLoginView.hideProgressDialog();
        } else {
            mLoginView.userIsNotFound();
            mLoginView.hideProgressDialog();
        }
    }

    @Override
    public void onFailure() {
        mLoginView.errorIsServer();
        mLoginView.hideProgressDialog();
    }
}
