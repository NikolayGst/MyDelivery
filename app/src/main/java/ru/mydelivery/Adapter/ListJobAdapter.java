package ru.mydelivery.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.mydelivery.Activities.Detail.DetailActivity;
import ru.mydelivery.R;
import ru.mydelivery.network.Model.Main.Jobs;
import ru.mydelivery.network.Model.Main.User;


public class ListJobAdapter extends RecyclerView.Adapter<ListJobAdapter.ViewHolder> {

    private List<Jobs> mJobList;
    private String mLetter;
    private Context mContext;
    private TextDrawable mTextDrawable;
    private ColorGenerator mGenerator = ColorGenerator.MATERIAL;
    private Jobs mJob;

    public ListJobAdapter(Context context) {
        mJobList = Collections.emptyList();
        mContext = context;
    }

    public void setJobList(List<Jobs> jobList) {
        mJobList.clear();
        mJobList = jobList;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.list_job, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        mJob = mJobList.get(position);
        final User user = mJob.getUser();
        mLetter = String.valueOf(user.getFio().charAt(0));
        mTextDrawable = TextDrawable.builder()
                .buildRound(mLetter, mGenerator.getRandomColor());
        holder.imgName.setImageDrawable(mTextDrawable);
        holder.txtFio.setText(user.getFio());
        int status = user.getStatus();
        String statusText = user.getStatusText();
        holder.txtDate.setText("Дата получения: " + user.getDate());
        holder.txtStatus.setText("Статус: " + getStatus(status) + getStatusText(statusText));
        holder.rltItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mJob = mJobList.get(position);
                String userId = user.getUserId();
                String jobsId = user.getJobsId();
                Intent intent = new Intent(mContext, DetailActivity.class);
                intent.putExtra("userId", userId);
                intent.putExtra("jobsId", jobsId);
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mJobList == null ? 0 : mJobList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img)
        ImageView imgName;
        @BindView(R.id.fio)
        TextView txtFio;
        @BindView(R.id.status)
        TextView txtStatus;
        @BindView(R.id.date)
        TextView txtDate;
        @BindView(R.id.rltItem)
        RelativeLayout rltItem;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public String getStatus(int status) {
        String result = null;
        switch (status) {
            case 0:
                result = "Не выбрано";
                break;
            case 1:
                result = "Перенесен";
                break;
            case 2:
                result = "Частичный возврат:";
                break;
            case 3:
                result = "Отменен:";
                break;
            case 4:
                result = "Выполнено";
                break;
        }
        return result;
    }

    public String getStatusText(String text) {
        String format = "";
        if (!text.isEmpty()) {
            format = "\n" + text;
        }
        return format;
    }
}
