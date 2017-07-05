package app.developteam.detectfall.myapplication.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;

import app.developteam.detectfall.myapplication.MyService;
import app.developteam.detectfall.myapplication.R;
import app.developteam.detectfall.myapplication.SharePreferenceHelper;

public class MainActivity extends AppCompatActivity {
    public static final String STOP = "Stop";
    public static final String START = "Start";
    private Context context = MainActivity.this;

    private FloatingActionButton btnPower;
    private Spinner spnRound;
    private ImageView imgSetting;
    private ImageView imgTest;
    private TextView tvCaption;
    private TextView tvStatus;
    MyReceiver myReceiver;

    SharePreferenceHelper sp;
    Intent intent1;

    private SensorManager mSensorManager;
    private Sensor mAccelerometerSensor;

    Intent intent;
//    LineChart realtimeChart;
//    private ArrayAdapter spnRoundAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindWidget();

//        setSpinner();
        sp = new SharePreferenceHelper(context);

        if (!sp.getUsecontactSharePreference()) {
            Intent intent = new Intent(context, EmergencyContactActivity.class);
            startActivity(intent);
            finish();
        }

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometerSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        tvCaption.setText(Html.fromHtml("<U><B>ขั้นตอนการใช้งาน</B></U><br /> " +
                "1.กดปุ่มสีแดงทางด้านล่างเพื่อเริ่มการตรวจจับการหกล้ม<br /> " +
                "2.กรุณานำโทรศัพท์ของท่านใส่กระเป๋าในแนวตั้งแล้วคาดไว้ที่เอวเพื่อเริ่มใช้งาน<br/>" +
                "3.ถ้าสถานะแสดงว่าไม่สามารถใช้งานได้ให้รองตรวจสอบว่าอุปกรณ์ของท่านมี Accelerometer sensor หรือไม่"));

        btnPower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (sp.getServiceSharePreference()) {
                    stopSensorEvent();
                } else {
                    startSensorEvent();
                }
            }
        });

        imgSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EmergencyContactActivity.class);
                startActivity(intent);
            }
        });

        imgTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, TestActivity.class);
                startActivity(intent);
            }
        });

        myReceiver = new MyReceiver();
//        setChart();


    }

    private void bindWidget() {
        btnPower = (FloatingActionButton) findViewById(R.id.btnevent);
        spnRound = (Spinner) findViewById(R.id.spnround);
        imgSetting = (ImageView) findViewById(R.id.setting);
        imgTest = (ImageView) findViewById(R.id.test);
        tvCaption = (TextView) findViewById(R.id.caption);
        tvStatus = (TextView) findViewById(R.id.status);
//        realtimeChart = (LineChart) findViewById(R.id.realtime);


    }

//    private void setChart() {
//
//        realtimeChart.getDescription().setEnabled(true);
//        realtimeChart.setTouchEnabled(true);
//        realtimeChart.setDragEnabled(true);
//        realtimeChart.setScaleEnabled(true);
//        realtimeChart.setDrawGridBackground(false);
//        realtimeChart.setPinchZoom(false);
//        realtimeChart.setBackgroundColor(Color.WHITE);
//
//        float[] aAxis =new float[]{0,0,0};
//        setDataRealtime(aAxis);
//
//        Legend l = realtimeChart.getLegend();
//        l.setForm(Legend.LegendForm.LINE);
//        l.setTextColor(Color.BLACK);
//
//        XAxis xl = realtimeChart.getXAxis();
//        xl.setTextColor(Color.BLACK);
//        xl.setDrawGridLines(false);
//        xl.setAvoidFirstLastClipping(true);
//        xl.setEnabled(true);
//
//        YAxis leftAxis = realtimeChart.getAxisLeft();
//        leftAxis.setTextColor(Color.BLACK);
//        leftAxis.setAxisMaximum(20f);
//        leftAxis.setAxisMinimum(-20f);
//        leftAxis.setDrawGridLines(true);
//
//        YAxis rightAxis = realtimeChart.getAxisRight();
//        rightAxis.setEnabled(false);
//
//    }

//    public void setSpinner() {
//
//        String[] listround = new String[100];
//        int round = 1;
//        for (int i = 0; i < 100; i++) {
//            listround[i] = String.valueOf(round);
//            round++;
//        }
//        spnRoundAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, listround);
//
//        spnRound.setAdapter(spnRoundAdapter);
//
//    }

    public void startSensorEvent() {
        if (mAccelerometerSensor != null) {
            btnPower.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.green)));
            tvStatus.setTextColor(getResources().getColor(R.color.green));
            tvStatus.setText("สถานะ : กำลังทำงาน");
            Toast.makeText(context, "โปรแกรมเริ่มทำงานแล้ว", Toast.LENGTH_SHORT).show();
            sp.putServiceSharePreference(true);

            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(MyService.MYACTION);
            if (myReceiver != null) {
                if(!myReceiver.isRegister()) {
                    context.registerReceiver(myReceiver, intentFilter);
                    myReceiver.setRegister(true);
                }
            }

            intent = new Intent(context, MyService.class);
//        String round = spnRound.getSelectedItem().toString();
//        intent.putExtra("round", round);
            startService(intent);
        } else {
            tvStatus.setTextColor(getResources().getColor(R.color.red));
            tvStatus.setText("สถานะ : ไม่สามารถใช้งานได้");
        }
    }

    public void stopSensorEvent() {

        sp.putServiceSharePreference(false);
        tvStatus.setTextColor(getResources().getColor(R.color.red));
        tvStatus.setText("สถานะ : พร้อมใช้งาน");
        btnPower.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.red)));
        if (myReceiver != null) {
            if(myReceiver.isRegister()) {
                context.unregisterReceiver(myReceiver);
                myReceiver.setRegister(false);
            }
        }

        intent = new Intent(context, MyService.class);
        stopService(intent);

    }


    @Override
    protected void onResume() {
        super.onResume();
        if (sp.getServiceSharePreference()) {
            startSensorEvent();
        } else {
            stopSensorEvent();
        }
    }

//    private LineDataSet createDataSet(String legend,int color) {
//        ArrayList<Entry> entries=new ArrayList<>();
//        entries.add(new Entry(0,0));
//        LineDataSet dataset = new LineDataSet(entries,legend);
//        dataset.setDrawCircleHole(false);
//        dataset.setDrawCircles(false);
//        dataset.setDrawValues(false);
//        dataset.setAxisDependency(YAxis.AxisDependency.LEFT);
//        dataset.setColor(color);
//        dataset.setLineWidth(2f);
//        return dataset;
//    }
//
//    private void setDataRealtime(float[] axis) {
//        LineData data = realtimeChart.getData();
//        LineDataSet x, y, z;
//        if (data != null) {
//            x = (LineDataSet) data.getDataSetByIndex(0);
//            y = (LineDataSet) data.getDataSetByIndex(1);
//            z = (LineDataSet) data.getDataSetByIndex(2);
//
//            x.addEntry(new Entry(x.getEntryCount(),axis[0]));
//            y.addEntry(new Entry(y.getEntryCount(),axis[1]));
//            z.addEntry(new Entry(z.getEntryCount(),axis[2]));
//
//            data.notifyDataChanged();
//
//            // let the chart know it's data has changed
//            realtimeChart.notifyDataSetChanged();
//
//            // limit the number of visible entries
//            realtimeChart.setVisibleXRangeMaximum(40);
//            // mChart.setVisibleYRange(30, AxisDependency.LEFT);
//
//            // move to the latest entry
//            realtimeChart.moveViewToX(data.getEntryCount());
//        } else {
//
//
//            x = createDataSet("x",getResources().getColor(R.color.blue));
//            y = createDataSet("y",getResources().getColor(R.color.red));
//            z = createDataSet("z",getResources().getColor(R.color.violet));
//
//            ArrayList<ILineDataSet> dataSets =new ArrayList<>();
//            dataSets.add(x);
//            dataSets.add(y);
//            dataSets.add(z);
//            LineData data1=new LineData(dataSets);
//            realtimeChart.setData(data1);
//        }
//    }


    private class MyReceiver extends BroadcastReceiver {
        boolean register =false;
        int value = -1;
        int detect = -1;

        @Override
        public void onReceive(Context context, Intent intent) {
            value = intent.getIntExtra("FALL", -1);
            detect = intent.getIntExtra("DETECT", -1);

            if (detect == 1) {
                tvStatus.setTextColor(getResources().getColor(R.color.red));
                tvStatus.setText("สถานะ : ตรวจจับได้ว่าหกล้ม");
//                setDataRealtime(axis);
            } else if (detect == 2) {
                tvStatus.setTextColor(getResources().getColor(R.color.green));
                tvStatus.setText("สถานะ : กำลังทำงาน");
            }
            if (value == 1) {
                intent1 = new Intent(context, FallActivity.class);
                startActivity(intent1);
                value = -1;
            }
        }

        public boolean isRegister() {
            return register;
        }

        public void setRegister(boolean register) {
            this.register = register;
        }
    }
}