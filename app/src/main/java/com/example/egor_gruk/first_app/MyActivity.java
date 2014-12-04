package com.example.egor_gruk.first_app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockActivity;
import com.example.egor_gruk.first_app.database_package.Hashing;
import com.example.egor_gruk.first_app.database_package.MySQLiteClass;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.security.NoSuchAlgorithmException;

public class MyActivity extends Activity {
    EditText loginField, passwordField;
    Button button, saveButton;
    private static final String TYPE_GET = "GET", TYPE_PUT = "PUT";
    MySQLiteClass database;
    String DEVICE_ID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        //<инициализация полей и переменных>
        loginField = (EditText) findViewById(R.id.loginField);
        passwordField = (EditText) findViewById(R.id.passwordField);
        saveButton = (Button) findViewById(R.id.button4);
        button = (Button) findViewById(R.id.button);
        database = new MySQLiteClass(MyActivity.this);
        DEVICE_ID = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
        //</инициализация полей и переменных>

        //<сохранение логина и пароля при повороте>
        if (savedInstanceState != null)
        {
            loginField.setText(savedInstanceState.getString("loginText"));
            passwordField.setText(savedInstanceState.getString("passwordText"));
        }
        //<сохранение логина и пароля при повороте>

        //<добавление слушателей>
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent("com.example.egor_gruk.first_app.SomeAdapter");
                //startActivity(intent);
                Toast.makeText(MyActivity.this, database.fetchRecord(loginField.getText().toString()), Toast.LENGTH_LONG).show();
            }
        });
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String hashed1 = "";
                String hashed2 = "";
                try {
                    hashed1 = Hashing.md5(passwordField.getText().toString());
                    hashed2 = Hashing.md5(DEVICE_ID);
                    hashed2 = Hashing.md5(hashed2, hashed1);
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
                database.addRecord(loginField.getText().toString(), passwordField.getText().toString(), hashed1, hashed2);
            }
        });
        //</добавление слушателей>







        //<подключение и передача>
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (networkInfo != null && networkInfo.isConnected())
        {
            Toast.makeText(MyActivity.this, "is connected",Toast.LENGTH_LONG).show();
            String stringUrl = "https://mcd.cloudant.com/course/egori";
//            new DownloadWebPageTask().execute(stringUrl);
        }
        else
        {
           // Toast.makeText(MyActivity.this, "not connected",Toast.LENGTH_LONG).show();
        }
        //</подключение и передача>
    }

    private class DownloadWebPageTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {

            // params comes from the execute() call: params[0] is the url.
            try
            {
                return downloadUrl(urls[0], TYPE_GET);
            } catch (IOException e)
            {
                return "Unable to retrieve web page. URL may be invalid.";
            }
        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
            Toast.makeText(MyActivity.this, result, Toast.LENGTH_LONG).show();
            Log.d("put result",result);
        }
    }
/////////////////
    private String downloadUrl(String myUrl, String methodType) throws IOException {
        InputStream is = null;
        // Only display the first 500 characters of the retrieved
        // web page content.
        int len = 1024;

        try {
            URL url = new URL(myUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod(methodType);
            conn.addRequestProperty("Authorization", "Basic eW91Z2h0dGVzdHJ1Y2hlbnRlbmdzdHJpOkRLMU9rSzNoaTBNOFVwMjFzMmlVaTVDQQ==");
            conn.setDoInput(true);
            if (methodType.equals(TYPE_PUT))
            {
                OutputStream out = conn.getOutputStream();
                writeStream(out);
            }

            // Starts the query
            conn.connect();
            int response = conn.getResponseCode();
            Log.d("DEBUG_TAG", "The response is: " + response);
            Log.d("DEBUG_TAG", "The ResponseMessage is: " + conn.getResponseMessage());
            is = conn.getInputStream();

            // Convert the InputStream into a string
            String contentAsString = readIt(is, len);
            return contentAsString;

            // Makes sure that the InputStream is closed after the app is
            // finished using it.
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }

    private void writeStream(OutputStream out) throws IOException {
        String s = "{\"key\" : \"преподымать\", \"value\" : \"иву\"}";
        out.write(s.getBytes((Charset.forName("UTF-8"))));
    }

    public String readIt(InputStream stream, int len) throws IOException {
        Reader reader;
        reader = new InputStreamReader(stream, "UTF-8");
        char[] buffer = new char[len];
        reader.read(buffer);
        return new String(buffer);
    }
/////////////////
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if(loginField.getText().toString() != null) {
            outState.putString("loginText", loginField.getText().toString());
        }
        if(passwordField.getText().toString() != null) {
            outState.putString("passwordText", passwordField.getText().toString());
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
