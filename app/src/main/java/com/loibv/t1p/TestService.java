package com.loibv.t1p;

import android.app.IntentService;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by vanloibui on 4/12/16.
 */
public class TestService extends IntentService {

    public TestService() {
        super("test-serice");
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        Toast.makeText(this,"aaa", Toast.LENGTH_SHORT).show();
    }
}
