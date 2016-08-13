package ru.mydelivery.Activities.Main.Model;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.mydelivery.network.Model.Main.JobsForUser;
import ru.mydelivery.network.Request;

public class MainInteractorImpl implements MainInteractor<List<JobsForUser>> {

    @Override
    public void loadingJobs(int id, final OnLoadingCompletedListener<List<JobsForUser>> onLoadingCompletedListener) {
        Request.getInstance().getListJobs(id).enqueue(new Callback<List<JobsForUser>>() {
            @Override
            public void onResponse(Call<List<JobsForUser>> call, Response<List<JobsForUser>> response) {
                if (response.body() != null) {
                    onLoadingCompletedListener.onSuccess(response.body());
                } else {
                    onLoadingCompletedListener.onFailure();
                }
            }

            @Override
            public void onFailure(Call<List<JobsForUser>> call, Throwable t) {
                onLoadingCompletedListener.onFailure();
            }
        });
    }

}
