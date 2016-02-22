package com.freescale.joinin;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.freescale.joinin.Utilities.*;

public class FinishEventActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish_event);
        setTitle(getSharedPreferences(DATA_FILE, 0).getString(KEY_EVENT_NAME, null));
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_finish_event, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    public void confirmFinish(View view) {
        EditText editText = (EditText) findViewById(R.id.edit_verify_coreid);
        String coreID = editText.getText().toString();
        String actualCoreID = getSharedPreferences(DATA_FILE, 0).getString(KEY_CORE_ID, null);
        if (coreID.equalsIgnoreCase(actualCoreID)) {
            Intent intent = new Intent(this, MainActivity.class);
            boolean result = sendData();
            if (result) startActivity(intent);
        } else {
            alertMessage(getString(R.string.alert_invalid_coreid), this);
        }
    }

    public void backToReg(View view) {
        Intent intent = new Intent(this, EventActivity.class);
        startActivity(intent);
    }

    public boolean sendData() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            String eventName = getSharedPreferences(DATA_FILE, 0).getString(KEY_EVENT_NAME, null);
            try {
                File file = new File(getFilesDir().getAbsolutePath() + "/" + INFO_FILE);
                Scanner sc = new Scanner(file);
                String line, url;
                String[] data;
                List<String> urls = new ArrayList<>();
                while (sc.hasNextLine()) {
                    url = BASE_URL + "event=" + eventName;
                    line = sc.nextLine();
                    data = line.split(",");
                    url += "&name=" + data[0];
                    url += "&lastname=" + data[1];
                    url += "&email=" + data[2];
                    urls.add(url);
                }
                String[] strUrls = new String[urls.size()];
                new SendDataTask().execute(urls.toArray(strUrls));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            return true;
        } else {
            alertMessage(getString(R.string.alert_no_connection), this);
            return false;
        }
    }

}
