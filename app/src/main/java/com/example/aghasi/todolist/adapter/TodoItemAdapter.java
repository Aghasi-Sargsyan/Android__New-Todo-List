package com.example.aghasi.todolist.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.aghasi.todolist.R;
import com.example.aghasi.todolist.adapter.viewHolder.TodoItemViewHolder;
import com.example.aghasi.todolist.items.TodoItem;
import com.example.aghasi.todolist.util.DateUtil;

import java.util.ArrayList;
import java.util.List;

public class TodoItemAdapter extends RecyclerView.Adapter<TodoItemViewHolder> {

    private List<TodoItem> mItemList;
    private OnItemClicked mOnItemClicked;

    public TodoItemAdapter() {
        mItemList = new ArrayList<>();
    }

    @NonNull
    @Override
    public TodoItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TodoItemViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_todo_item, parent, false));
    }


    @Override
    public void onBindViewHolder(@NonNull final TodoItemViewHolder holder, final int position) {
        final TodoItem item = mItemList.get(position);
        holder.getTitle().setText(item.getTitle());
        holder.getDescription().setText(item.getDescription());
        holder.getDateAndTime().setText(DateUtil.dateToStringParser(item.getDate()));
        holder.setOnItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                   mOnItemClicked.onItemClicked(item);
            }
        });
        holder.setOnRemoveClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClicked.onRemoveClicked(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }

    public void addItemToList(TodoItem todoItem) {
        mItemList.add(todoItem);
    }

    public void removeItemFromList(int position) {
        mItemList.remove(position);
    }

    public void updateItem(TodoItem todoItem) {
        for (int i = 0; i < mItemList.size(); i++) {
            if (todoItem.equals(mItemList.get(i))) {
                mItemList.set(i, todoItem);
                break;
            }
        }
    }

    public void setOnItemClicked(OnItemClicked onItemClicked) {
        mOnItemClicked = onItemClicked;
    }

    public interface OnItemClicked {
        void onItemClicked(TodoItem todoItem);

        void onRemoveClicked(int position);
    }
}
