package com.example.aghasi.todolist.items;

import java.util.Date;

public class TodoItem {
    private String mTitle;
    private String mDescription;
    private Date mDate;
    private boolean mReminder;
    private boolean mRepeat;
    private int mPriority;

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public boolean isReminder() {
        return mReminder;
    }

    public void setReminder(boolean reminder) {
        mReminder = reminder;
    }

    public boolean isRepeat() {
        return mRepeat;
    }

    public void setRepeat(boolean repeat) {
        mRepeat = repeat;
    }

    public int getPriority() {
        return mPriority;
    }

    public void setPriority(int priority) {
        mPriority = priority;
    }
}
