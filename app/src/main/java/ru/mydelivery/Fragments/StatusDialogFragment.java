package ru.mydelivery.Fragments;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.mydelivery.R;
import ru.mydelivery.Utils.DateTimePikerDialog;
import ru.mydelivery.network.Model.Detail.Status;
import ru.mydelivery.network.Request;


public class StatusDialogFragment extends DialogFragment implements AdapterView.OnItemClickListener,
        View.OnClickListener {
    private String[] mListStatus = {"Не выбрано", "Перенесен", "Частичный возврат", "Отменен", "Выполнено"};

    @BindView(R.id.list)
    ListView mListView;
    @BindView(R.id.rltStatus)
    RelativeLayout mRelativeLayout;
    @BindView(R.id.btnOk)
    Button mButtonOk;
    @BindView(R.id.txtRltStatus)
    TextView txtStatusText;
    @BindView(R.id.txtStatus)
    EditText mEditStatus;
    @BindView(R.id.txtNewDate)
    EditText mEditDate;

    private String mStatus;
    private String mStatusText = " ";
    private String mDate = " ";
    private DateTimePikerDialog mDateTimePikerDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_fragment, container, false);
        ButterKnife.bind(this, view);
        mDateTimePikerDialog = new DateTimePikerDialog(getActivity(), mEditDate);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_1, mListStatus);
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(this);
        mButtonOk.setOnClickListener(this);
        mEditDate.setOnClickListener(this);
        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {
        mStatus = String.valueOf(position);
        switch (position) {
            case 0:
                getData();
                break;
            case 1:
                mListView.setVisibility(View.GONE);
                mRelativeLayout.setVisibility(View.VISIBLE);
                mEditStatus.setVisibility(View.GONE);
                mEditDate.setVisibility(View.VISIBLE);
                mButtonOk.setVisibility(View.VISIBLE);
                txtStatusText.setText("Перенесен");
                break;
            case 2:
                mListView.setVisibility(View.GONE);
                mRelativeLayout.setVisibility(View.VISIBLE);
                mButtonOk.setVisibility(View.VISIBLE);
                txtStatusText.setText("Частичный возврат");
                break;
            case 3:
                mListView.setVisibility(View.GONE);
                mRelativeLayout.setVisibility(View.VISIBLE);
                mButtonOk.setVisibility(View.VISIBLE);
                txtStatusText.setText("Причина отмены");
                break;
            case 4:
                getData();
                break;
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnOk:
                getData();
                break;
            case R.id.txtNewDate:
                mEditDate.setText(" ");
                mDateTimePikerDialog.showData();
        }

    }

    public void getData() {
        Bundle user = getArguments();
        String userId = user.getString("userId");
        String jobId = user.getString("jobId");
        mStatusText = mEditStatus.getText().toString();
        mDate = mEditDate.getText().toString() + "\n(обновлено)";
        Request.getInstance().setStatus(userId, jobId, mStatus, mStatusText, mDate).enqueue(new Callback<Status>() {
            @Override
            public void onResponse(Call<Status> call, Response<Status> response) {
                Status status = response.body();
                if (status.isError()) {
                    Toast.makeText(getContext(), status.getStatus(), Toast.LENGTH_LONG).show();
                } else {
                    dismiss();
                    Toast.makeText(getContext(), status.getStatus(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Status> call, Throwable t) {
                Toast.makeText(getContext(), "error", Toast.LENGTH_LONG).show();
            }
        });
    }
}
