package com.example.aghasi.todolist;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.aghasi.todolist.fragments.MainFragment;
import com.example.aghasi.todolist.fragments.SecondFragment;

public class MainActivity extends AppCompatActivity {

    private FragmentManager mFragmentManager;
    private MainFragment mMainFragment;
    private SecondFragment mSecondFragment;
    private static final int CONTAINER_ID = R.id.container;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initFragments();
        mFragmentManager = getFragmentManager();

        openMainFragment();

        mMainFragment.setOnMainFragmentListener(new MainFragment.MainFragmentListener() {
            @Override
            public void onImageAddClicked() {
                openSecondFragment();
            }
        });
    }

    private void openMainFragment() {
        mFragmentManager.beginTransaction().
                add(CONTAINER_ID, mMainFragment).
                commit();
    }

    private void openSecondFragment() {
        Bundle addButtonBundle = new Bundle();
        addButtonBundle.putBoolean("addItem", true);
        mFragmentManager.beginTransaction().
                replace(CONTAINER_ID, mSecondFragment).
                addToBackStack(SecondFragment.FRAGMENT_TAG).
                commit();
    }


    private void initFragments() {
        mMainFragment = new MainFragment();
        mSecondFragment = new SecondFragment();
    }
}
