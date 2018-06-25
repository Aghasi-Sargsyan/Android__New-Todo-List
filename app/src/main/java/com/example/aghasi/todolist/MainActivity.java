package com.example.aghasi.todolist;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.aghasi.todolist.fragments.SecondFragment;

public class MainActivity extends AppCompatActivity {

    private FragmentManager mFragmentManager;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFragmentManager = getFragmentManager();

        Fragment container = mFragmentManager.findFragmentById(R.id.container);
        if (container == null) {
            container = new SecondFragment();
            mFragmentManager.beginTransaction()
                    .add(R.id.container, container)
                    .commit();
        }
    }
}
