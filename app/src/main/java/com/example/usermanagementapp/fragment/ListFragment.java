package com.example.usermanagementapp.fragment;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.usermanagementapp.R;
import com.example.usermanagementapp.activity.UpdateActivity;
import com.example.usermanagementapp.adapter.UserAdapter;
import com.example.usermanagementapp.model.User;
import com.example.usermanagementapp.presenter.ListPresentor;

import java.util.ArrayList;
import java.util.List;

public class ListFragment extends Fragment implements ListPresentor.Callback {

    private static final int MY_CODE = 10;
    private static final String KEY_USER = "object_user";
    private RecyclerView rcvUser;
    private UserAdapter userAdapter;
    private List<User> listUser;
    private SwipeRefreshLayout swipeRefreshLayout;
    private EditText edtSearch;
    private ListPresentor listPresentor;

    public static void starter(Activity activity, int code, User user) {
        Intent intent = new Intent(activity, UpdateActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(KEY_USER, user);
        intent.putExtras(bundle);
        activity.startActivityForResult(intent, code);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        edtSearch = view.findViewById(R.id.edt_search);
        rcvUser = view.findViewById(R.id.rcv_user);
        listPresentor = new ListPresentor(getContext(), this);
        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout);

        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData();
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        listUser = new ArrayList<>();
        userAdapter = new UserAdapter(listUser, new UserAdapter.IclickItemUser() {
            @Override
            public void updateUser(User user) {
                clickUpdateUser(user);
            }

            @Override
            public void deleteUser(User user) {
                clickDeleteUser(user);
            }
        });

        userAdapter.setData(listUser);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext(), RecyclerView.VERTICAL, false);
        rcvUser.setLayoutManager(linearLayoutManager);
        rcvUser.setAdapter(userAdapter);

        edtSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    String keyword = edtSearch.getText().toString().trim();
                    listPresentor.search(keyword);
                }
                return false;
            }
        });

        loadData();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == MY_CODE && resultCode == Activity.RESULT_OK) {
            loadData();
        }
    }

    @Override
    public void successDelete(User user) {
        new AlertDialog.Builder(getContext())
                .setTitle("Confirm delete user")
                .setMessage("Are you sure")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getContext(), "Delete user successfully", Toast.LENGTH_SHORT).show();

                        loadData();
                    }
                })
                .setNegativeButton("No", null)
                .show();

    }


    @Override
    public void successClick(User user) {
        starter(getActivity(), MY_CODE, user);
    }

    @Override
    public void successSearch(List<User> userList) {
        userAdapter.setData(userList);
    }

    @Override
    public void failSearch() {
        Toast.makeText(getContext(), "Please fill in the blank", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void successLoad(List<User> users) {
        userAdapter.setData(users);
    }


    private void clickUpdateUser(User user) {
        listPresentor.clickUpdate(user);
    }

    private void clickDeleteUser(final User user) {
        listPresentor.delete(user);
    }

    private void loadData() {
        listPresentor.load();
    }

    @Override
    public void onResume() {
        super.onResume();
        loadData();
    }
}
