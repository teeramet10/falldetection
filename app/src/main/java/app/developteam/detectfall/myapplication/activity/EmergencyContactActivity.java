package app.developteam.detectfall.myapplication.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import app.developteam.detectfall.myapplication.APIService;
import app.developteam.detectfall.myapplication.DBData;
import app.developteam.detectfall.myapplication.GetSms;
import app.developteam.detectfall.myapplication.R;
import app.developteam.detectfall.myapplication.ResponseModel;
import app.developteam.detectfall.myapplication.SMSData;
import app.developteam.detectfall.myapplication.SharePreferenceHelper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EmergencyContactActivity extends AppCompatActivity {
    Context context = this;
    FloatingActionButton fab;
    EditText edtUser;
    EditText edtPass;
    EditText edtTel;
    TextView tvCaption;
    TextView tvTitle;
    ImageView imgBack;

    String user = "";
    String pass = "";
    String number = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_contact);

        edtUser = (EditText) findViewById(R.id.edtuser);
        edtPass = (EditText) findViewById(R.id.edtpass);
        edtTel = (EditText) findViewById(R.id.edtcontact);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        tvCaption = (TextView) findViewById(R.id.caption);
        imgBack = (ImageView) findViewById(R.id.back);
        tvTitle = (TextView) findViewById(R.id.tvtitle);

        Intent intent = getIntent();
        int value = intent.getIntExtra(SettingActivity.TAG, -1);

        if (value == -1) {
            View btnback = findViewById(R.id.back);
            ((ViewGroup) btnback.getParent()).removeView(btnback);
            tvTitle.setMaxWidth(Integer.MAX_VALUE);
            tvTitle.setPadding(16, 0, 0, 0);
        }

        SharePreferenceHelper sp = new SharePreferenceHelper(context);
        String user = sp.getUsernameSharePreference();
        String pass = sp.getPasswordSharePreference();
        String tel = sp.getTelSharePreference();

        edtUser.setText(user);
        edtPass.setText(pass);
        edtTel.setText(tel);
        tvCaption.setText(Html.fromHtml("Username และ Password สามารถสมัครและรับได้ที่ <a href='https://member.thaibulksms.com/user/register'>คลิกที่นี่</a>"));

        tvCaption.setMovementMethod(LinkMovementMethod.getInstance());

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = edtUser.getText().toString();
                String pass = edtPass.getText().toString();
                String number = edtTel.getText().toString();

                if (!pass.equals("") && !user.equals("") && !number.equals("")) {
                    if (number.length() == 10) {
                        new AsyncTasks().execute();
                    } else {
                        Toast.makeText(context, "กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(context, "กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    class AsyncTasks extends AsyncTask<Void, String, String> {

        private ProgressDialog pd;
        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            user = edtUser.getText().toString();
            pass = edtPass.getText().toString();
            number = edtTel.getText().toString();
            pd = new ProgressDialog(context);
            pd.setMessage("Waiting...");
            pd.setCancelable(false);
            pd.setIndeterminate(true);
            pd.show();

        }

        @Override
        protected String doInBackground(Void... params) {
            SharePreferenceHelper sp = new SharePreferenceHelper(context);
            okhttp3.OkHttpClient client = new okhttp3.OkHttpClient();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://falldetections.site11.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();

            APIService service = retrofit.create(APIService.class);

            SMSData smsData = new SMSData();
            smsData.setUsername(user);
            smsData.setPassword(pass);

            Call<GetSms> call = service.checkThaiBulkUser(smsData.getUsername()
                    , smsData.getPassword());

            publishProgress();

            try {
                Response<GetSms> getSmsResponse = call.execute();

                if (getSmsResponse.body() != null) {
                    if(getSmsResponse.body().getCredit()!=null) {
                        return getSmsResponse.body().getCredit();
                    }else{
                        return "-1";
                    }
                } else {
                    return "-1";
                }


            } catch (IOException e) {
                e.printStackTrace();
            }

            return "-1";
        }


        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);


            if (s != null) {

                pd.dismiss();
                String str = s;
                if (!str.equals("")) {
                    int value = Integer.valueOf(str);
                    if (value != -1) {
                        SharePreferenceHelper sp = new SharePreferenceHelper(context);
                        sp.putUseContactSharePreference(true);
                        sp.putUsernameSharePreference(user);
                        sp.putPasswordSharePreference(pass);
                        sp.putTelSharePreference(number);

                        Intent intent = new Intent(context, MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(context, "Wrong username & password", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(context, "Failed to connect", Toast.LENGTH_SHORT).show();
                }
            }

        }
    }


}
