package com.example.usermanagementapp.presenter;

import com.example.usermanagementapp.User;

public class ClickUpdatePresenter {
    private ClickUpdateCallback clickUpdateCallback;

    public ClickUpdatePresenter(ClickUpdateCallback clickUpdateCallback) {
        this.clickUpdateCallback = clickUpdateCallback;
    }

    public void clickUpdate(User user) {
        clickUpdateCallback.successClick(user);
    }

    public interface ClickUpdateCallback {
        void successClick(User user);
    }
}
