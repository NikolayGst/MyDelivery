package ru.mydelivery.Activities.Detail.Model;

public interface DetailInteractor<T> {

    void loadingJob(String userId, String jobId, OnJobLoadingListener<T> onJobLoadingListener);

    interface OnJobLoadingListener<T> {

        void onSuccess(T t);

        void onFailure();
    }
}
