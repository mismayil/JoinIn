package com.freescale.joinin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import static com.freescale.joinin.Utilities.*;

public class FinishJoinInActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish_join_in);
        String firstName = getSharedPreferences(DATA_FILE, 0).getString(KEY_FIRST_NAME, null);
        TextView textView = new TextView(this);
        textView.setText(getString(R.string.text_thanks) + firstName);
        textView.setTextColor(getResources().getColor(R.color.black));
        textView.setTextAppearance(this, android.R.style.TextAppearance_Large);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(RelativeLayout.ABOVE, R.id.text_success);
        layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL, 1);
        textView.setLayoutParams(layoutParams);
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.activity_finish_join_in);
        relativeLayout.addView(textView);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_finish_join_in, menu);
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

    public void done(View view) {
        Intent intent = new Intent(this, EventActivity.class);
        startActivity(intent);
    }
}
