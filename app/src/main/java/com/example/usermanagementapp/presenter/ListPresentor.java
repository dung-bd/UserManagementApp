package com.example.usermanagementapp.presenter;

import android.content.Context;
import android.text.TextUtils;

import com.example.usermanagementapp.database.UserDAO;
import com.example.usermanagementapp.database.UserDatabase;
import com.example.usermanagementapp.model.User;

import java.util.List;

public class ListPresentor {
    private Callback callback;
    private UserDAO userDAO;
    private List<User> listUser;
    private List<User> search;

    public ListPresentor(Context context, Callback callback) {
        this.callback = callback;
        this.userDAO = UserDatabase.getInstance(context.getApplicationContext()).userDAO();
    }

    public void load() {
        listUser = userDAO.getListUser();
        callback.successLoad(listUser);
    }

    public void clickUpdate(User user) {
        callback.successClick(user);
    }

    public void delete(User user) {
        userDAO.deleteUser(user);
        callback.successDelete(user);
    }

    public void search(String keyword) {
        if (!TextUtils.isEmpty(keyword)) {
            search = userDAO.searchUser(keyword);
            callback.successSearch(search);
        } else {
            callback.failSearch();
        }
    }

    public interface Callback {
        void successLoad(List<User> users);

        void successClick(User user);

        void successDelete(User user);

        void successSearch(List<User> searchList);

        void failSearch();
    }
}
