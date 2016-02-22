package com.freescale.joinin;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import static com.freescale.joinin.Utilities.*;

public class EventActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        SharedPreferences sp = getSharedPreferences(DATA_FILE, 0);
        String eventName = sp.getString(KEY_EVENT_NAME, null);
        setTitle(eventName);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_event, menu);
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

    public void joinInEvent(View view) throws IOException {
        Intent intent = new Intent(this, FinishJoinInActivity.class);
        EditText editText = (EditText) findViewById(R.id.edit_first_name);
        String firstName = editText.getText().toString();
        editText = (EditText) findViewById(R.id.edit_last_name);
        String lastName = editText.getText().toString();
        editText = (EditText) findViewById(R.id.edit_email);
        String email = editText.getText().toString();
        editText = (EditText) findViewById(R.id.edit_phone_num);
        String phoneNum = editText.getText().toString();

        if (firstName.equalsIgnoreCase("")) {
            alertMessage(getString(R.string.alert_no_first_name), this);
            return;
        }

        if (lastName.equalsIgnoreCase("")) {
            alertMessage(getString(R.string.alert_no_last_name), this);
            return;
        }

        if (email.equalsIgnoreCase("")) {
            alertMessage(getString(R.string.alert_no_email), this);
            return;
        }

        SharedPreferences sp = getSharedPreferences(DATA_FILE, 0);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(KEY_FIRST_NAME, firstName);
        editor.apply();

        FileWriter file = new FileWriter(getFilesDir().getAbsolutePath() + "/" + INFO_FILE, true);
        PrintWriter pw = new PrintWriter(file);
        pw.append(firstName + "," + lastName + "," + email + "," + phoneNum + "\n");
        pw.close();
        startActivity(intent);
    }

    public void finishEvent(View view) {
        Intent intent = new Intent(this, FinishEventActivity.class);
        startActivity(intent);
    }
}
