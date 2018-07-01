package com.example.aghasi.todolist.activities;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.aghasi.todolist.R;
import com.example.aghasi.todolist.adapter.TodoItemAdapter;
import com.example.aghasi.todolist.fragments.ItemListFragment;
import com.example.aghasi.todolist.fragments.ItemPageFragment;
import com.example.aghasi.todolist.items.TodoItem;

import static com.example.aghasi.todolist.fragments.ItemPageFragment.OnSaveActionListener;
import static com.example.aghasi.todolist.fragments.ItemPageFragment.newInstance;

public class MainActivity extends AppCompatActivity {

    private ImageView mImageAddItem;
    private ItemListFragment mItemListFragment;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mImageAddItem = findViewById(R.id.image_activity_main_add_item);

        setupAddButton();

        mItemListFragment = (ItemListFragment) getFragmentManager().findFragmentById(R.id.fragment_activity_main);
        mItemListFragment.setOnItemClicked(new TodoItemAdapter.OnItemClicked() {
            @Override
            public void onItemClicked(TodoItem todoItem) {
                showItemView(todoItem);
            }
        });
    }


    private void setupAddButton() {
        mImageAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showItemView(null);

            }
        });
    }

    private void popPageFragment() {
        getFragmentManager().popBackStack();
    }

    private void showItemView(TodoItem item) {
        ItemPageFragment fragment = newInstance(item);
        fragment.setOnSaveActionListener(new OnSaveActionListener() {
            @Override
            public void onCreated(TodoItem todoItem) {
                mItemListFragment.addItemToAdapterList(todoItem);
                popPageFragment();
            }

            @Override
            public void onUpdated(TodoItem todoItem) {
                mItemListFragment.updateItemInAdapterList(todoItem);
                popPageFragment();
            }
        });
        getFragmentManager()
                .beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .add(R.id.container_activity_main, fragment)
                .addToBackStack(ItemPageFragment.FRAGMENT_NAME)
                .commit();
    }
}
