package com.example.damia.bmiapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final String TEXT_VIEW_RESULT_KEY = "result";
    private static final String EDIT_TEXT_HEIGHT_KEY = "height";
    private static final String EDIT_TEXT_MASS_KEY = "mass";
    private static final String SWITCH_STATE = "switchkey";
    private static final boolean SWITCH_STATE_KG_M = false;
    private static final boolean SWITCH_STATE_LBS_INCH = true;

    private EditText etHeight;
    private EditText etMass;
    private TextView tvMass;
    private TextView tvHeight;
    private Switch swUnits;

    private BMI mBMI;
    private SharedPreferences mPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = findViewById(R.id.appbar);
        setSupportActionBar(myToolbar);
        initiateViews();
        mPreferences = getPreferences(Context.MODE_PRIVATE);
        mBMI = new KilogramMeterBMI();
        restoreData();
    }

    private void initiateViews() {
        etHeight = findViewById(R.id.et_put_height);
        etMass = findViewById(R.id.et_put_mass);
        tvMass = findViewById(R.id.tv_put_mass);
        tvHeight = findViewById(R.id.tv_put_height);
        swUnits = findViewById(R.id.switch_unit);
    }

    @Override
    

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.menu_item_about:
                openAboutAuthorActivity();
                return true;
            case R.id.menu_item_save:
                saveData();
                showToast(R.string.toast_data_saved);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState){
        etHeight.setText(savedInstanceState.getString(EDIT_TEXT_HEIGHT_KEY));
        etMass.setText(savedInstanceState.getString(EDIT_TEXT_MASS_KEY));
    }

    @Override
    public void onSaveInstanceState(Bundle outState){
        outState.putString(EDIT_TEXT_HEIGHT_KEY, etHeight.getText().toString());
        outState.putString(EDIT_TEXT_MASS_KEY, etMass.getText().toString());
        super.onSaveInstanceState(outState);
    }

    public void changeUnits(View view){
        if(swUnits.isChecked() == SWITCH_STATE_LBS_INCH){
            changeUnitsToLbsInch(true);
        }else{
            changeUnitsToKgM(true);
        }
        cleanEditTexts();
    }

    public void sendBMI(View view){
        Intent intent = new Intent(this, DisplayBMIActivity.class);
        String result = calculateAndGetStringResult();
        intent.putExtra(TEXT_VIEW_RESULT_KEY, result);
        startActivity(intent);
    }

    public void cleanEditText(View view){
        ((EditText)view).setText("");
    }

    private void showToast(int msgId) {
        Toast.makeText(this, msgId, Toast.LENGTH_SHORT).show();
    }

    private void saveData() {
        SharedPreferences.Editor editor = mPreferences.edit();
        passDataToEditor(editor);
        editor.apply();
    }

    private void passDataToEditor(SharedPreferences.Editor editor) {
        String height = etHeight.getText().toString();
        String mass = etMass.getText().toString();
        boolean swUnitsState = swUnits.isChecked();
        editor.putString(EDIT_TEXT_HEIGHT_KEY, height);
        editor.putString(EDIT_TEXT_MASS_KEY, mass);
        editor.putBoolean(SWITCH_STATE, swUnitsState);
    }

    private void restoreData(){
        String height = mPreferences.getString(EDIT_TEXT_HEIGHT_KEY, "");
        String mass = mPreferences.getString(EDIT_TEXT_MASS_KEY, "");
        boolean checked = mPreferences.getBoolean(SWITCH_STATE, true);
        setRestoredConfig(height, mass, checked);
    }

    private void setRestoredConfig(String height, String mass, boolean checked) {
        etHeight.setText(height);
        etMass.setText(mass);
        swUnits.setChecked(checked);
        if(checked == SWITCH_STATE_KG_M) changeUnitsToKgM(false);
        else changeUnitsToLbsInch(false);
    }

    private void openAboutAuthorActivity(){
        Intent intent = new Intent(this, AboutFullscreenActivity.class);
        startActivity(intent);
    }

    private void changeUnitsToKgM(boolean showInfo) {
        tvMass.setText(R.string.tv_mass_kg);
        tvHeight.setText(R.string.tv_height_m);
        mBMI = new KilogramMeterBMI();
        if(showInfo) showToast(R.string.toast_unit_changed_kg_m);
    }

    private void changeUnitsToLbsInch(boolean showInfo) {
        tvMass.setText(R.string.tv_mass_lbs);
        tvHeight.setText(R.string.tv_height_inch);
        mBMI = new PoundInchBMI();
        if(showInfo) showToast(R.string.toast_unit_changed_lbs_inch);
    }

    private void cleanEditTexts() {
        cleanEditText(etHeight);
        cleanEditText(etMass);
    }

    private String calculateAndGetStringResult(){
        double height = checkData(etHeight);
        double mass = checkData(etMass);
        passValues(height, mass);
        return getValidResult();
    }

    private String getValidResult(){
        String result = mBMI.calculateBMI() == BMI.BMI_ERR_INPUT ? "Incorrect data" : Double.toString(mBMI.calculateBMI());
        return result;
    }

    private double checkData(EditText editText){
        String input = editText.getText().toString();
        double data;
        try{
            data = Double.parseDouble(input);
        }
        catch(RuntimeException e){
            data = BMI.BMI_ERR_INPUT;
        }
        return data;
    }

    private void passValues(double height, double mass){
        mBMI.setHeight(height);
        mBMI.setMass(mass);
    }
}
