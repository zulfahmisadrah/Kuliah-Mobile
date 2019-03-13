package com.techneasy.pamaverage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SendObjectActivity extends AppCompatActivity implements View.OnClickListener{

    EditText edtNama, edtNim, edtProdi, edtEmail;
    Button sendData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_object);

        edtNama = findViewById(R.id.edt_nama);
        edtNim = findViewById(R.id.edt_nim);
        edtProdi = findViewById(R.id.edt_prodi);
        edtEmail = findViewById(R.id.edt_email);
        sendData = findViewById(R.id.send_data);

        sendData.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.send_data){
            String inputNama = edtNama.getText().toString();
            String inputNim = edtNim.getText().toString();
            String inputProdi = edtProdi.getText().toString();
            String inputEmail = edtEmail.getText().toString();

            Student student = new Student();
            student.setNama(inputNama);
            student.setNim(inputNim);
            student.setProdi(inputProdi);
            student.setEmail(inputEmail);
            Intent moveObject = new Intent(SendObjectActivity.this, MoveWithObject.class);
            moveObject.putExtra(MoveWithObject.EXTRA_STUDENT, student);
            startActivity(moveObject);
        }
    }
}
