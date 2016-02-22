package com.freescale.joinin;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.view.View;

import static com.freescale.joinin.Utilities.*;

public class CreateEventActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);
        SharedPreferences sp = getSharedPreferences(DATA_FILE, 0);
        String coreID = sp.getString(KEY_CORE_ID, null);
        TextView textView = new TextView(this);
        textView.setTextColor(getResources().getColor(R.color.black));
        textView.setText("Welcome, " + coreID);
        textView.setTextAppearance(this, android.R.style.TextAppearance_Medium);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(RelativeLayout.ABOVE, R.id.text_enter_event_name);
        layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL, 1);
        layoutParams.addRule(RelativeLayout.CENTER_VERTICAL, 1);
        layoutParams.setMargins(0, 0, 0, 20);
        textView.setLayoutParams(layoutParams);
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.activity_create_event);
        relativeLayout.addView(textView);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_verification, menu);
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

    public void changeCoreID(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void createEvent(View view) {
        Intent intent = new Intent(this, VerificationActivity.class);
        EditText editText = (EditText) findViewById(R.id.event_name);
        String eventName = editText.getText().toString();
        if (eventName.equalsIgnoreCase("")) {
            alertMessage(getString(R.string.alert_no_event_name), this);
            return;
        }
        SharedPreferences sp = getSharedPreferences(DATA_FILE, 0);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(KEY_EVENT_NAME, eventName);
        editor.apply();
        startActivity(intent);
    }
}
