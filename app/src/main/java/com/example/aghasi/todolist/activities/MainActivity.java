package com.example.aghasi.todolist.activities;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ImageView;

import com.example.aghasi.todolist.R;
import com.example.aghasi.todolist.fragments.ItemPageFragment;
import com.example.aghasi.todolist.fragments.ItemListFragment;
import com.example.aghasi.todolist.items.TodoItem;

public class MainActivity extends AppCompatActivity {

    private ImageView mImageAddItem;
    private ItemListFragment mItemsListFragment;
    private ItemPageFragment.OnFragmentInteractionListener mListener = new ItemPageFragment.OnFragmentInteractionListener() {
        @Override
        public void onItemCreated(TodoItem todoItem) {
            mItemsListFragment.addTodoItem(todoItem);
            getFragmentManager().popBackStack();
        }
    };

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        idInitialization();

        mImageAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openNewItemPageFragment();
            }
        });
    }

    private void openNewItemPageFragment() {
        ItemPageFragment itemPageFragment = ItemPageFragment.newInstance(new TodoItem());
        itemPageFragment.setOnFragmentInteractionListener(mListener);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.constraint_main, itemPageFragment)
                .addToBackStack("EditItemFragment")
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }

    private void idInitialization() {
        mImageAddItem = findViewById(R.id.image_activity_main_add_item);
        mItemsListFragment = (ItemListFragment) (getFragmentManager()
                .findFragmentById(R.id.fragment_activity_main));
    }

}