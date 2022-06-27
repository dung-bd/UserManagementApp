package com.example.usermanagementapp.presenter;

import com.example.usermanagementapp.User;

public class UpdatePresenter {
    private UpdateCallback updateCallback;

    public UpdatePresenter(UpdateCallback updateCallback) {
        this.updateCallback = updateCallback;
    }

    public void update(User user) {
        if (user.isValidEmail() && user.isFilled()) {
            updateCallback.success(user);
        } else {
            updateCallback.fail(user);
        }
    }

    public interface UpdateCallback {
        void success(User user);

        void fail(User user);
    }
}
