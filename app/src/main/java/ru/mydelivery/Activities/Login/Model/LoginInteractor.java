package ru.mydelivery.Activities.Login.Model;

public interface LoginInteractor<T> {

    void loginUser(String login, String password, OnLoginListener<T> onLoginListener );

    interface OnLoginListener<T> {
        void onSuccess(T t);
        void onFailure();
    }
}
