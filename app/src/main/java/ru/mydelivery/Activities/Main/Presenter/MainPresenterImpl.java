package ru.mydelivery.Activities.Main.Presenter;

import java.util.List;

import ru.mydelivery.Activities.Main.Model.MainInteractor;
import ru.mydelivery.Activities.Main.Model.MainInteractorImpl;
import ru.mydelivery.Activities.Main.View.MainView;
import ru.mydelivery.R;
import ru.mydelivery.network.Model.Main.Jobs;

public class MainPresenterImpl implements MainPresenter, MainInteractor.OnLoadingCompletedListener<List<Jobs>> {

    private MainView<List<Jobs>> mMainView;
    private MainInteractor<List<Jobs>> mMainInteractor;

    public MainPresenterImpl(MainView<List<Jobs>> mainView) {
        mMainView = mainView;
        mMainInteractor = new MainInteractorImpl();
    }

    @Override
    public void getJobs(int id) {
        mMainInteractor.loadingJobs(id, this);
    }

    @Override
    public void refreshJobs(int id) {
        mMainInteractor.loadingJobs(id, this);
    }

    @Override
    public void onSuccess(List<Jobs> jobs) {
        mMainView.onJobsLoaded(jobs);
        if (mMainView.isSwipeRefreshing()) mMainView.hideSwipe();
    }

    @Override
    public void onFailure() {
        mMainView.errorIsServer(R.string.error_server);
    }

    @Override
    public void onDestroy() {
        mMainView = null;
        mMainInteractor = null;
    }
}
