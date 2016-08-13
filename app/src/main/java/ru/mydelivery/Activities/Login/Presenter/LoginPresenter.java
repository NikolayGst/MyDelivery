package ru.mydelivery.Activities.Login.Presenter;

import android.content.Context;

import ru.mydelivery.Activities.Login.Model.LoginInteractor;
import ru.mydelivery.Activities.Login.Model.OnlineLoginInteractor;
import ru.mydelivery.Activities.Login.View.LoginView;
import ru.mydelivery.R;
import ru.mydelivery.Utils.SessionListener;
import ru.mydelivery.Utils.SessionManager;
import ru.mydelivery.network.Model.Login.Login;

public class LoginPresenter implements Presenter, LoginInteractor.OnLoginListener<Login>, SessionListener {
    private LoginView<Login> mLoginView;
    private LoginInteractor<Login> mLoginInteractor;
    private SessionManager mSessionManager;

    public LoginPresenter(LoginView<Login> loginView, Context context) {
        mLoginView = loginView;
        mLoginInteractor = new OnlineLoginInteractor();
        mSessionManager = new SessionManager(context);
        mSessionManager.setSessionListener(this);
    }

    @Override
    public void loginUser(String login, String password) {
        mLoginView.showProgressDialog();
        if (login.isEmpty()) {
            mLoginView.errorIsEmptyLogin(R.string.error_empty_login);
            mLoginView.hideProgressDialog();
            return;
        }
        if (password.isEmpty()) {
            mLoginView.errorIsEmptyPassword(R.string.error_empty_password);
            mLoginView.hideProgressDialog();
            return;
        }
        mLoginInteractor.loginUser(login, password, this);
    }

    @Override
    public void saveUser(boolean isChecked, String login, String password) {
        if (isChecked) {
            mSessionManager.setLogin(true);
            mSessionManager.setLoginAndPassword(login, password);
        } else {
            mSessionManager.setLoginAndPassword("", "");
            mSessionManager.setLogin(false);
        }
    }

    @Override
    public void onDestroy() {
        mLoginView = null;
        mSessionManager = null;
        mLoginInteractor = null;
    }

    @Override
    public void onSuccess(Login login) {
        if (login != null) {
            mLoginView.goToMainActivity(login);
            mLoginView.hideProgressDialog();
        } else {
            mLoginView.userIsNotFound(R.string.error_user_not_found);
            mLoginView.hideProgressDialog();
        }
    }

    @Override
    public void onFailure() {
        mLoginView.errorIsServer(R.string.error_server);
        mLoginView.hideProgressDialog();
    }

    @Override
    public void onSavedUser(boolean check, String login, String password) {
        mLoginView.restoreSavedLoginAndPassword(check, login, password);
    }
}
