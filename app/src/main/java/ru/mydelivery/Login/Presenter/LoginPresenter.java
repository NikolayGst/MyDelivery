package ru.mydelivery.Login.Presenter;

import ru.mydelivery.Login.Model.LoginInteractor;
import ru.mydelivery.Login.Model.OnlineLoginInteractor;
import ru.mydelivery.Login.View.LoginView;
import ru.mydelivery.network.Model.Login.Login;

public class LoginPresenter implements LoginInteractor.OnLoginListener<Login> {
    private LoginView<Login> mLoginView;
    private LoginInteractor<Login> mLoginInteractor;

    public LoginPresenter(LoginView<Login> loginView) {
        mLoginView = loginView;
        mLoginInteractor = new OnlineLoginInteractor();
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
    public void onSuccess(Login login) {
        if(login != null) {
            mLoginView.goToMainActivity(login);
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
