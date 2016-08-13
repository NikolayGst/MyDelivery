package ru.mydelivery.Login.View;

public interface LoginView {
    void errorIsEmptyLogin();
    void errorIsEmptyPassword();
    void errorIsServer();
    void userIsNotFound();
    void showProgressDialog();
    void hideProgressDialog();
    void goToMainActivity();
}
