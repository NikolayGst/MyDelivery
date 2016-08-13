package ru.mydelivery.Login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ru.mydelivery.Login.Presenter.LoginPresenter;
import ru.mydelivery.Login.View.LoginView;
import ru.mydelivery.Main.MainActivity;
import ru.mydelivery.R;

public class SplashActivity extends AppCompatActivity implements LoginView {

    @BindView(R.id.editLogin)
    EditText mEditLogin;
    @BindView(R.id.editPassword)
    EditText mEditPassword;

    private LoginPresenter mLoginPresenter;
    private ProgressDialog mProgressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        initProgressDialog();
        mLoginPresenter = new LoginPresenter(this);
    }

    @OnClick(R.id.button)
    void login() {
        String login = mEditLogin.getText().toString().trim();
        String password = mEditPassword.getText().toString().trim();
        mLoginPresenter.loginUser(login, password);
    }

    private void initProgressDialog() {
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Авторизация...");
        mProgressDialog.setCancelable(false);
    }

    @Override
    public void errorIsEmptyLogin() {
        Toast.makeText(SplashActivity.this, "Поле для логина пустое!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void errorIsEmptyPassword() {
        Toast.makeText(SplashActivity.this, "Поле для пароля пустое!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void errorIsServer() {
        Toast.makeText(SplashActivity.this, "Проблемы с сервером", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void userIsNotFound() {
        Toast.makeText(SplashActivity.this, "Пользователь не найден!", Toast.LENGTH_SHORT).show();
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
    public void goToMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
