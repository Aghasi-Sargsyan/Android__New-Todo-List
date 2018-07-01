package com.example.aghasi.todolist.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.aghasi.todolist.R;
import com.example.aghasi.todolist.adapter.TodoItemAdapter;
import com.example.aghasi.todolist.items.TodoItem;

public class ItemListFragment extends Fragment {

    private TodoItemAdapter mTodoItemAdapter;

    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_item_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRecycler(view);
    }

    private void initRecycler(View view) {
        mTodoItemAdapter = new TodoItemAdapter();
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(mTodoItemAdapter);
    }

    public void addItemToAdapterList(TodoItem todoItem) {
        mTodoItemAdapter.addItemToList(todoItem);
        mTodoItemAdapter.notifyDataSetChanged();
    }

    public void updateItemInAdapterList(TodoItem todoItem) {
        mTodoItemAdapter.updateItem(todoItem);
        mTodoItemAdapter.notifyDataSetChanged();
    }

    public void setOnItemClicked(TodoItemAdapter.OnItemClicked onItemClicked) {
        mTodoItemAdapter.setOnItemClicked(onItemClicked);
    }
}
