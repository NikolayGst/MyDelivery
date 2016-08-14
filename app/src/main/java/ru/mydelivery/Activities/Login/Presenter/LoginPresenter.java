package ru.mydelivery.Activities.Login.Presenter;

public interface LoginPresenter {

    void loginUser(String login, String password);

    void saveUser(boolean isChecked, String login, String password);

    void onDestroy();
}
