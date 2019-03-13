package com.techneasy.pamaverage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText edtData1, edtData2, edtData3, edtCatatan;
    TextView tvAverage, tvMax, tvMin, tvMedian;
    Button btnCalculate, btnReset, btnSentData, btnSentObject;

    String inputData1, inputData2, inputData3;
    Double median, max, min, mean;

    private static final String STATE_AVERAGE = "state_average";
    private static final String STATE_MAX = "state_max";
    private static final String STATE_MIN = "state_min";
    private static final String STATE_MEDIAN = "state_median";
    private static final int BANYAK_DATA = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtData1 = findViewById(R.id.edt_data1);
        edtData2 = findViewById(R.id.edt_data2);
        edtData3 = findViewById(R.id.edt_data3);
        tvAverage = findViewById(R.id.average);
        tvMax = findViewById(R.id.max);
        tvMin = findViewById(R.id.min);
        tvMedian = findViewById(R.id.median);
        btnCalculate = findViewById(R.id.hitung);
        btnReset = findViewById(R.id.reset);
        edtCatatan = findViewById(R.id.edt_catatan);
        btnSentData = findViewById(R.id.kirim);
        btnSentObject = findViewById(R.id.send_object);

        btnCalculate.setOnClickListener(this);
        btnReset.setOnClickListener(this);
        btnSentData.setOnClickListener(this);
        btnSentObject.setOnClickListener(this);

        if(savedInstanceState != null){
            String hasilAverage = savedInstanceState.getString(STATE_AVERAGE);
            tvAverage.setText(hasilAverage);
            String hasilMax = savedInstanceState.getString(STATE_MAX);
            tvMax.setText(hasilMax);
            String hasilMin = savedInstanceState.getString(STATE_MIN);
            tvMin.setText(hasilMin);
            String hasilMedian = savedInstanceState.getString(STATE_MEDIAN);
            tvMedian.setText(hasilMedian);
        }
    }

    @Override
    public void onClick(View v) {
        //Sembunyikan Keyboard
        try{
            InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }catch (Exception e){

        }

        if(v.getId() == R.id.hitung){
            inputData1 = edtData1.getText().toString().trim();
            inputData2 = edtData2.getText().toString().trim();
            inputData3 = edtData3.getText().toString().trim();

            boolean inputnyaKosong = false;
            boolean invalidInput = false;

            if(TextUtils.isEmpty(inputData1)){
                inputnyaKosong = true;
                edtData1.setError("Field ini tidak boleh kosong");
            }
            if(TextUtils.isEmpty(inputData2)){
                inputnyaKosong = true;
                edtData2.setError("Field ini tidak boleh kosong");
            }
            if(TextUtils.isEmpty(inputData3)){
                inputnyaKosong = true;
                edtData3.setError("Field ini tidak boleh kosong");
            }

            Double data1 = toDouble(inputData1);
            Double data2 = toDouble(inputData2);
            Double data3 = toDouble(inputData3);

            if(data1 == null){
                invalidInput = true;
                edtData1.setError("Field ini harus berupa angka yang valid");
            }
            if(data2 == null){
                invalidInput = true;
                edtData2.setError("Field ini harus berupa angka yang valid");
            }
            if(data3 == null){
                invalidInput = true;
                edtData3.setError("Field ini harus berupa angka yang valid");
            }

            if(!inputnyaKosong && !invalidInput){
                average(data1, data2, data3);
                max(data1, data2, data3);
                min(data1, data2, data3);
                median(data1, data2, data3);
            }
        }else if(v.getId()==R.id.reset){
            edtData1.setText("");
            edtData2.setText("");
            edtData3.setText("");
            tvAverage.setText("...");
            tvMax.setText("...");
            tvMin.setText("...");
            tvMedian.setText("...");
        }else if(v.getId()==R.id.kirim){
            Intent moveData = new Intent(MainActivity.this, SecondActivity.class);
            String inputCatatan = edtCatatan.getText().toString();
            moveData.putExtra(SecondActivity.EXTRA_DATA1, inputData1);
            moveData.putExtra(SecondActivity.EXTRA_DATA2, inputData2);
            moveData.putExtra(SecondActivity.EXTRA_DATA3, inputData3);
            moveData.putExtra(SecondActivity.EXTRA_CATATAN, inputCatatan);
            moveData.putExtra(SecondActivity.EXTRA_MEAN, mean);
            moveData.putExtra(SecondActivity.EXTRA_MIN, min);
            moveData.putExtra(SecondActivity.EXTRA_MAX, max);
            moveData.putExtra(SecondActivity.EXTRA_MEDIAN, median);
            startActivity(moveData);
        }else if(v.getId()==R.id.send_object){
            Intent moveObject = new Intent(MainActivity.this, SendObjectActivity.class);
            startActivity(moveObject);
        }
    }

    public void average(Double data1, Double data2, Double data3){
        mean = (data1 + data2 + data3) / BANYAK_DATA;
        String round = roundOffTo2Prec(mean);
        String str = String.valueOf(round);
        tvAverage.setText(str);
    }

    public void max(Double data1, Double data2, Double data3){
        max = 0.0;
        if(data1>=data2 && data1>=data3)
            max = data1;
        else if(data2>=data1 && data2>=data3)
            max = data2;
        else if(data3>=data1 && data3>=data2)
            max = data3;
        String str = String.valueOf(max);
        tvMax.setText(str);
    }

    public void min(Double data1, Double data2, Double data3){
        min = 0.0;
        if(data1<=data2 && data1<=data3)
            min = data1;
        else if(data2<=data1 && data2<=data3)
            min = data2;
        else if(data3<=data1 && data3<=data2)
            min = data3;
        String str = String.valueOf(min);
        tvMin.setText(str);
    }

    public void median(Double data1, Double data2, Double data3){
        Double[] data = new Double[3];
        data[0] = data1;
        data[1] = data2;
        data[2] = data3;
        double temp = 0;
        for(int i=0; i<BANYAK_DATA; i++){
            for(int j=1; j<(BANYAK_DATA); j++){
                if(data[j-1] > data[j]){
                    temp = data[j-1];
                    data[j-1] = data[j];
                    data[j] = temp;
                }
            }
        }
        int indexMedian = (BANYAK_DATA+1)/2;
        median = data[indexMedian-1];
        String str = String.valueOf(median);
        tvMedian.setText(str);
    }

    Double toDouble(String str){
        try{
            return Double.valueOf(str);
        } catch (NumberFormatException e){
            return null;
        }
    }

    static String roundOffTo2Prec(Double d) {
        return String.format(Locale.CANADA, "%.2f", d);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putString(STATE_AVERAGE, tvAverage.getText().toString());
        outState.putString(STATE_MAX, tvMax.getText().toString());
        outState.putString(STATE_MIN, tvMin.getText().toString());
        outState.putString(STATE_MEDIAN, tvMedian.getText().toString());
    }
}
