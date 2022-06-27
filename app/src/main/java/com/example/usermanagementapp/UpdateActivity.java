package com.example.usermanagementapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.usermanagementapp.database.UserDatabase;
import com.example.usermanagementapp.presenter.UpdatePresenter;

public class UpdateActivity extends AppCompatActivity implements UpdatePresenter.UpdateCallback {
    private EditText edtName, edtEmail, edtAddress;
    private Button btnUpdateUser;
    private User user;
    private UpdatePresenter updatePresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_update);

        edtAddress = findViewById(R.id.edt_address_update);
        edtEmail = findViewById(R.id.edt_email_update);
        edtName = findViewById(R.id.edt_name_update);
        btnUpdateUser = findViewById(R.id.btn_update_user);
        updatePresenter = new UpdatePresenter(this);

        user = (User) getIntent().getExtras().get("object_user");
        if (user != null) {
            edtName.setText(user.getName());
            edtEmail.setText(user.getEmail());
            edtAddress.setText(user.getAddress());
        }

        btnUpdateUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateUser();
            }
        });
    }

    private void updateUser() {
        updatePresenter.update(user);
    }


    @Override
    public void success(User user) {
        String name = edtName.getText().toString().trim();
        String address = edtAddress.getText().toString().trim();
        String email = edtEmail.getText().toString().trim();
        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(address) || TextUtils.isEmpty(email)) {
            return;
        }
        user.setName(name);
        user.setAddress(address);
        user.setEmail(email);

        UserDatabase.getInstance(getApplicationContext()).userDAO().updateUser(user);
        Toast.makeText(this, "Update user successfully", Toast.LENGTH_SHORT).show();

        Intent intentResult = new Intent();
        setResult(Activity.RESULT_OK, intentResult);
        finish();
    }

    @Override
    public void fail(User user) {
        Toast.makeText(this, "Update failed", Toast.LENGTH_SHORT).show();
    }
}
