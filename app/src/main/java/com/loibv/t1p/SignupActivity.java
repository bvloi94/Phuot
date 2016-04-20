package com.loibv.t1p;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.loibv.t1p.iinterface.OnSendObject;
import com.loibv.t1p.model.Account;
import com.loibv.t1p.utils.Const;
import com.loibv.t1p.utils.ServiceUtil;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SignupActivity extends AppCompatActivity {
    private static final String TAG = "SignupActivity";


    private static final String KEY_NAME = "fullname";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASSWORD = "password";

    private ProgressDialog progressDialog;

    @Bind(R.id.input_name)
    EditText _nameText;
    @Bind(R.id.input_email)
    EditText _emailText;
    @Bind(R.id.input_password)
    EditText _passwordText;
    @Bind(R.id.input_confirm_password)
    EditText _confirmPasswordText;
    @Bind(R.id.btn_signup)
    Button _signupButton;
    @Bind(R.id.link_login)
    TextView _loginLink;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);

        _signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });

        _loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the registration screen and return to the Login activity
                finish();
            }
        });
    }

    public void signup() {

        Log.d(TAG, "Signup");

        progressDialog = new ProgressDialog(SignupActivity.this,
                R.style.AppTheme_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creating Account...");
        progressDialog.show();

        if (!validate()) {
            onSignupFailed();
            return;
        }

        _signupButton.setEnabled(false);

        final String name = _nameText.getText().toString();
        final String email = _emailText.getText().toString();
        final String password = _passwordText.getText().toString();

        showProgressDialog();

        ServiceUtil<Account> serviceUtil = new ServiceUtil<>(this);
        serviceUtil.getHashMap().put("fullname",name);
        serviceUtil.getHashMap().put("email",email);
        serviceUtil.getHashMap().put("password",email);
        serviceUtil.sendObjectData(Const.URL_SIGNUP, new OnSendObject() {
            @Override
            public void onTaskCompleted(boolean error, String message) {
                if (error) {
                    onSignupSuccess();
                } else {
                    onSignupFailed();
                }
            }
        });
    }


    public void onSignupSuccess() {
        hideProgressDialog();
        Toast.makeText(this, "Signup success! You can login now.", Toast.LENGTH_SHORT).show();
        _signupButton.setEnabled(true);
        finish();
    }

    public void onSignupFailed() {
        hideProgressDialog();
        _signupButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String name = _nameText.getText().toString();
        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();
        String confirmPassword = _confirmPasswordText.getText().toString();

        if (name.isEmpty() || name.length() < 3) {
            _nameText.setError("at least 3 characters");
            valid = false;
        } else {
            _nameText.setError(null);
        }

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

        if (confirmPassword.isEmpty() || !confirmPassword.equals(password)) {
            _confirmPasswordText.setError("password not match");
            valid = false;
        } else {
            _confirmPasswordText.setError(null);
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