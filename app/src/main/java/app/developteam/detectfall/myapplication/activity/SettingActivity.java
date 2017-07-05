package app.developteam.detectfall.myapplication.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import app.developteam.detectfall.myapplication.R;
import app.developteam.detectfall.myapplication.SharePreferenceHelper;

public class SettingActivity extends AppCompatActivity {
    public  static final String TAG="SETTING";
    Context context = this;
    private TextView tvIPAddress;
    private EditText edtIP;
    private String ip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        tvIPAddress = (TextView) findViewById(R.id.ipaddress);

        SharePreferenceHelper sp = new SharePreferenceHelper(context);
        ip = sp.getIPSharePreference();
        tvIPAddress.setText(ip);

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                onBackPressed();
                break;

            case R.id.formsetip:
                editIPAddress();
                break;

            case R.id.formsettel:
                Intent intent=new Intent(context,EmergencyContactActivity.class);
                intent.putExtra(TAG,1);
                startActivity(intent);
                break;

        }
    }

    public void editIPAddress() {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dialog_ip, null);
        edtIP = (EditText) view.findViewById(R.id.edtipaddress);
        edtIP.setText(ip);

        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        alert.setTitle("IP Address");
        alert.setView(view);
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                SharePreferenceHelper sp = new SharePreferenceHelper(context);
                sp.putIPSharePreference(edtIP.getText().toString());

                tvIPAddress.setText(sp.getIPSharePreference());
            }
        });
        alert.setNegativeButton("CANCEL", null);
        alert.create().show();

    }

    public void editTel() {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dialog_ip, null);
        edtIP = (EditText) view.findViewById(R.id.edtipaddress);
        edtIP.setText(ip);

        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        alert.setTitle("Emergency Contact");
        alert.setView(view);
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                SharePreferenceHelper sp = new SharePreferenceHelper(context);
                sp.putIPSharePreference(edtIP.getText().toString());

                tvIPAddress.setText(sp.getIPSharePreference());
            }
        });
        alert.setNegativeButton("CANCEL", null);
        alert.create().show();

    }
}
