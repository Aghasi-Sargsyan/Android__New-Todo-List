package com.example.aghasi.todolist.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.aghasi.todolist.R;
import com.example.aghasi.todolist.adapter.viewHolder.TodoItemViewHolder;
import com.example.aghasi.todolist.items.TodoItem;
import com.example.aghasi.todolist.util.DateUtil;

import java.util.ArrayList;
import java.util.List;

public class TodoItemAdapter extends RecyclerView.Adapter<TodoItemViewHolder> {

    private List<TodoItem> mItemList;

    public TodoItemAdapter() {
        mItemList = new ArrayList<>();
    }

    @NonNull
    @Override
    public TodoItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        TodoItemViewHolder viewHolder = new TodoItemViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_todo_item, parent, false));
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull TodoItemViewHolder holder, int position) {
        TodoItem item = mItemList.get(position);
        holder.getTitle().setText(item.getTitle());
        holder.getDescription().setText(item.getDescription());
        holder.getDateAndTime().setText(DateUtil.dateToStringParser(item.getDate()));
    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }

    public void addItemToList(TodoItem todoItem) {
        mItemList.add(todoItem);
    }
}
