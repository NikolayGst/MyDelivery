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

import ru.mydelivery.R;


public class StatusDialogFragment extends DialogFragment implements AdapterView.OnItemClickListener,
        View.OnClickListener {
    private String[] mListStatus = {"Не выбрано", "Перенесен", "Частичный возврат", "Отменен", "Выполнено"};
    private ListView mListView;
    private RelativeLayout mRelativeLayout;
    private Button mButtonOk;
    private TextView txtStatusText;
    private EditText mEditStatus;
    private EditText mEditDate;
 //   private VolleyRequest mVolleyRequest;
    private String mStatus;
    private String mStatusText = " ";
    private String mDate = " ";
//    private DateTimePikerDialog mDateTimePikerDialog;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
 //       mVolleyRequest = new VolleyRequest();

        View view = inflater.inflate(R.layout.dialog_fragment, container, false);
        mListView = (ListView) view.findViewById(R.id.list);
        mRelativeLayout = (RelativeLayout) view.findViewById(R.id.rltStatus);
        mButtonOk = (Button) view.findViewById(R.id.btnOk);
        mEditStatus = (EditText) view.findViewById(R.id.txtStatus);
        mEditDate = (EditText) view.findViewById(R.id.txtNewDate);
    //    mDateTimePikerDialog = new DateTimePikerDialog(getActivity(), mEditDate);
        txtStatusText = (TextView) view.findViewById(R.id.txtRltStatus);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_1, mListStatus);
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(this);
        mButtonOk.setOnClickListener(this);
        mEditDate.setOnClickListener(this);

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
        switch (v.getId()){
            case R.id.btnOk:
                getData();
                break;
            case R.id.txtNewDate:
                mEditDate.setText(" ");
    //            mDateTimePikerDialog.showData();
        }

    }

    public void getData() {
        Bundle user = getArguments();
        String userId = user.getString("userId");
        String jobId = user.getString("jobId");
        mStatusText = mEditStatus.getText().toString();
        mDate = mEditDate.getText().toString() + "\n(обновлено)";
    /*    mVolleyRequest.setStatus(getActivity(), userId, jobId, mStatus, mStatusText, mDate, new CallBack() {
            @Override
            public void onSuccess(String response) {
                dismiss();
                Toast.makeText(getContext(), response, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFail(String error) {
                Toast.makeText(getContext(), error, Toast.LENGTH_LONG).show();
            }
        });*/

    }
}
