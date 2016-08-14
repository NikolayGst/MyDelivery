package ru.mydelivery.Activities.Detail.Model;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.mydelivery.Model.Detail.JobForUser;
import ru.mydelivery.Network.Request;

public class DetailInteractorImpl implements DetailInteractor<JobForUser> {

    @Override
    public void loadingJob(String userId, String jobId, final OnJobLoadingListener<JobForUser> onJobLoadingListener) {
        Request.getInstance().getJobForUser(userId, jobId).enqueue(new Callback<JobForUser>() {
            @Override
            public void onResponse(Call<JobForUser> call, Response<JobForUser> response) {
                if(response != null){
                    onJobLoadingListener.onSuccess(response.body());
                }else {
                    onJobLoadingListener.onFailure();
                }
            }

            @Override
            public void onFailure(Call<JobForUser> call, Throwable t) {
                onJobLoadingListener.onFailure();
            }
        });
    }

}
