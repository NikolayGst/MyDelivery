package ru.mydelivery.Utils;

public interface SessionListener {
    void onSavedUser(boolean check, String login, String password);
}
