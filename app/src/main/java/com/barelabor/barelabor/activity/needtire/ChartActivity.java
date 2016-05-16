package com.barelabor.barelabor.activity.needtire;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import com.barelabor.barelabor.R;
import com.barelabor.barelabor.activity.MenuActivity;
import com.barelabor.barelabor.activity.SplashActivity;
import com.barelabor.barelabor.data.model.PriceModel;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;

import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;

public class ChartActivity extends AppCompatActivity implements View.OnClickListener {

    //private LineChartView mChart;
    private LineChart mChart;
    private String lowPrice, highPrice, avgPrice;
    private Boolean fromNotification = false;

    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        lowPrice = getIntent().getStringExtra("low_price");
        highPrice = getIntent().getStringExtra("high_price");
        avgPrice = getIntent().getStringExtra("avg_price");
        fromNotification = getIntent().getBooleanExtra("notification", false);

        initView();

        setData();
    }

    private void initView(){

        btnBack = (Button) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(this);

        mChart = (LineChart) findViewById(R.id.chart1);
        mChart.setDrawGridBackground(false);
        // no description text
        mChart.setDescription("");
        mChart.setNoDataTextDescription("You need to provide data for the chart.");

        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.setAxisMaxValue(1200f);
        leftAxis.setAxisMinValue(0f);
        leftAxis.setDrawLabels(false);

        mChart.getAxisRight().setEnabled(false);
        mChart.getAxisLeft().setEnabled(true);

        XAxis xAxis = mChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setSpaceBetweenLabels(0);
        xAxis.setLabelsToSkip(0);
        xAxis.setTextSize(15);
        xAxis.setTextColor(Color.WHITE);

        mChart.setExtraLeftOffset(25);
        mChart.setExtraRightOffset(25);

        mChart.animateX(2500, Easing.EasingOption.EaseInOutQuart);
    }



    private void setData(){





        ArrayList<Entry> entries = new ArrayList<>();

        ArrayList<String> labels = new ArrayList<String>();

        for(int i = 0; i < 20; i++){
            if (i <= 1 || i >= 19)
                entries.add(new Entry(0.0f, 0));
            else if(i < 10){
                entries.add(new Entry((float) Math.pow(2, i), i));
            }else{
                entries.add(new Entry((float) Math.pow(2, (20-i)), i));
            }

            if(i == 0)
                labels.add("$" + lowPrice);
            else if(i == 10)
                labels.add("$" + avgPrice);
            else if(i >= 19)
                labels.add("$" + highPrice);
            else
                labels.add("");
        }

        LineDataSet dataset = new LineDataSet(entries, "");
        dataset.setDrawCubic(true);
        dataset.setDrawValues(false);
        dataset.setDrawCircles(false);

        LineData data = new LineData(labels, dataset);
        mChart.setData(data);

        mChart.getLegend().setEnabled(false);
    }

    @Override
    public void onClick(View v) {

        if(v == btnBack){

            finish();
        }
    }
}
