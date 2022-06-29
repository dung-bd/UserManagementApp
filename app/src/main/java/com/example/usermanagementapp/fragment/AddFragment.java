package com.example.usermanagementapp.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.usermanagementapp.R;
import com.example.usermanagementapp.model.User;
import com.example.usermanagementapp.presenter.AddPresenter;
import com.google.android.material.snackbar.Snackbar;

public class AddFragment extends Fragment implements AddPresenter.AddCallback {
    private EditText Name, Email, Address;
    private Button Add;
    private AddPresenter addPresenter;
    private LinearLayout root;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        root = view.findViewById(R.id.layout);
        Name = view.findViewById(R.id.edt_name);
        Email = view.findViewById(R.id.edt_email);
        Address = view.findViewById(R.id.edt_address);
        Add = view.findViewById(R.id.btn_add);

        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleClickAdd();
            }
        });
    }

    private void handleClickAdd() {
        String name = Name.getText().toString().trim();
        String email = Email.getText().toString().trim();
        String address = Address.getText().toString().trim();
        addPresenter = new AddPresenter(this, getContext());

        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(email) || TextUtils.isEmpty(address)) {
            return;
        }

        User user = new User(name, email, address);

        addPresenter.add(user);

    }

    @Override
    public void success(User user) {
        Snackbar.make(root, "Add user successfully", Snackbar.LENGTH_SHORT).show();
        Name.setText("");
        Address.setText("");
        Email.setText("");
    }

    @Override
    public void fail(String msg) {
        Snackbar.make(root, msg, Snackbar.LENGTH_SHORT).show();
    }
}
