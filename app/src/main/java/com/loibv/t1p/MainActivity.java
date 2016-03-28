package com.loibv.t1p;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/SVN-Unthrift.otf");
        TextView tvAppName = (TextView) findViewById(R.id.tvAppName);
        tvAppName.setTypeface(custom_font);
    }
}
