package com.example.usermanagementapp.presenter;

import android.content.Context;

import com.example.usermanagementapp.database.UserDatabase;
import com.example.usermanagementapp.model.User;

public class UpdatePresenter {
    private UpdateCallback updateCallback;
    private Context context;
    private User original;

    public UpdatePresenter(UpdateCallback updateCallback, Context context) {
        this.updateCallback = updateCallback;
        this.context = context;
    }

    public void update(User user) {
        String msg;

        if (user == null) {
            msg = "Please fill";
            updateCallback.fail(msg);
        } else if (user.getName() == null) {
            msg = "Please fill name";
            updateCallback.fail(msg);
        } else if (user.getEmail() == null) {
            msg = "Please fill email";
            updateCallback.fail(msg);
        } else if (user.getAddress() == null) {
            msg = "Please fill address";
            updateCallback.fail(msg);
        } else {
            user.setId(original.getId());
            UserDatabase.getInstance(context).userDAO().updateUser(user);
            updateCallback.success(user);
        }
    }

    public void setOriginalData(User user) {
        original = user;
    }

    public interface UpdateCallback {
        void success(User user);

        void fail(String msg);
    }
}
