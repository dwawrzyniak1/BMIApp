package com.example.damia.bmiapplication;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class DisplayBMIActivity extends AppCompatActivity {

    private static double UNDERWEIGHT_MAX = 18.5;
    private static double GOOD_BMI_MAX = 25;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_bmi);

        String bmiResult = getMessage(MainActivity.TEXT_VIEW_RESULT_KEY);
        showResult(bmiResult);

        Toolbar myToolbar = findViewById(R.id.display_app_bar);
        setSupportActionBar(myToolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showResult(String bmiResult) {
        TextView textView = findViewById(R.id.tv_bmi_result);
        textView.setText(bmiResult);
        double result = tryGetResult(bmiResult);
        showInfoToast(result);
    }

    private void showInfoToast(double result) {
        if(result == BMI.BMI_ERR_INPUT)
            showToast(R.string.input_error);
        else if (result < UNDERWEIGHT_MAX)
            showToast(R.string.underweight);
        else if (result < GOOD_BMI_MAX)
            showToast(R.string.good_weight);
        else
            showToast(R.string.overweight);
    }

    private void showToast(int msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    private double tryGetResult(String bmiResult) {
        double result;
        try{
            result = Double.parseDouble(bmiResult);
        }catch(RuntimeException e){
            result = BMI.BMI_ERR_INPUT;
        }
        return result;
    }

    private String getMessage(String intentKey) {
        Intent intent = getIntent();
        return intent.getStringExtra(intentKey);
    }
}
