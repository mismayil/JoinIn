package com.freescale.joinin;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;

import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by mismayil on 08/08/15.
 */
public class Utilities {

    public static final String BASE_URL = "http://1-dot-appfsl-1028.appspot.com/app?";
    public static final String KEY_FIRST_NAME = "com.freescale.joinin.KEY_FIRST_NAME";
    public static final String KEY_EVENT_NAME = "com.freescale.joinin.KEY_EVENT_NAME";
    public static final String KEY_CORE_ID = "com.freescale.joinin.KEY_CORE_ID";
    public static final String DATA_FILE = "com.freescale.joinin.DATA_FILE";
    public static final String INFO_FILE = "info.txt";

    public static void alertMessage(String msg, Context ctx) {
        AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
        builder.setMessage(msg);
        builder.setPositiveButton(R.string.button_ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}

class SendDataTask extends AsyncTask<String, Void, String> {

    @Override
    protected String doInBackground(String... urls) {
        try {
            URL urlObj = null;
            HttpURLConnection con = null;
            int response = 200;

            for (int i = 0; i < urls.length; i++) {
                urlObj = new URL(urls[i]);
                con = (HttpURLConnection) urlObj.openConnection();
                con.setRequestMethod("GET");
                response = con.getResponseCode();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
