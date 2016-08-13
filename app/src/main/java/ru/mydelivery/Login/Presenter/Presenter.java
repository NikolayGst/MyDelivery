package ru.mydelivery.Login.Presenter;

/**
 * Created by Николай on 13.08.2016.
 */
public interface Presenter {
    void loginUser(String login, String password);
    void saveUser(boolean isChecked, String login, String password);
    void onDestroy();
}
