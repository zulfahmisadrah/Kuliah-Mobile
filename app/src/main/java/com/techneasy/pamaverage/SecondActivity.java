package com.techneasy.pamaverage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    public static final String EXTRA_DATA1 = "extra_data1";
    public static final String EXTRA_DATA2 = "extra_data2";
    public static final String EXTRA_DATA3 = "extra_data3";
    public static final String EXTRA_CATATAN = "extra_catatan";
    public static final String EXTRA_MEAN = "extra_mean";
    public static final String EXTRA_MAX = "extra_max";
    public static final String EXTRA_MIN = "extra_min";
    public static final String EXTRA_MEDIAN = "extra_median";

    TextView tvAverage, tvMax, tvMin, tvMedian, tvData1, tvData2, tvData3, tvCatatan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        tvAverage = findViewById(R.id.average);
        tvMax = findViewById(R.id.max);
        tvMin = findViewById(R.id.min);
        tvMedian = findViewById(R.id.median);
        tvData1 = findViewById(R.id.sentdata1);
        tvData2 = findViewById(R.id.sentdata2);
        tvData3 = findViewById(R.id.sentdata3);
        tvCatatan = findViewById(R.id.sentcatatan);

        String data1 = getIntent().getStringExtra(EXTRA_DATA1);
        String data2 = getIntent().getStringExtra(EXTRA_DATA2);
        String data3 = getIntent().getStringExtra(EXTRA_DATA3);
        String catatan = getIntent().getStringExtra(EXTRA_CATATAN);
        Double mean = getIntent().getDoubleExtra(EXTRA_MEAN, 0);
        Double max = getIntent().getDoubleExtra(EXTRA_MAX, 0);
        Double min = getIntent().getDoubleExtra(EXTRA_MIN, 0);
        Double median = getIntent().getDoubleExtra(EXTRA_MEDIAN, 0);

        String strMean = MainActivity.roundOffTo2Prec(mean);
        String strMax = String.valueOf(max);
        String strMin = String.valueOf(min);
        String strMedian = String.valueOf(median);

        tvData1.setText(data1);
        tvData2.setText(data2);
        tvData3.setText(data3);
        tvCatatan.setText(catatan);
        tvAverage.setText(strMean);
        tvMax.setText(strMax);
        tvMin.setText(strMin);
        tvMedian.setText(strMedian);
    }
}
