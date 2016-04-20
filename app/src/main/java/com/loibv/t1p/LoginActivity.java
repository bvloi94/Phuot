package com.loibv.t1p;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.loibv.t1p.iinterface.OnRequestObject;
import com.loibv.t1p.model.Account;
import com.loibv.t1p.utils.Const;
import com.loibv.t1p.utils.InternalStorage;
import com.loibv.t1p.utils.ServiceUtil;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {

    private static final int REQUEST_SIGNUP = 0;

    private ProgressDialog progressDialog;

    @Bind(R.id.input_email)
    EditText _emailText;
    @Bind(R.id.input_password)
    EditText _passwordText;
    @Bind(R.id.btn_login)
    Button _loginButton;
    @Bind(R.id.link_signup)
    TextView _signupLink;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        progressDialog = new ProgressDialog(LoginActivity.this,
                R.style.AppTheme_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.setCancelable(false);

        Account acc = null;

        try {
            acc = (Account) InternalStorage.readObject(this, Const.IS_ACCOUNT);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        if (acc != null) {
            _emailText.setText(acc.getEmail());
        }

        _loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (!validate()) {
                    onLoginFailed();
                    return;
                }

                _loginButton.setEnabled(false);

                final String email = _emailText.getText().toString().trim();
                final String password = _passwordText.getText().toString().trim();

                loginRequest(email, password);
            }
        });

        _signupLink.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Start the Signup activity
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
            }
        });
    }

    public void loginRequest(String email, String password) {
        showProgressDialog();
        ServiceUtil<Account> serviceUtil = new ServiceUtil<>(this);
        serviceUtil.getHashMap().put("email", email);
        serviceUtil.getHashMap().put("password", password);
        serviceUtil.retrieveObjectData(Const.URL_LOGIN, new OnRequestObject<Account>() {
            @Override
            public void onTaskCompleted(Account obj, boolean error, String message) {
                if (!error) {
                    onLoginSuccess(obj);
                } else {
                    onLoginFailed();
                }
            }
        }, Account.class);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {
                Account acc = data.getParcelableExtra(Const.BD_ACCOUNT);
                loginRequest(acc.getEmail(), acc.getPassword());
            }
        }
    }

    @Override
    public void onBackPressed() {
        // disable going back to the LoginActivity
        moveTaskToBack(true);
    }

    public void onLoginSuccess(Account acc) {
        _loginButton.setEnabled(true);
        Intent homeIntent = new Intent(this, HomeActivity.class);
        try {
            // Save the list of entries to internal storage
            InternalStorage.writeObject(this, Const.IS_ACCOUNT, acc);
        } catch (IOException e) {
            Log.e(Const.IS_TAG, e.getMessage());
        }
        hideProgressDialog();
        startActivity(homeIntent);
    }

    public void onLoginFailed() {
        hideProgressDialog();
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();
        _loginButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("enter a valid email address");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 6) {
            _passwordText.setError("at least 6 characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        return valid;
    }

    private void showProgressDialog() {
        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }
    }

    private void hideProgressDialog() {
        if (progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }
}