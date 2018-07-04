package com.example.aghasi.todolist.adapter.viewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aghasi.todolist.R;

public class TodoItemViewHolder extends RecyclerView.ViewHolder{

    private TextView mTitle;
    private TextView mDescription;
    private TextView mDateAndTime;
    private ImageView mDelete;
    private View mView;

    public TodoItemViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mTitle = itemView.findViewById(R.id.text_todo_item_view_title);
        mDescription = itemView.findViewById(R.id.text_todo_item_view_description);
        mDateAndTime = itemView.findViewById(R.id.text_todo_item_view_date);
        mDelete = itemView.findViewById(R.id.image_todo_item_delete);
    }

    public void setOnItemClickListener(View.OnClickListener clickListener) {
        mView.setOnClickListener(clickListener);
    }
    public void setOnRemoveClickListener(View.OnClickListener clickListener) {
        mDelete.setOnClickListener(clickListener);
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
