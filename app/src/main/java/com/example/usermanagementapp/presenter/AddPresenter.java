package com.example.usermanagementapp.presenter;

import com.example.usermanagementapp.User;

public class AddPresenter {

    private AddCallback addCallback;

    public AddPresenter(AddCallback addCallback) {
        this.addCallback = addCallback;
    }

    public void add(User user) {
        if (user.isValidEmail() && user.isFilled()) {
            addCallback.success(user);
        } else {
            addCallback.fail(user);
        }
    }

    public interface AddCallback {
        void success(User user);

        void fail(User user);
    }
}

