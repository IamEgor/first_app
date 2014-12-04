package com.example.egor_gruk.first_app.start_for_result_package;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.egor_gruk.first_app.R;

/**
 * Created by Егор on 20.11.2014.
 */
public class FirstActivity  extends Activity{
    Button button;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_activ);
        textView = (TextView) findViewById(R.id.textView3);
        button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("com.example.egor_gruk.first_app.start_for_result_package.SecondActivity");
                startActivityForResult(intent, 1);
            }
        });
        if(savedInstanceState != null)
        {
            Bundle bundle = getIntent().getExtras();
            Toast.makeText(FirstActivity.this, "FirstActivity savedInstanceState ", Toast.LENGTH_LONG).show();
            textView.setText("" + bundle.getInt("rButtonId"));
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK)
        {
            textView.setText("" + data.getIntExtra("rButtonId", -1));
        }
    }
}
