package com.branchdeeplinking;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.json.JSONObject;

import io.branch.referral.Branch;
import io.branch.referral.BranchError;

public class MainActivity extends AppCompatActivity {

    private int param1 = 0;
    private int param2 = 0;
    private String EXTRA_PARAM_1 = "EXTRA_PARAM_1";
    private String EXTRA_PARAM_2 = "EXTRA_PARAM_2";
    private String TAG = "BranchConfigTest";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Branch.getAutoInstance(this);


    }

    @Override
    public void onStart() {
        super.onStart();
        Branch branch = Branch.getInstance();

        branch.initSession(new Branch.BranchReferralInitListener(){
            @Override
            public void onInitFinished(JSONObject referringParams, BranchError error) {
                if (error == null) {
                    // params are the deep linked params associated with the link that the user clicked -> was re-directed to this app
                    // params will be empty if no data found
                    // ... insert custom logic here ...
                    Log.i(TAG, "deep link data: " + referringParams.toString());

                    try {
                        param1 =  referringParams.getInt(getResources().getString(R.string.param1));
                        param2 =  referringParams.getInt(getResources().getString(R.string.param2));

                    } catch (org.json.JSONException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                    if(param1 != 0 && param2 != 0) {
                        Bundle bundle = new Bundle();
                        bundle.putLong(EXTRA_PARAM_1, param1);
                        bundle.putLong(EXTRA_PARAM_2, param2);

                        Intent intent = new Intent(getBaseContext(), DetailActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        finish();
                    }


                } else {
                    Log.i(TAG, error.getMessage());
                }
            }
        }, this.getIntent().getData(), this);
    }

    @Override
    public void onNewIntent(Intent intent) {
        this.setIntent(intent);
    }

}
