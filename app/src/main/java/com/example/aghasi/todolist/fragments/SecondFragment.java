package com.example.aghasi.todolist.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.aghasi.todolist.R;
import com.example.aghasi.todolist.items.TodoItem;

import java.text.SimpleDateFormat;
import java.util.Date;

import static android.widget.CompoundButton.*;

public class SecondFragment extends Fragment {

    private Button mButtonSave;
    private EditText mEditTitle, mEditDescription;
    private TextView mTextDateTime, mTextPriority;
    private CheckBox mCheckBoxReminder, mCheckBoxRepeat;
    private RadioGroup mRadioGroupPeriod;
    private ImageView mImageUp, mImageDown;
    private TodoItem mTodoItem;


    @Override

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTodoItem = new TodoItem();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_second, container, false);
        idInitialization(view);
        mEditTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mTodoItem.setTitle(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mTextDateTime.setText(dateToStringParser(mTodoItem.getDate()));

        mEditDescription.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mTodoItem.setDescription(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mCheckBoxReminder.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mTodoItem.setReminder(isChecked);
            }
        });

        mCheckBoxRepeat.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mRadioGroupPeriod.setVisibility(View.VISIBLE);
                    mRadioGroupPeriod.check(R.id.radioButton_fragment_second_daily);
                } else {
                    mRadioGroupPeriod.setVisibility(View.GONE);
                    mRadioGroupPeriod.clearCheck();
                }
                mTodoItem.setRepeat(isChecked);
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void idInitialization(View view) {
        mButtonSave = view.findViewById(R.id.button_fragment_second_save);
        mEditTitle = view.findViewById(R.id.edit_fragment_second_title);
        mEditDescription = view.findViewById(R.id.edit_fragment_second_description);
        mTextDateTime = view.findViewById(R.id.text_fragment_second_date);
        mTextPriority = view.findViewById(R.id.text_fragment_second_priority);
        mCheckBoxReminder = view.findViewById(R.id.checkbox_fragment_second_reminder);
        mCheckBoxRepeat = view.findViewById(R.id.checkbox_fragment_second_repeat);
        mRadioGroupPeriod = view.findViewById(R.id.radioGroup_fragment_second_period);
        mImageUp = view.findViewById(R.id.image_fragment_second_up);
        mImageDown = view.findViewById(R.id.image_fragment_second_down);
    }

    private String dateToStringParser(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyy  kk:mm");
        String dateAndTime = simpleDateFormat.format(date);
        return dateAndTime;
    }
}
