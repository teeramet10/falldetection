package app.developteam.detectfall.myapplication.activity;

import android.content.Context;
import android.os.AsyncTask;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import app.developteam.detectfall.myapplication.APIService;
import app.developteam.detectfall.myapplication.Alarm;
import app.developteam.detectfall.myapplication.R;
import app.developteam.detectfall.myapplication.ResponseModel;
import app.developteam.detectfall.myapplication.SMSData;
import app.developteam.detectfall.myapplication.SharePreferenceHelper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FallActivity extends AppCompatActivity {
    FloatingActionButton fabBack;
    TextView tvCaption;
    Context context = this;
    TextView tvCountDown;
    Alarm alarm;
    ProgressBar pdTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fall);

        fabBack = (FloatingActionButton) findViewById(R.id.btnback);
        tvCountDown = (TextView) findViewById(R.id.countdown);
        pdTimer= (ProgressBar) findViewById(R.id.Progress);
        tvCaption = (TextView) findViewById(R.id.txtfall);

        new AsyncTasks("Help me please!").execute();

        alarm = new Alarm(context);
        alarm.openAlarm();
        countDownTimer.start();

        tvCaption.setText(Html.fromHtml("<center>ตรวจจับได้ว่ามีการหกล้มหากท่านยังสบายดีกรุณากดปุ่ม</center> 'OK' </br>เพื่อยกเลิกการส่ง Sms"));

        fabBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alarm.closeAlarm();
                new AsyncTasks("I'm Ok.").execute();
                countDownTimer.cancel();
                finish();

            }
        });


    }

    CountDownTimer countDownTimer = new CountDownTimer(20000, 1000) {

        @Override
        public void onTick(long millisUntilFinished) {
            pdTimer.setProgress((int) (20));
            tvCountDown.setText(String.valueOf(millisUntilFinished/1000));
        }

        @Override
        public void onFinish() {
            new AsyncTasks("Help me now!!").execute();
            Toast.makeText(context,"ส่ง SMS แล้ว",Toast.LENGTH_SHORT).show();
            tvCountDown.setText("0");
            pdTimer.setProgress(0);
        }
    };

    private void sendSms(String message) {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        okhttp3.OkHttpClient client = new okhttp3.OkHttpClient();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.thaibulksms.com")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();


        APIService service = retrofit.create(APIService.class);

        SharePreferenceHelper sp = new SharePreferenceHelper(context);
        SMSData smsData = new SMSData();
        smsData.setMessage(message);
        smsData.setUsername(sp.getUsernameSharePreference());
        smsData.setPassword(sp.getPasswordSharePreference());
//        smsData.setUsername("thaibulksms");
//        smsData.setPassword("thisispassword");
        smsData.setNumber(sp.getTelSharePreference());


        Call<ResponseModel> call = service.sendSms(smsData.getUsername()
                , smsData.getPassword()
                , smsData.getNumber()
                , smsData.getMessage());
        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                Log.i("DETECTSFALL",response.message());
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Log.i("DETECTSFALL",t.getMessage());
            }
        });



    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    class AsyncTasks extends AsyncTask<Void, Integer, Void> {
        String message ;

        public AsyncTasks(String message) {
            this.message = message;
        }

        @Override
        protected Void doInBackground(Void... params) {
            sendSms(message);
            return null;
        }
    }
}
