package com.example.usermanagementapp.presenter;

import android.text.TextUtils;

public class SearchPresenter {
    private SearchCallback searchCallback;

    public SearchPresenter(SearchCallback searchCallback) {
        this.searchCallback = searchCallback;
    }

    public void search(String keyword) {
        if (!TextUtils.isEmpty(keyword)) {
            searchCallback.successSearch(keyword);
        } else {
            searchCallback.failSearch(keyword);
        }
    }

    public interface SearchCallback {
        void successSearch(String keyword);

        void failSearch(String keyword);
    }
}
