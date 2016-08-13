package ru.mydelivery.Activities.Main.View;

import android.support.annotation.StringRes;

public interface MainView<T> {
    void onJobsLoaded(T t);
    void errorIsServer(@StringRes int resId);
}
