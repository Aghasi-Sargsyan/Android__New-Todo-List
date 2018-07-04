package com.example.aghasi.todolist.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.aghasi.todolist.R;
import com.example.aghasi.todolist.items.TodoItem;
import com.example.aghasi.todolist.util.KeyboardUtil;

import java.util.Date;

import static com.example.aghasi.todolist.util.DateUtil.dateToStringParser;

public class ItemPageFragment extends Fragment {

    private static final String ARG_TODO_ITEM = "arg.todoitem";
    public static final String FRAGMENT_NAME = "Page Fragment";
    private static final int CREATE_ITEM = 0;
    private static final int EDIT_ITEM = 1;

    private int mItemMode;
    private EditText mEditTitle, mEditDescription;
    private TextView mTextDateTime, mTextPriority;
    private CheckBox mCheckBoxReminder, mCheckBoxRepeat;
    private RadioGroup mRadioGroupPeriod;
    private ImageView mImageUp, mImageDown;
    private Date mSelectedDate = new Date();
    private int mPriorityCounter = 0;
    private TodoItem mTodoItem;
    private OnSaveActionListener mOnSaveActionListener;
    private boolean isEditMode;

    public static ItemPageFragment newInstance(TodoItem todoItem) {

        ItemPageFragment fragment = new ItemPageFragment();

        Bundle args = new Bundle();
        args.putParcelable(ARG_TODO_ITEM, todoItem);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_save:
                if (isEditMode) {
                    enableFields();
                    item.setIcon(R.drawable.ic_menu_save);
                    isEditMode = false;
                } else {
                    save();
                }
                return true;
            default:
                return false;

        }
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        if (mItemMode == EDIT_ITEM) {
            menu.findItem(R.id.menu_save).setIcon(R.drawable.ic_mode_edit_black_24dp);
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        if (getArguments() != null) {
            mItemMode = CREATE_ITEM;
            if (getArguments().getParcelable(ARG_TODO_ITEM) != null) {
                mTodoItem = getArguments().getParcelable(ARG_TODO_ITEM);

                mItemMode = EDIT_ITEM;
                isEditMode = true;
            }
        }
    }

    private void setupEditMode() {
        if (mTodoItem != null) {
            mEditTitle.setText(mTodoItem.getTitle());
            mEditDescription.setText(mTodoItem.getDescription());
            mTextDateTime.setText(dateToStringParser(mTodoItem.getDate()));
        }
    }

    private void save() {
        KeyboardUtil.hideKeyboardFrom(getActivity(), getView());
        if (checkTitle ()) {
            if (mItemMode == CREATE_ITEM) {
                mTodoItem = new TodoItem();
            }
            mTodoItem.setTitle(mEditTitle.getText().toString());
            mTodoItem.setDescription(mEditDescription.getText().toString());
            mTodoItem.setDate(mSelectedDate);
            if (mOnSaveActionListener != null) {
                if (mItemMode == CREATE_ITEM) {
                    mOnSaveActionListener.onCreated(mTodoItem);
                } else {
                    mOnSaveActionListener.onUpdated(mTodoItem);
                }
            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_item_page, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        idInitialization(view);
        setupEditMode();
        if (mItemMode == EDIT_ITEM) {
            disableFields();
        }
    }

    private void idInitialization(View view) {
        mEditTitle = view.findViewById(R.id.edit_fragment_item_page_title);
        mEditDescription = view.findViewById(R.id.edit_fragment_item_page_description);
        mTextDateTime = view.findViewById(R.id.text_fragment_item_page_date);
        mTextPriority = view.findViewById(R.id.text_fragment_item_page_priority);
        mCheckBoxReminder = view.findViewById(R.id.checkbox_fragment_item_page_reminder);
        mCheckBoxRepeat = view.findViewById(R.id.checkbox_fragment_item_page_repeat);
        mRadioGroupPeriod = view.findViewById(R.id.radioGroup_fragment_item_page_period);
        mImageUp = view.findViewById(R.id.image_fragment_item_page_up);
        mImageDown = view.findViewById(R.id.image_fragment_item_page_down);
    }

    public void setOnSaveActionListener(OnSaveActionListener onSaveActionListener) {
        mOnSaveActionListener = onSaveActionListener;
    }

    public interface OnSaveActionListener {
        void onCreated(TodoItem todoItem);

        void onUpdated(TodoItem todoItem);
    }

    private void disableFields() {
        mEditTitle.setFocusable(false);
        mEditDescription.setFocusable(false);
        mCheckBoxReminder.setEnabled(false);
        mCheckBoxRepeat.setEnabled(false);
        mTextDateTime.setEnabled(false);
        mImageUp.setEnabled(false);
        mImageDown.setEnabled(false);
    }

    private void enableFields() {
        mEditTitle.setFocusable(true);
        mEditTitle.setFocusableInTouchMode(true);
        mEditDescription.setFocusable(true);
        mEditDescription.setFocusableInTouchMode(true);
        mCheckBoxReminder.setEnabled(true);
        mCheckBoxRepeat.setEnabled(true);
        mTextDateTime.setEnabled(true);
        mImageUp.setEnabled(true);
        mImageDown.setEnabled(true);
    }

    private boolean checkTitle (){
        if (mEditTitle.getText().toString().length() <= 0) {
            mEditTitle.setError("Title is empty");
            return false;
        }
        return true;
    }
}
