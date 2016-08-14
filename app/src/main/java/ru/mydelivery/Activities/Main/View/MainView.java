package ru.mydelivery.Activities.Main.View;

import android.support.annotation.StringRes;

public interface MainView<T> {
    void onJobsLoaded(T t);
    boolean isSwipeRefreshing();
    void hideSwipe();
    void errorIsServer(@StringRes int resId);
}
