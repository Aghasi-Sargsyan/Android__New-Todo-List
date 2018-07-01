package com.example.aghasi.todolist.items;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.aghasi.todolist.util.RepeatPeriod;

import java.util.Date;
import java.util.UUID;

public class TodoItem implements Parcelable {

    public static final int PRIORITY_MAX = 5;
    public static final int PRIORITY_MIN = 0;

    private String mTitle;
    private String mDescription;
    private Date mDate;
    private boolean mReminder;
    private int mPriority;
    private RepeatPeriod mRepeatPeriod;
    private UUID mId;

    public TodoItem() {
        mDate = new Date();
        mId = UUID.randomUUID();
    }


    private TodoItem(Parcel in) {
        mTitle = in.readString();
        mDescription = in.readString();
        mDate = new Date(in.readLong());
        mReminder = in.readByte() != 0;
        mPriority = in.readInt();
        int repeatTypeOrdinal = in.readInt();
        mRepeatPeriod = RepeatPeriod.values()[repeatTypeOrdinal];
        mId = (UUID)in.readSerializable();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof  TodoItem)) {
            return false;
        }

        TodoItem newItem = (TodoItem) obj;

        return newItem.mId.equals(this.mId);
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mTitle);
        parcel.writeString(mDescription);
        parcel.writeLong(mDate.getTime());
        parcel.writeInt((byte) (mReminder ? 1 : 0));
        parcel.writeInt(mPriority);
        parcel.writeInt(mRepeatPeriod.ordinal());
        parcel.writeSerializable(mId);
    }

    public static final Creator<TodoItem> CREATOR = new Creator<TodoItem>() {
        @Override
        public TodoItem createFromParcel(Parcel in) {
            return new TodoItem(in);
        }

        @Override
        public TodoItem[] newArray(int size) {
            return new TodoItem[size];
        }
    };

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

    public int getPriority() {
        return mPriority;
    }

    public void setPriority(int priority) {
        mPriority = priority;
    }

    public RepeatPeriod getRepeatPeriod() {
        return mRepeatPeriod;
    }

    public void setRepeatPeriod(RepeatPeriod repeatPeriod) {
        mRepeatPeriod = repeatPeriod;
    }

    @Override
    public int describeContents() {
        return 0;
    }
}
