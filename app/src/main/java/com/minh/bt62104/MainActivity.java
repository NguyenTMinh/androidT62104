package com.minh.bt62104;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText mEditText1;
    private EditText mEditText2;
    private RadioGroup mRadioGroup;
    private CheckBox mCheckBox1;
    private CheckBox mCheckBox2;
    private CheckBox mCheckBox3;
    private EditText mEditText3;
    private Button mButton1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        // view trong main activity
        mEditText1 = findViewById(R.id.et1);
        mEditText2 = findViewById(R.id.et2);
        mEditText3 = findViewById(R.id.et3);
        mRadioGroup = findViewById(R.id.rg1);
        mCheckBox1 = findViewById(R.id.cb1);
        mCheckBox2 = findViewById(R.id.cb2);
        mCheckBox3 = findViewById(R.id.cb3);
        mButton1 = findViewById(R.id.bt1);

        //alert thong
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        View custom = getLayoutInflater().inflate(R.layout.alert_info_dialog, null);
        AlertDialog alertDialog = builder.setView(custom).create();

        mButton1.setOnClickListener(v -> {
            String name = mEditText1.getText().toString();
            String cmnd = mEditText2.getText().toString();

            if (TextUtils.isEmpty(name) || TextUtils.isEmpty(cmnd)) {
                return;
            }
            if (!TextUtils.isDigitsOnly(cmnd) || cmnd.length() != 9) {
                return;
            }
            if (!mCheckBox1.isChecked() && !mCheckBox2.isChecked() && !mCheckBox3.isChecked()) {
                return;
            }

            TextView textView = custom.findViewById(R.id.tv6);
            Button button = custom.findViewById(R.id.bt2);

            RadioButton radioButton = findViewById(mRadioGroup.getCheckedRadioButtonId());
            String fav = "";
            if (mCheckBox1.isChecked()) {
                fav += mCheckBox1.getText().toString();
            }
            if (mCheckBox2.isChecked()) {
                fav += (mCheckBox1.isChecked())? "- ":"" + mCheckBox2.getText().toString();
            }
            if (mCheckBox3.isChecked()) {
                fav += (mCheckBox2.isChecked())? "- ":"" + mCheckBox3.getText().toString();
            }
            String line = "------------------";
            String ttbs = mEditText3.getText().toString();

            String info1 = name + "\n" + cmnd + "\n" + radioButton.getText().toString() + "\n" + fav + "\n" + line + "\n"
                    + ((TextUtils.isEmpty(ttbs))? "": "Thông tin bổ sung\n" + ttbs + "\n" + line);

            if (textView != null && button != null) {
                textView.setText(info1);
                button.setOnClickListener(v1 -> {
                    alertDialog.cancel();
                });
            } else {
                Toast.makeText(this, "null", Toast.LENGTH_SHORT).show();
            }


            alertDialog.show();
            Toast.makeText(this, "clicked", Toast.LENGTH_SHORT).show();
        });

        //view trong dialog
    }
}