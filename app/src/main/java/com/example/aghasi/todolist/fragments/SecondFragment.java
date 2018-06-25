package com.example.aghasi.todolist.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.aghasi.todolist.R;

import java.util.Date;

public class SecondFragment extends Fragment{

    public static final String FRAGMENT_TAG = "Second Fragment";

    private Button mButtonSave;
    private EditText mEditTitle, mEditDescription;
    private TextView mTextDateTime, mTextPriority;
    private CheckBox mCheckBoxReminder, mCheckBoxRepeat;
    private RadioGroup mRadioGroupPeriod;
    private ImageView mImageUp, mImageDown;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_second, container, false);
        idInitialization(view);


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

}
