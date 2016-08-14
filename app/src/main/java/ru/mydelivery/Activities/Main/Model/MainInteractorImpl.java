package ru.mydelivery.Activities.Main.Model;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.mydelivery.network.Model.Main.Jobs;
import ru.mydelivery.network.Request;

public class MainInteractorImpl implements MainInteractor<List<Jobs>> {

    @Override
    public void loadingJobs(int id, final OnLoadingCompletedListener<List<Jobs>> onLoadingCompletedListener) {
        Request.getInstance().getListJobs(id).enqueue(new Callback<List<Jobs>>() {
            @Override
            public void onResponse(Call<List<Jobs>> call, Response<List<Jobs>> response) {
                if (response.body() != null) {
                    onLoadingCompletedListener.onSuccess(response.body());
                } else {
                    onLoadingCompletedListener.onFailure();
                }
            }

            @Override
            public void onFailure(Call<List<Jobs>> call, Throwable t) {
                onLoadingCompletedListener.onFailure();
            }
        });
    }

}
