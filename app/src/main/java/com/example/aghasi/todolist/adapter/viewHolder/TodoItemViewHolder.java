package com.example.aghasi.todolist.adapter.viewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.aghasi.todolist.R;

public class TodoItemViewHolder extends RecyclerView.ViewHolder{

    private TextView mTitle;
    private TextView mDescription;
    private TextView mDateAndTime;
    private View mView;

    public TodoItemViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mTitle = itemView.findViewById(R.id.text_todo_item_view_title);
        mDescription = itemView.findViewById(R.id.text_todo_item_view_description);
        mDateAndTime = itemView.findViewById(R.id.text_todo_item_view_date);
    }

    public void setOnClickListener(View.OnClickListener clickListener) {
        mView.setOnClickListener(clickListener);
    }

    public TextView getTitle() {
        return mTitle;
    }

    public TextView getDescription() {
        return mDescription;
    }

    public TextView getDateAndTime() {
        return mDateAndTime;
    }

}
