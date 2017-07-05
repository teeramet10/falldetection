package app.developteam.detectfall.myapplication.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

import app.developteam.detectfall.myapplication.Fuzzy;
import app.developteam.detectfall.myapplication.R;

public class TestActivity extends AppCompatActivity {
    EditText edtX;
    EditText edtY;
    EditText edtZ;
    EditText edtAcc;
    TextView tvDom;
    Button btnCal;
    Button btnClear;

    Context context = this;

    DecimalFormat decimalFormat = new DecimalFormat("#.##");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        edtX = (EditText) findViewById(R.id.edtx);
        edtY = (EditText) findViewById(R.id.edty);
        edtZ = (EditText) findViewById(R.id.edtz);
        edtAcc = (EditText) findViewById(R.id.edtacc);
        tvDom = (TextView) findViewById(R.id.tvdom);
        btnCal = (Button) findViewById(R.id.btncal);
        btnClear = (Button) findViewById(R.id.btnclear);

        btnCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                float x = 0;
                float y = 0;
                float z = 0;
                float acc = 0;

                String textx = edtX.getText().toString();
                String texty = edtY.getText().toString();
                String textz = edtZ.getText().toString();
                String textacc = edtAcc.getText().toString();
                if (!textx.equals("") && !texty.equals("") && !textz.equals("") & !textacc.equals("")) {
                    x = Float.parseFloat(textx);
                    y = Float.parseFloat(texty);
                    z = Float.parseFloat(textz);
                    acc = Float.parseFloat(textacc);

                    Fuzzy fuzzy = new Fuzzy();
                    fuzzy.calculateXMemberShipFx(x);
                    fuzzy.calculateYMemberShipFx(y);
                    fuzzy.calculateZMemberShipFx(z);
//                    fuzzy.calculateAcclerationMemberShipFx(acc);
                    String activity = fuzzy.rulesInference();


                    tvDom.setMovementMethod(new ScrollingMovementMethod());
                    tvDom.setText("DOM_X_HNegative =" + decimalFormat.format(fuzzy.getDOM_X_HNegative())
                            + "\nDOM_X_Negative :" + decimalFormat.format(fuzzy.getDOM_X_Negative())
                            + "\nDOM_X_Zero :" + decimalFormat.format(fuzzy.getDOM_X_Zero())
                            + "\nDOM_X_Positive :" + decimalFormat.format(fuzzy.getDOM_X_Positive())
                            + "\n\nDOM_Y_Negative :" + decimalFormat.format(fuzzy.getDOM_Y_HNegative())
                            + "\nDOM_Y_Zero :" + decimalFormat.format(fuzzy.getDOM_Y_NEGATIVE())
                            + "\nDOM_Y_Positive :" + decimalFormat.format(fuzzy.getDOM_Y_Positive())
                            + "\nDOM_Y_HPositive :" + decimalFormat.format(fuzzy.getDOM_Y_HPositive())
                            + "\n\nDOM_Z_Negative :" + decimalFormat.format(fuzzy.getDOM_Z_Negative())
                            + "\nDOM_Z_Zero :" + decimalFormat.format(fuzzy.getDOM_Z_Zero())
                            + "\nDOM_Z_Positive :" + decimalFormat.format(fuzzy.getDOM_Z_Positive())
                            + "\nDOM_Z_HPositive :" + decimalFormat.format(fuzzy.getDOM_Z_HPositive())
                            + "\nActivity : " + activity);
                } else {
                    Toast.makeText(context, "Please Fill in Form", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtX.setText("");
                edtY.setText("");
                edtZ.setText("");
                edtAcc.setText("");
                tvDom.setText("");
            }
        });
    }

}
