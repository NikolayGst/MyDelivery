package ru.mydelivery.Activities.Main;

import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.mydelivery.Activities.Main.Presenter.MainPresenter;
import ru.mydelivery.Activities.Main.Presenter.MainPresenterImpl;
import ru.mydelivery.Activities.Main.View.MainView;
import ru.mydelivery.Adapter.ListJobAdapter;
import ru.mydelivery.R;
import ru.mydelivery.Utils.DividerItemDecoration;
import ru.mydelivery.network.Model.Main.Jobs;

public class MainActivity extends AppCompatActivity implements MainView<List<Jobs>>, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipe;

    private RecyclerView.LayoutManager mLayoutManager;
    private ListJobAdapter mListJobAdapter;
    private MainPresenter mMainPresenter;
    private RecyclerView.ItemDecoration mItemDecoration;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        init();

        mMainPresenter = new MainPresenterImpl(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        mMainPresenter.getJobs(id);
    }

    private void init() {
        id = getIntent().getIntExtra("id", -1);

        mLayoutManager = new LinearLayoutManager(this);
        mListJobAdapter = new ListJobAdapter(this);

        mItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST);
        mRecyclerView.addItemDecoration(mItemDecoration);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mListJobAdapter);

        mSwipe.setColorSchemeResources
                (R.color.DarkColorPrimary, R.color.green, R.color.blue);
        mSwipe.setOnRefreshListener(this);

    }

    @Override
    public void onJobsLoaded(List<Jobs> jobs) {
        mListJobAdapter.setJobList(jobs);
    }

    @Override
    public boolean isSwipeRefreshing() {
        return mSwipe.isRefreshing();
    }

    @Override
    public void hideSwipe() {
        mSwipe.setRefreshing(false);
    }

    @Override
    public void errorIsServer(@StringRes int resId) {
        Toast.makeText(MainActivity.this, resId, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRefresh() {
        mMainPresenter.refreshJobs(id);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMainPresenter.onDestroy();
    }
}
