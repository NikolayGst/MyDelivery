package ru.mydelivery.Activities.Login.View;

import android.support.annotation.StringRes;

public interface LoginView<T> {
    void errorIsEmptyLogin(@StringRes int resId);
    void errorIsEmptyPassword(@StringRes int resId);
    void errorIsServer(@StringRes int resId);
    void userIsNotFound(@StringRes int resId);
    void showProgressDialog();
    void hideProgressDialog();
    void restoreSavedLoginAndPassword(boolean check, String login, String password);
    void goToMainActivity(T t);
}
