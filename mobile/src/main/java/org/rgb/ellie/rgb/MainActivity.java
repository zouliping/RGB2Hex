package org.rgb.ellie.rgb;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private EditText r_et, g_et, b_et;
    private TextView value_tv;
    private Button change_btn;
    private View bg_view;

    private StringBuilder result_value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }

    private void initViews() {
        r_et = (EditText) findViewById(R.id.r_value);
        g_et = (EditText) findViewById(R.id.g_value);
        b_et = (EditText) findViewById(R.id.b_value);
        value_tv = (TextView) findViewById(R.id.result_value);
        change_btn = (Button) findViewById(R.id.change_btn);
        bg_view = findViewById(R.id.background_view);

        change_btn.setOnClickListener(clickListener);
        r_et.addTextChangedListener(textWatcher);
        g_et.addTextChangedListener(textWatcher);
        b_et.addTextChangedListener(textWatcher);
    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.change_btn:
                    changeToHex();
                    break;
            }
        }
    };

    private void changeToHex() {
        int r_value = 0;
        int g_value = 0;
        int b_value = 0;

        try{
            if (!TextUtils.isEmpty(r_et.getText().toString())) {
                r_value = Integer.parseInt(r_et.getText().toString());
            }

            if (!TextUtils.isEmpty(g_et.getText().toString())) {
                g_value = Integer.parseInt(g_et.getText().toString());
            }

            if (!TextUtils.isEmpty(b_et.getText().toString())) {
                b_value = Integer.parseInt(b_et.getText().toString());
            }
        }catch (NumberFormatException e) {
            e.printStackTrace();
        }

        result_value = new StringBuilder();
        result_value.append("#");
        result_value.append(String.format("%02X", r_value));
        result_value.append(String.format("%02X", g_value));
        result_value.append(String.format("%02X", b_value));
//        result_value.append(Integer.toHexString(0x100 | r_value).substring(1));
//        result_value.append(Integer.toHexString(0x100 | g_value).substring(1));
//        result_value.append(Integer.toHexString(0x100 | b_value).substring(1));
        Log.e("ellie", result_value.toString());
        value_tv.setText(result_value);

        bg_view.setBackgroundColor(Color.parseColor(result_value.toString()));
    }

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            String value = s.toString();
            if (!TextUtils.isEmpty(value)) {
                int intVal = Integer.parseInt(value);

                if (intVal < 0) {
                    s.replace(0, s.length(), "0");
                }else if (intVal > 255) {
                    s.replace(0, s.length(), "255");
                }
            }
        }
    };

}
