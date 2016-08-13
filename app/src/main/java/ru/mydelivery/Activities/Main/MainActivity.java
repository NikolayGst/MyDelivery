package ru.mydelivery.Activities.Main;

import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.mydelivery.Activities.Main.Presenter.MainPresenter;
import ru.mydelivery.Activities.Main.Presenter.Presenter;
import ru.mydelivery.Activities.Main.View.MainView;
import ru.mydelivery.Adapter.ListJobAdapter;
import ru.mydelivery.R;
import ru.mydelivery.network.Model.Main.JobsForUser;

public class MainActivity extends AppCompatActivity implements MainView<List<JobsForUser>> {

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    private RecyclerView.LayoutManager mLayoutManager;
    private ListJobAdapter mListJobAdapter;
    private Presenter mMainPresenter;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        init();

        mMainPresenter = new MainPresenter(this);
        mMainPresenter.getJobs(id);

    }

    private void init() {
        id = getIntent().getIntExtra("id", -1);
        mLayoutManager = new LinearLayoutManager(this);
        mListJobAdapter = new ListJobAdapter(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mListJobAdapter);
    }

    @Override
    public void onJobsLoaded(List<JobsForUser> jobsForUsers) {
        mListJobAdapter.setJobList(jobsForUsers);
    }

    @Override
    public void errorIsServer(@StringRes int resId) {
        Toast.makeText(MainActivity.this, resId, Toast.LENGTH_SHORT).show();
    }
}
