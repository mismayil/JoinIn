package com.freescale.joinin;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import static com.freescale.joinin.Utilities.*;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public void go(View view) {
        Intent intent = new Intent(this, CreateEventActivity.class);
        EditText editText = (EditText) findViewById(R.id.core_id);
        String coreID = editText.getText().toString();
        if (coreID.equalsIgnoreCase("")) {
            alertMessage(getString(R.string.alert_no_core_id), this);
            return;
        }
        SharedPreferences sp = getSharedPreferences(DATA_FILE, 0);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(KEY_CORE_ID, coreID);
        editor.apply();
        startActivity(intent);
    }
}
