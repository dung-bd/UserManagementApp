package com.example.usermanagementapp.presenter;

import android.content.Context;

import com.example.usermanagementapp.database.UserDAO;
import com.example.usermanagementapp.database.UserDatabase;
import com.example.usermanagementapp.model.User;

public class AddPresenter {

    private AddCallback addCallback;
    private UserDAO userDAO;

    public AddPresenter(AddCallback addCallback, Context context) {
        this.addCallback = addCallback;
        userDAO = UserDatabase.getInstance(context).userDAO();
    }

    public void add(User user) {
        String msg;

        if (user == null) {
            msg = "Please fill";
            addCallback.fail(msg);
        } else if (user.getName() == null) {
            msg = "Please fill name";
            addCallback.fail(msg);
        } else if (user.getEmail() == null) {
            msg = "Please fill email";
            addCallback.fail(msg);
        } else if (user.getAddress() == null) {
            msg = "Please fill address";
            addCallback.fail(msg);
        } else {
            userDAO.insertUser(user);
            addCallback.success(user);
        }
    }

    public interface AddCallback {
        void success(User user);

        void fail(String msg);
    }
}

