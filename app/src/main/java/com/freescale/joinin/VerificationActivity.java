package com.freescale.joinin;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.content.Intent;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import static com.freescale.joinin.Utilities.*;

public class VerificationActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);
        SharedPreferences sp = getSharedPreferences(DATA_FILE, 0);
        String eventName = sp.getString(KEY_EVENT_NAME, null);
        TextView textView = new TextView(this);
        textView.setId(R.id.text_view_event_name);
        textView.setText(eventName);
        textView.setTextColor(getResources().getColor(R.color.black));
        textView.setTextAppearance(this, android.R.style.TextAppearance_Large);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(RelativeLayout.ABOVE, R.id.text_verify);
        layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL, 1);
        layoutParams.addRule(RelativeLayout.CENTER_VERTICAL, 1);
        layoutParams.setMargins(0, 0, 0, 20);
        textView.setLayoutParams(layoutParams);
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.activity_verification);
        relativeLayout.addView(textView);

        textView = new TextView(this);
        String coreID = sp.getString(KEY_CORE_ID, null);
        textView.setText(coreID);
        textView.setTextColor(getResources().getColor(R.color.black));
        textView.setTextAppearance(this, android.R.style.TextAppearance_Large);
        layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(RelativeLayout.ABOVE, R.id.text_view_event_name);
        layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL, 1);
        layoutParams.addRule(RelativeLayout.CENTER_VERTICAL, 1);
        layoutParams.setMargins(0, 0, 0, 20);
        textView.setLayoutParams(layoutParams);
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

    public void changeEventName(View view) {
        Intent intent = new Intent(this, CreateEventActivity.class);
        startActivity(intent);
    }

    public void goToEvent(View view) {
        Intent intent = new Intent(this, EventActivity.class);
        startActivity(intent);
    }
}
