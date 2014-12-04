package com.example.egor_gruk.first_app.ViewPager_package;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.egor_gruk.first_app.R;

/**
 * Created by Егор on 13.11.2014.
 */
public class EditorFragment999 extends Fragment {
    private static final String KEY_POSITION="position";
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View result=inflater.inflate(R.layout.editor999, container, false);
        EditText editor=(EditText)result.findViewById(R.id.editor99988);
        int position=getArguments().getInt(KEY_POSITION, -1);

        editor.setHint(String.format(getString(R.string.hint), position + 1));

        return(result);
    }
    static EditorFragment999 newInstance(int position) {
        EditorFragment999 frag=new EditorFragment999();
        Bundle args=new Bundle();

        args.putInt(KEY_POSITION, position);
        frag.setArguments(args);

        return(frag);
    }


}