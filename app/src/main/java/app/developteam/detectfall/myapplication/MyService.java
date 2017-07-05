package app.developteam.detectfall.myapplication;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.AsyncTask;

import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;

import app.developteam.detectfall.myapplication.activity.MainActivity;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by barbie on 3/28/2017.
 */

public class MyService extends Service implements SensorEventListener {
    public static final String MYACTION = "ACTION";
    private DecimalFormat decimalFormat = new DecimalFormat("#.##");
    private SimpleDateFormat datetimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static int ID_NOTI = 100;
    private int delay = 200;
    Handler handler;
    Context context = MyService.this;

    private SensorEventListener sensorEvent = this;
    private SensorManager mSensorManager;
    private Sensor mAccelerometerSensor;

    private float[] aAxis = new float[16];
    private float[] aAxis_Old = new float[]{0, 0, 0};
    private float[] max_data = new float[]{0, 0, 0};

    private ArrayList<Axis> axisList;
    private ArrayList<Double> postData;

    private String status = Fuzzy2.NONE;
    private String status2 = Fuzzy2.NONE;
    String round = "-1";

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mSensorManager.registerListener(sensorEvent, mAccelerometerSensor, SensorManager.SENSOR_DELAY_NORMAL);
//        if (intent.hasExtra("round")) {
//            round = intent.getStringExtra("round");
//            Log.i("SERVICES", round);
//        }

        handler.removeCallbacks(runnable);
        handler.postDelayed(runnable, delay);
        return START_NOT_STICKY;
    }


    @Override
    public void onCreate() {
        super.onCreate();

        axisList = new ArrayList<>();
        postData = new ArrayList<>();
        handler = new Handler();


        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometerSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        startForeground(ID_NOTI, myNotification());

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mSensorManager.unregisterListener(sensorEvent, mAccelerometerSensor);
        handler.removeCallbacks(runnable);

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        synchronized (this) {
            switch (event.sensor.getType()) {
                case Sensor.TYPE_ACCELEROMETER:
                    aAxis = event.values;
                    break;
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            processSensorRaw(aAxis);
            Log.i("time", datetimeFormat.format(System.currentTimeMillis()));

            updateNotification();
            handler.postDelayed(this, delay);
        }
    };


    public Notification myNotification() {
//        Notification notification = new Notification.Builder(context)
//                .setSmallIcon(R.mipmap.ic_launcher)
//                .setContentText("x:" + max_data[0] + " y:" + max_data[1] + " z:" + max_data[2]+" status:"+status)
//                .setContentTitle("Fall Detection")
//                .build();
//        return notification;

//        Notification notification = new Notification.Builder(context)
//                .setSmallIcon(R.mipmap.ic_launcher)
//                .setContentText("x:" + aAxis[0] + " y:" + aAxis[1] + " z:" + aAxis[2] + " status:" + status)
//                .setContentTitle("Fall Detection")
//                .build();
//        return notification;

        Intent notificationIntent = new Intent(context, MainActivity.class);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent intent = PendingIntent.getActivity(context, 0, notificationIntent, 0);


        Notification notification = new Notification.Builder(context)
                .setSmallIcon(R.drawable.siren48)
                .setContentText("สถานะ ; กำลังตรวจจับการหกล้ม")
                .setContentTitle("Fall Detection")
                .setContentIntent(intent)
                .build();
        return notification;
    }

    public void updateNotification() {
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(ID_NOTI, myNotification());
    }

    public void processSensorRaw(float[] values) {
        //ระบบ 2
        float[] data;
        data = calculateSlope(values);
        max_data = calMovingMax(data);

        aAxis_Old[0] = values[0];
        aAxis_Old[1] = values[1];
        aAxis_Old[2] = values[2];

        if (max_data != null) {
            Log.i("DETECTS", " X:" + max_data[0] + " Y:" + max_data[1] + " Z:" + max_data[2]);
        }
        //ระบบ 1
//        if (!status.equals(Fuzzy.FALL)) {
//            fallDetection(values);
//        } else if (status.equals(Fuzzy.FALL)) {
//           isFall();
//        }
        //TODO inserttoserver
//        new AsyncTasks().execute();

    }

    public String fallDetection(float[] axis) {
        float ax = axis[0];
        float ay = axis[1];
        float az = axis[2];


        //ระบบ 1
//        Fuzzy fuzzy = new Fuzzy();
//        fuzzy.calculateXMemberShipFx(ax);
//        fuzzy.calculateYMemberShipFx(ay);
//        fuzzy.calculateZMemberShipFx(az);
//        String activity = fuzzy.rulesInference();
//
//        if (activity.equals(Fuzzy.FALL)) {
//            isFall();
//            Log.i("DETECTS", Fuzzy.FALL);
//        } else {
//            status = Fuzzy.NONE;
//            Log.i("DETECTS", Fuzzy.NONE);
//        }
//        fuzzy.clearData();

        //ระบบ 2

        Fuzzy2 fuzzy = new Fuzzy2();
        fuzzy.calculateXMemberShipFx(ax);
        fuzzy.calculateYMemberShipFx(ay);
        fuzzy.calculateZMemberShipFx(az);
        String activity = fuzzy.rulesInference();
        fuzzy.clearData();
        if (activity.equals(Fuzzy2.FALL)) {
            Log.i("DETECTS", Fuzzy2.FALL);
            return status = Fuzzy2.FALL;
        } else {
            Log.i("DETECTS", Fuzzy2.NONE);
            return status = Fuzzy2.NONE;

        }

    }

    public String fallDetectionWhenFall(float[] axis) {
        float ax = axis[0];
        float ay = axis[1];
        float az = axis[2];

        //ระบบ 2

        Fuzzy2 fuzzy = new Fuzzy2();
        fuzzy.calculateXMemberShipFx(ax);
        fuzzy.calculateYMemberShipFx(ay);
        fuzzy.calculateZMemberShipFx(az);
        String activity = fuzzy.rulesInference();
        fuzzy.clearData();
        if (activity.equals(Fuzzy2.FALL)) {
            return status2 = Fuzzy2.FALL;
        } else {
            return status2 = Fuzzy2.NONE;

        }

    }

    public float[] calMovingMax(float[] aAxis) {
        Axis axis = new Axis(aAxis[0], aAxis[1], aAxis[2]);
        axisList.add(axis);

        if (axisList.size() == 3) {
            float[] x = new float[]{0, 0, 0};
            float[] y = new float[]{0, 0, 0};
            float[] z = new float[]{0, 0, 0};
            float[] max = new float[]{0, 0, 0};

            for (int i = 0; i < axisList.size(); i++) {
                x[i] = axisList.get(i).getxAxis();
                y[i] = axisList.get(i).getyAxis();
                z[i] = axisList.get(i).getzAxis();
            }
            Arrays.sort(x);
            Arrays.sort(y);
            Arrays.sort(z);

            max[0] = x[x.length - 1];
            max[1] = y[y.length - 1];
            max[2] = z[z.length - 1];

            if (!status.equals(Fuzzy2.FALL)) {
                fallDetection(max);
            } else if (status.equals(Fuzzy2.FALL)) {
                postData.add((double) aAxis[1]);

                String statusfall = fallDetectionWhenFall(max);

                Intent intent = new Intent();
                intent.setAction(MYACTION);
                intent.putExtra("DETECT",1);
                sendBroadcast(intent);

                if (statusfall.equals(Fuzzy2.FALL)) {
                    postData.clear();
                }

                if (postData.size() >= 9) {
                    Calculator cal = new Calculator();
                    double variance = cal.calVariance(postData);
                    Log.i("DETECTS", String.valueOf(variance));
                    if (variance < 5) {
                        postData.clear();
                        isFall();
                    } else {
                        Intent intent1 = new Intent();
                        intent1.setAction(MYACTION);
                        intent1.putExtra("DETECT",2);
                        sendBroadcast(intent1);

                        postData.clear();
                        status = Fuzzy2.NONE;
                        status2 = Fuzzy2.NONE;
                    }
                }
            }
            axisList.clear();
            return max;
        } else return null;

    }

    private void isFall() {

        status = Fuzzy2.NONE;
        status2 = Fuzzy2.NONE;
        stopSelf();

//        mSensorManager.unregisterListener(sensorEvent, mAccelerometerSensor);
//        handler.removeCallbacks(runnable);

        Intent intent = new Intent();
        intent.setAction(MYACTION);
        intent.putExtra("FALL", 1);
        sendBroadcast(intent);

    }

    public float[] calculateSlope(float[] value) {
        float[] slope = new float[]{0, 0, 0};

        slope[0] = Math.abs(value[0] - aAxis_Old[0]);
        slope[1] = Math.abs(value[1] - aAxis_Old[1]);
        slope[2] = Math.abs(value[2] - aAxis_Old[2]);

        Intent intent = new Intent();
        intent.setAction(MYACTION);
        intent.putExtra("AXIS", value);
        sendBroadcast(intent);

        return slope;
    }

    public double calculateAcceleration(float[] values) {
        double acceleration = 0;
        acceleration = Math.sqrt(Math.pow(values[0], 2) + Math.pow(values[1], 2) + Math.pow(values[2], 2));
        return acceleration;
    }

    public String insertDataToServer(float[] axis) {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();


        SharePreferenceHelper sharepre = new SharePreferenceHelper(context);
        okhttp3.OkHttpClient client = new okhttp3.OkHttpClient();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://" + sharepre.getIPSharePreference() + "/datachart/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();


        double stracceleration = calculateAcceleration(axis);

        APIService service = retrofit.create(APIService.class);

        DBData dbData = new DBData();
        dbData.setRound("1");
        dbData.setAx(decimalFormat.format(axis[0]));
        dbData.setAy(decimalFormat.format(axis[1]));
        dbData.setAz(decimalFormat.format(axis[2]));
        dbData.setAcceleration(String.valueOf(stracceleration));
        dbData.setTime(datetimeFormat.format(System.currentTimeMillis()));


        Call<ResponseModel> call = service.insertDataBase(dbData.getRound()
                , dbData.getAx()
                , dbData.getAy()
                , dbData.getAz()
                , dbData.getAccleration()
                , dbData.getTime());

        try {
            Response response = call.execute();
            if (response.raw().networkResponse().code() == 404) {
                return "";
            } else {
                //code 200
                if (response.body() != null) {
                    return response.body().toString();
                } else {
                    return "";
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }


    }


    class AsyncTasks extends AsyncTask<Void, Integer, String> {
        @Override
        protected String doInBackground(Void... voids) {
            return insertDataToServer(aAxis);
        }

    }
}