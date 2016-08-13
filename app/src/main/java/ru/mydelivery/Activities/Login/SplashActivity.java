package ru.mydelivery.Activities.Login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ru.mydelivery.Activities.Login.Presenter.LoginPresenter;
import ru.mydelivery.Activities.Login.Presenter.Presenter;
import ru.mydelivery.Activities.Login.View.LoginView;
import ru.mydelivery.Activities.Main.MainActivity;
import ru.mydelivery.R;
import ru.mydelivery.network.Model.Login.Login;

public class SplashActivity extends AppCompatActivity implements LoginView<Login>,
        CompoundButton.OnCheckedChangeListener {

    @BindView(R.id.editLogin)
    EditText mEditLogin;
    @BindView(R.id.editPassword)
    EditText mEditPassword;
    @BindView(R.id.setLogin)
    CheckBox mCheckBox;

    private Presenter mLoginPresenter;
    private ProgressDialog mProgressDialog;
    private String login;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        initProgressDialog();
        mLoginPresenter = new LoginPresenter(this, this);
        mCheckBox.setOnCheckedChangeListener(this);
    }

    @OnClick(R.id.button)
    void login() {
        login = mEditLogin.getText().toString().trim();
        password = mEditPassword.getText().toString().trim();
        mLoginPresenter.loginUser(login, password);
    }

    private void initProgressDialog() {
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Авторизация...");
        mProgressDialog.setCancelable(false);
    }

    @Override
    public void errorIsEmptyLogin(int resId) {
        Toast.makeText(SplashActivity.this, resId, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void errorIsEmptyPassword(int resId) {
        Toast.makeText(SplashActivity.this, resId, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void errorIsServer(int resId) {
        Toast.makeText(SplashActivity.this, resId, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void userIsNotFound(int resId) {
        Toast.makeText(SplashActivity.this, resId, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgressDialog() {
        if (!mProgressDialog.isShowing())
            mProgressDialog.show();
    }

    @Override
    public void hideProgressDialog() {
        if (mProgressDialog.isShowing())
            mProgressDialog.dismiss();
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        login = mEditLogin.getText().toString().trim();
        password = mEditPassword.getText().toString().trim();
        if (compoundButton.isChecked()) {
            mLoginPresenter.saveUser(true, login, password);
        } else {
            mLoginPresenter.saveUser(false, "", "");
        }
    }

    @Override
    public void restoreSavedLoginAndPassword(boolean check, String login, String password) {
        mCheckBox.setChecked(check);
        mEditLogin.setText(login);
        mEditPassword.setText(password);
    }

    @Override
    public void goToMainActivity(Login login) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("id", login.getUser().getId());
        intent.putExtra("login", login.getUser().getLogin());
        startActivity(intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLoginPresenter.onDestroy();
    }
}
