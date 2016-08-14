package ru.mydelivery.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SessionManager {

    private SharedPreferences pref;
    private MySharedPreferences spref;
    private Editor editor;
    private Context _context;
    private SessionListener mSessionListener;

    int PRIVATE_MODE = 0;

    private static final String PREF_NAME = "Login";
    private static final String KEY_IS_LOGGEDIN = "isLoggedIn";
    private static final String KEY_LOGIN = "login";
    private static final String KEY_PASSWORD = "password";

    public SessionManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        spref = MySharedPreferences.getInstance(_context, "secure_pref");
        editor = pref.edit();
    }

    public void setSessionListener(SessionListener sessionListener) {
        mSessionListener = sessionListener;
        if(isLoggedIn()){
            mSessionListener.onSavedUser(isLoggedIn(), getLoginName(), getPassword());
        }
    }

    public void setLoginAndPassword(String login, String password){

        spref.putString(KEY_LOGIN, login);
        spref.putString(KEY_PASSWORD, password);
        spref.commit();

    }

    public String getLoginName() {
        return spref.getString(KEY_LOGIN, "");
    }

    public String getPassword() {
        return spref.getString(KEY_PASSWORD, "");
    }

    public void setLogin(boolean isLoggedIn) {
        editor.putBoolean(KEY_IS_LOGGEDIN, isLoggedIn);
        editor.apply();

    }

    public boolean isLoggedIn() {
        return pref.getBoolean(KEY_IS_LOGGEDIN, false);
    }
}