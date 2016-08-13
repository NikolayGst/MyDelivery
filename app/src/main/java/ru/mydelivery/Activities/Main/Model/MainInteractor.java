package ru.mydelivery.Activities.Main.Model;

public interface MainInteractor<T> {

    void loadingJobs(int id, OnLoadingCompletedListener<T> onLoadingCompletedListener);

    interface OnLoadingCompletedListener<T> {
        void onSuccess(T t);

        void onFailure();
    }
}
