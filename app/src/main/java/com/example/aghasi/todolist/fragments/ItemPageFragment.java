package com.example.aghasi.todolist.fragments;

import android.app.DatePickerDialog;
import android.app.Fragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toolbar;

import com.example.aghasi.todolist.R;
import com.example.aghasi.todolist.util.DateUtil;
import com.example.aghasi.todolist.util.RepeatPeriod;
import com.example.aghasi.todolist.items.TodoItem;

import java.util.Calendar;
import java.util.Date;

import static android.widget.CompoundButton.*;

public class ItemPageFragment extends Fragment {

    private static final String ARG_TODO_ITEM = "arg.todoitem";
    private Button mButtonSave;
    private EditText mEditTitle, mEditDescription;
    private TextView mTextDateTime, mTextPriority;
    private CheckBox mCheckBoxReminder, mCheckBoxRepeat;
    private RadioGroup mRadioGroupPeriod;
    private ImageView mImageUp, mImageDown;
    private Date mSelectedDate;
    private int mPriorityCounter = 0;
    private TodoItem mTodoItem;

    private OnFragmentInteractionListener mOnFragmentInteractionListener;


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_save) {
            if (mOnFragmentInteractionListener != null) {
                mOnFragmentInteractionListener.onItemCreated(createTodoItemFromInput());
            }
        }
        return true;
    }

    public static ItemPageFragment newInstance(TodoItem todoItem) {
        ItemPageFragment fragment = new ItemPageFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_TODO_ITEM, todoItem);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        mTodoItem = getArguments().getParcelable(ARG_TODO_ITEM);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_page, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        idInitialization(view);

        mTextDateTime.setText(DateUtil.dateToStringParser(mTodoItem.getDate()));

        mCheckBoxRepeat.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    mRadioGroupPeriod.setVisibility(View.VISIBLE);
                    mRadioGroupPeriod.check(R.id.radioButton_fragment_item_page_daily);
                } else {
                    mRadioGroupPeriod.setVisibility(View.GONE);
                    mTodoItem.setRepeatPeriod(RepeatPeriod.NONE);
                }
            }
        });
        mImageUp.setOnClickListener(mOnClickListener);
        mImageDown.setOnClickListener(mOnClickListener);
        mTextDateTime.setOnClickListener(mOnClickListener);
    }

    private TodoItem createTodoItemFromInput() {
        if (mTodoItem == null) {
            mTodoItem = new TodoItem();
        }
        mTodoItem.setTitle(mEditTitle.getText().toString());
        mTodoItem.setDescription(mEditDescription.getText().toString());
        if (mSelectedDate != null) {
            mTodoItem.setDate(mSelectedDate);
        }
        mTodoItem.setReminder(mCheckBoxReminder.isChecked());
        mTodoItem.setPriority(mPriorityCounter);
        if (mCheckBoxRepeat.isChecked()) {
            switch (mRadioGroupPeriod.getCheckedRadioButtonId()) {
                case R.id.radioButton_fragment_item_page_weekly:
                    mTodoItem.setRepeatPeriod(RepeatPeriod.WEEKLY);
                    break;
                case R.id.radioButton_fragment_item_page_monthly:
                    mTodoItem.setRepeatPeriod(RepeatPeriod.MONTHLY);
            }
        }

        return mTodoItem;
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

    private void increasePriority() {
        mPriorityCounter = Math.min(++mPriorityCounter, TodoItem.PRIORITY_MAX);
        mTextPriority.setText("Priority " + String.valueOf(mPriorityCounter));
    }

    private void decreasePriority() {
        mPriorityCounter = Math.max(--mPriorityCounter, TodoItem.PRIORITY_MIN);
        mTextPriority.setText("Priority " + String.valueOf(mPriorityCounter));
    }

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.image_fragment_item_page_up:
                    increasePriority();
                    break;
                case R.id.image_fragment_item_page_down:
                    decreasePriority();
                    break;
                case R.id.text_fragment_item_page_date:
                    chooseDateAndTime();
                    break;
            }
        }
    };

    public interface OnFragmentInteractionListener {
        void onItemCreated(TodoItem todoItem);
    }

    public void setOnFragmentInteractionListener(OnFragmentInteractionListener listener) {
        mOnFragmentInteractionListener = listener;
    }

    private void chooseDateAndTime() {
        final Calendar calendar = Calendar.getInstance();
        new DatePickerDialog(getActivity(), R.style.SpinnerDate, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                calendar.set(year, monthOfYear, dayOfMonth);
                new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        calendar.set(Calendar.MINUTE, minute);

                        mSelectedDate = calendar.getTime();

                        mTextDateTime.setText(DateUtil.dateToStringParser(calendar.getTime()));
                    }
                }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true).show();
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE)).show();

    }
}
