package ru.mydelivery.Activities.Detail;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import ru.mydelivery.R;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setResult(R.layout.activity_detail);
    }
}
