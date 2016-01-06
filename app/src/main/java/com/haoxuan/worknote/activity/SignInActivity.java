package com.haoxuan.worknote.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;

import com.haoxuan.worknote.R;

public class SignInActivity extends AppCompatActivity implements TextWatcher{
  private TextInputLayout user;
  private TextInputLayout password;
  private Button sign_in;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        user= (TextInputLayout) findViewById(R.id.user_name);
        user.getEditText().addTextChangedListener(this);
        password= (TextInputLayout) findViewById(R.id.user_password);
        password.getEditText().addTextChangedListener(this);
        sign_in= (Button) findViewById(R.id.sign_in);
        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
