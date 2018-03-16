package com.example.damia.bmiapplication;

/**
 * Created by damia on 10.03.2018.
 */

public class PoundInchBMI extends BMI {

    PoundInchBMI(){
        this(0, 0);
    }

    PoundInchBMI(double height, double mass){
        super(height, mass);
        setRange();
    }

    public double calculateBMI(){
        return isDataCorrect() ? mMass * 703 / (mHeight*mHeight) : BMI_ERR_INPUT;
    }

    private void setRange() {
        BMI_MIN_HEIGHT = 25;
        BMI_MAX_HEIGHT = 125;
        BMI_MIN_MASS = 10;
        BMI_MAX_MASS = 500;
    }

}
