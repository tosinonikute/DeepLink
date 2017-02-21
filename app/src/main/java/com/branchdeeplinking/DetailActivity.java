package com.branchdeeplinking;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class DetailActivity extends AppCompatActivity {

    private int param1;
    private int param2;
    private String EXTRA_PARAM_1 = "EXTRA_PARAM_1";
    private String EXTRA_PARAM_2 = "EXTRA_PARAM_2";
    private String TAG = "BranchConfigTest";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            param1 = extras.getInt(EXTRA_PARAM_1);
            param2 = extras.getInt(EXTRA_PARAM_2);

            Log.i(TAG, String.valueOf(param1) + " " + String.valueOf(param2));
        }


    }
}
