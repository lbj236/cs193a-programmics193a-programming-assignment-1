package com.example.zachzhuang.myapplication;

import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends AppCompatActivity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    private EditText heightFeet;
    private EditText heightInches;
    private EditText weight;
    private boolean metric = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    //private void isFilled() {
        //Toast.makeText(this, "you got ", Toast.LENGTH_LONG).show();
        //Button calculate = (Button) findViewById(R.id.calculate);
        //if (heightFeet.getText().toString() != null &&
          //      heightInches.getText().toString() != null &&
            //    weight.getText().toString() != null) {
          //  Toast.makeText(this, "you got ", Toast.LENGTH_LONG).show();
        //}
    //}

    public void calculateBMI(View view) {
        heightFeet = (EditText) findViewById(R.id.heightFeet);
        heightInches = (EditText) findViewById(R.id.heightInch);
        weight = (EditText) findViewById(R.id.weight);
        if (heightFeet.getText() == null || heightInches.getText() == null || weight.getText() == null) {
            Toast.makeText(this, "Information incomplete", Toast.LENGTH_LONG).show();
        } else {
            TextView bmi = (TextView) findViewById(R.id.index);
            TextView health = (TextView) findViewById(R.id.healthCondition);
            double BMI;
            if (!metric) {
                int height = Integer.parseInt(heightFeet.getText().toString()) * 12
                        + Integer.parseInt(heightInches.getText().toString());
                int weightNo = Integer.parseInt(weight.getText().toString());
                double raw = (double) 703 * weightNo / height / height;
                double rounded = raw * 100;
                BMI = ((int) rounded) / 100.0;
                bmi.setText(Double.toString(BMI));

            } else {
                int height = Integer.parseInt(heightFeet.getText().toString()) * 100
                        + Integer.parseInt(heightInches.getText().toString());
                int weightNo = Integer.parseInt(weight.getText().toString());
                double raw = (double) 10000 * weightNo / height / height;
                double rounded = raw * 100;
                BMI = ((int) rounded) / 100.0;
                bmi.setText(Double.toString(BMI));
            }
            if (BMI < 18.5) {
                health.setText("UNDERWEIGHT");
                health.setTextColor(Color.YELLOW);
            } else if (BMI < 24.9) {
                health.setText("HEALTHY");
                health.setTextColor(Color.GREEN);
            } else if (BMI < 29.9) {
                health.setText("OVERWEIGHT");
                health.setTextColor(Color.YELLOW);
            } else {
                health.setText("OBESE");
                health.setTextColor(Color.RED);
            }
        }
    }


    public void switchUnit(View view) {
        heightFeet = (EditText) findViewById(R.id.heightFeet);
        heightInches = (EditText) findViewById(R.id.heightInch);
        weight = (EditText) findViewById(R.id.weight);
        if (!metric) {
            heightInches.setHint("cm");
            heightFeet.setHint("meters");
            weight.setHint("kg");
            metric = !metric;
        } else {
            heightInches.setHint("inches");
            heightFeet.setHint("feet");
            weight.setHint("pounds");
            metric = !metric;
        }
    }
}
