package com.example.aghasi.todolist.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.aghasi.todolist.R;

public class MainFragment extends Fragment {
    private ImageView mImageAdd;
    private MainFragmentListener mOnMainFragmentListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        mImageAdd = view.findViewById(R.id.image_fragment_main_add);
        mImageAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnMainFragmentListener.onImageAddClicked();
            }
        });
        return view;
    }


    public void setOnMainFragmentListener(MainFragmentListener mainFragmentListener) {
        mOnMainFragmentListener = mainFragmentListener;
    }

    public interface MainFragmentListener {
        void onImageAddClicked();
    }
}
