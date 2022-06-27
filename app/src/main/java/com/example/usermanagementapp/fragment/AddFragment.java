package com.example.usermanagementapp.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.usermanagementapp.R;
import com.example.usermanagementapp.User;
import com.example.usermanagementapp.database.UserDatabase;
import com.example.usermanagementapp.presenter.AddPresenter;

public class AddFragment extends Fragment implements AddPresenter.AddCallback {
    private EditText edtName, edtEmail, edtAddress;
    private Button btnAdd;
    private AddPresenter addPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        edtName = view.findViewById(R.id.edt_name);
        edtEmail = view.findViewById(R.id.edt_email);
        edtAddress = view.findViewById(R.id.edt_address);
        btnAdd = view.findViewById(R.id.btn_add);
        addPresenter = new AddPresenter(this, getContext());

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleClickAdd();
            }
        });
    }

    private void handleClickAdd() {
        String name = edtName.getText().toString().trim();
        String email = edtEmail.getText().toString().trim();
        String address = edtAddress.getText().toString().trim();

        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(email) || TextUtils.isEmpty(address)) {
            return;
        }

        User user = new User(name, email, address);

        addPresenter.add(user);

        edtName.setText("");
        edtAddress.setText("");
        edtEmail.setText("");
    }

    @Override
    public void success(User user) {
        Toast.makeText(this.getContext(), "Add user sucessfully", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void failAdd(String msg) {
        Toast.makeText(this.getContext(), msg, Toast.LENGTH_SHORT).show();
    }
}
