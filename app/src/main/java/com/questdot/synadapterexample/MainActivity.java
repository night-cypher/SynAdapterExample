package com.questdot.synadapterexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnSyn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSyn= (Button)findViewById(R.id.btnSyn);

        btnSyn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnSyn:
                Log.d("ACT", "sync called");
                getContentResolver().notifyChange(SyncProvider.URI, null, true);

                break;
        }
    }
}
