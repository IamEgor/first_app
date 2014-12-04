package com.example.egor_gruk.first_app.ViewPager_package;

import android.app.Fragment;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.egor_gruk.first_app.R;

import java.util.Random;


/**
 * Created by Егор on 20.11.2014.
 */
public class PageFragment extends Fragment {
    private static final String KEY_POSITION = "position";
//    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View result=inflater.inflate(R.layout.picture_layout, container, false);
        LinearLayout layout=(LinearLayout)result.findViewById(R.id.page_layout);
        int position=getArguments().getInt(KEY_POSITION, -1);

        int[] mas = getResources().getIntArray(R.array.pictures_array);
        layout.setBackground(getResources().getDrawable(mas[position]));

        return(result);

        //return super.onCreateView(inflater, container, savedInstanceState);
    }
    static PageFragment newInstance(int position) {
        PageFragment frag=new PageFragment();
        Bundle args=new Bundle();

        args.putInt(KEY_POSITION, position);
        frag.setArguments(args);

        return(frag);
    }
}
