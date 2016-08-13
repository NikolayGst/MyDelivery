package ru.mydelivery.Activities.Main.Presenter;

import java.util.List;

import ru.mydelivery.Activities.Main.Model.MainInteractor;
import ru.mydelivery.Activities.Main.Model.MainInteractorImpl;
import ru.mydelivery.Activities.Main.View.MainView;
import ru.mydelivery.R;
import ru.mydelivery.network.Model.Main.JobsForUser;

/**
 * Created by Николай on 14.08.2016.
 */
public class MainPresenter implements Presenter, MainInteractor.OnLoadingCompletedListener<List<JobsForUser>> {
    private MainView<List<JobsForUser>> mMainView;
    private MainInteractor<List<JobsForUser>> mMainInteractor;

    public MainPresenter(MainView<List<JobsForUser>> mainView) {
        mMainView = mainView;
        mMainInteractor = new MainInteractorImpl();
    }

    @Override
    public void getJobs(int id) {
        mMainInteractor.loadingJobs(id, this);
    }

    @Override
    public void onSuccess(List<JobsForUser> jobsForUsers) {
        mMainView.onJobsLoaded(jobsForUsers);
    }

    @Override
    public void onFailure() {
        mMainView.errorIsServer(R.string.error_server);
    }
}
