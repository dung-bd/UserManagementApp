package com.example.usermanagementapp.presenter;

import android.content.Context;
import android.text.TextUtils;

import com.example.usermanagementapp.User;
import com.example.usermanagementapp.database.UserDatabase;

import java.util.List;

public class ListPresentor {
    private Callback loadCallback;
    private Callback clickCallback;
    private Callback deleteCallback;
    private Callback searchCallback;
    private Context context;
    private List<User> listUser;
    private List<User> search;

    public ListPresentor(Callback loadCallback, Callback clickCallback, Callback deleteCallback, Callback searchCallback, Context context) {
        this.loadCallback = loadCallback;
        this.clickCallback = clickCallback;
        this.deleteCallback = deleteCallback;
        this.searchCallback = searchCallback;
        this.context = context;
    }

    public void load(){
        listUser = UserDatabase.getInstance(context.getApplicationContext()).userDAO().getListUser();
        loadCallback.successLoad(listUser);
    }

    public void clickUpdate(User user) {
        clickCallback.successClick(user);
    }

    public void delete(User user) {
        UserDatabase.getInstance(context).userDAO().deleteUser(user);
        deleteCallback.successDelete(user);
    }

    public void search(String keyword) {
        if (!TextUtils.isEmpty(keyword)) {
            search = UserDatabase.getInstance(context).userDAO().searchUser(keyword);
            searchCallback.successSearch(search);
        } else {
            searchCallback.failSearch();
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
