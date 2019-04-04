package com.techneasy.pamaverage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FragmentActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnFrag1, btnFrag2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        btnFrag1 = findViewById(R.id.frag_1);
        btnFrag2 = findViewById(R.id.frag_2);

        btnFrag1.setOnClickListener(this);
        btnFrag2.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.frag_1) {
            Fragment1 fragment1 = new Fragment1();
            getSupportFragmentManager().beginTransaction().replace(R.id.main_container, fragment1).commit();
        } else if (v.getId() == R.id.frag_2) {
            Fragment2 fragment2 = new Fragment2();
            getSupportFragmentManager().beginTransaction().replace(R.id.main_container, fragment2).commit();
        }
    }
}
