package ru.mydelivery.Activities.Detail.Presenter;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import ru.mydelivery.Activities.Detail.Model.DetailInteractor;
import ru.mydelivery.Activities.Detail.Model.DetailInteractorImpl;
import ru.mydelivery.Activities.Detail.View.DetailView;
import ru.mydelivery.Fragments.StatusDialogFragment;
import ru.mydelivery.R;
import ru.mydelivery.Model.Detail.JobForUser;

public class DetailPresenterImpl implements DetailPresenter, DetailInteractor.OnJobLoadingListener<JobForUser> {

    private DetailView<JobForUser> mDetailView;
    private DetailInteractor<JobForUser> mDetailInteractor;


    public DetailPresenterImpl(DetailView<JobForUser> detailView) {
        mDetailView = detailView;
        mDetailInteractor = new DetailInteractorImpl();
    }

    @Override
    public void loadJob(String userId, String jobId) {
        mDetailView.showProgressDialog();
        mDetailInteractor.loadingJob(userId, jobId, this);
    }

    @Override
    public void showStatusDialog(String userId, String jobId, FragmentManager fragmentManager) {
        Bundle args = new Bundle();
        args.putString("userId", userId);
        args.putString("jobId", jobId);
        StatusDialogFragment dialog = new StatusDialogFragment();
        dialog.setArguments(args);
        dialog.show(fragmentManager, "dialog");
    }

    @Override
    public void telByNumberPhone(Context context, String tel) {
        Intent dialIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + tel));
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        context.startActivity(dialIntent);

    }

    @Override
    public void onDestroy() {
        mDetailView = null;
        mDetailInteractor = null;
    }

    @Override
    public void onSuccess(JobForUser jobForUser) {
        mDetailView.hideProgressDialog();
        mDetailView.onJobLoaded(jobForUser);
    }

    @Override
    public void onFailure() {
        mDetailView.hideProgressDialog();
        mDetailView.errorIsServer(R.string.error_server);
    }
}
