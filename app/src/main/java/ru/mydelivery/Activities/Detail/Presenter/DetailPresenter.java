package ru.mydelivery.Activities.Detail.Presenter;

import android.content.Context;
import android.support.v4.app.FragmentManager;

public interface DetailPresenter {

    void loadJob(String userId, String jobId);

    void showStatusDialog(String userId, String jobId, FragmentManager fragmentManager);

    void telByNumberPhone(Context context, String tel);

    void onDestroy();
}
