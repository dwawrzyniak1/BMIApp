package com.example.damia.bmiapplication;

/**
 * Created by damia on 10.03.2018.
 */

public class KilogramMeterBMI extends BMI {

    KilogramMeterBMI(){
        this(0, 0);
    }

    KilogramMeterBMI(double height, double mass){
        super(height, mass);
        setBmiRange();
    }

    public double calculateBMI() {
        if(!isDataCorrect())
            return BMI_ERR_INPUT;
        return mMass/(mHeight*mHeight);
    }

    private void setBmiRange(){
        BMI_MIN_HEIGHT = 0.5;
        BMI_MAX_HEIGHT = 3.0;
        BMI_MIN_MASS = 3;
        BMI_MAX_MASS = 300;
    }


}
