package ru.mydelivery.Activities.Detail.View;

import android.support.annotation.StringRes;

public interface DetailView<T> {
    void onJobLoaded(T t);
    void errorIsServer(@StringRes int resId);
}
