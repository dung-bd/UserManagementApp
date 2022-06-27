package com.example.usermanagementapp.presenter;

import android.content.Context;

import com.example.usermanagementapp.User;
import com.example.usermanagementapp.database.UserDatabase;

public class AddPresenter {

    private AddCallback addCallback;
    private Context context;

    public AddPresenter(AddCallback addCallback, Context context) {
        this.addCallback = addCallback;
        this.context = context;
    }

    public void add(User user) {
        String msg;

        if (user == null) {
            msg = "Please fill";
            addCallback.failAdd(msg);
        } else if (user.getName() == null) {
            msg = "Please fill name";
            addCallback.failAdd(msg);
        } else if (user.getEmail() == null) {
            msg = "Please fill email";
            addCallback.failAdd(msg);
        } else if (user.getAddress() == null) {
            msg = "Please fill address";
            addCallback.failAdd(msg);
        } else {
            UserDatabase.getInstance(context).userDAO().insertUser(user);
           addCallback.success(user);
        }
    }

    public interface AddCallback {
        void success(User user);

        void failAdd(String msg);
    }
}

