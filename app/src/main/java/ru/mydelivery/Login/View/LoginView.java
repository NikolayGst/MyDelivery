package ru.mydelivery.Login.View;

public interface LoginView<T> {
    void errorIsEmptyLogin();
    void errorIsEmptyPassword();
    void errorIsServer();
    void userIsNotFound();
    void showProgressDialog();
    void hideProgressDialog();
    void goToMainActivity(T t);
}
