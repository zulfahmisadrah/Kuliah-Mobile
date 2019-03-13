package com.techneasy.pamaverage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MoveWithObject extends AppCompatActivity {
    public static final String EXTRA_STUDENT = "extra_student";
    TextView tvNama, tvNim, tvProdi, tvEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        tvNama = findViewById(R.id.tv_nama);
        tvNim = findViewById(R.id.tv_nim);
        tvProdi = findViewById(R.id.tv_prodi);
        tvEmail = findViewById(R.id.tv_email);

        Student student = getIntent().getParcelableExtra(EXTRA_STUDENT);

        tvNama.setText(student.getNama());
        tvNim.setText(student.getNim());
        tvProdi.setText(student.getProdi());
        tvEmail.setText(student.getEmail());
    }
}
