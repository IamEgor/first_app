package com.example.egor_gruk.first_app.start_for_result_package;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.egor_gruk.first_app.R;

/**
 * Created by Егор on 20.11.2014.
 */
public class SecondActivity extends Activity {
    RadioGroup radioGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activ);

        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Bundle bundle = new Bundle();
        bundle.putInt("rButtonId", radioGroup.getCheckedRadioButtonId());
        Toast.makeText(SecondActivity.this, "SecondActivity onBackPressed " + radioGroup.getCheckedRadioButtonId(), Toast.LENGTH_LONG).show();
        Intent intent = new Intent("com.example.egor_gruk.first_app.start_for_result_package.FirstActivity");
        intent.putExtras(bundle);


    }
}
