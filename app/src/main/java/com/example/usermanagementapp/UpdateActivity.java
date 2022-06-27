package com.example.usermanagementapp;

import android.app.Activity;
import android.content.Context;
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

public class UpdateActivity extends AppCompatActivity {
    private static final String KEY_USER = "object_user";

    private EditText edtName, edtEmail, edtAddress;
    private Button btnUpdateUser;

    private UpdatePresenter updatePresenter;

    public static void starter(Activity activity, int code, User user) {
        Intent intent = new Intent(activity, UpdateActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(KEY_USER, user);
        intent.putExtras(bundle);
        activity.startActivityForResult(intent, code);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_update);

        edtAddress = findViewById(R.id.edt_address_update);
        edtEmail = findViewById(R.id.edt_email_update);
        edtName = findViewById(R.id.edt_name_update);
        btnUpdateUser = findViewById(R.id.btn_update_user);
        updatePresenter = new UpdatePresenter(new PresenterHandle(this), this);

        User user = (User) getIntent().getExtras().get(KEY_USER);
        if (user != null) {
            edtName.setText(user.getName());
            edtEmail.setText(user.getEmail());
            edtAddress.setText(user.getAddress());
        }
        updatePresenter.setOriginalData(user);

        btnUpdateUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateUser();
            }
        });
    }

    private void updateUser() {
        String name = edtName.getText().toString().trim();
        String address = edtAddress.getText().toString().trim();
        String email = edtEmail.getText().toString().trim();

        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(email) || TextUtils.isEmpty(address)) {
            return;
        }

        User user = new User(name, email, address);

        updatePresenter.update(user);
    }

    private class PresenterHandle implements UpdatePresenter.UpdateCallback {
        private Context context;

        public PresenterHandle(Context context) {
            this.context = context;
        }

        @Override
        public void success(User user) {
            Toast.makeText(context, "Update user successfully", Toast.LENGTH_SHORT).show();

            Intent intentResult = new Intent();
            setResult(Activity.RESULT_OK, intentResult);
            finish();
        }

        @Override
        public void fail(String msg) {
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
        }
    }
}
