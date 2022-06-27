package com.example.usermanagementapp.presenter;

import com.example.usermanagementapp.User;

public class DeletePresenter {
    private DeleteCallback deleteCallback;

    public DeletePresenter(DeleteCallback deleteCallback) {
        this.deleteCallback = deleteCallback;
    }

    public void delete(User user) {
        deleteCallback.success(user);
    }

    public interface DeleteCallback {
        void success(User user);
    }
}
