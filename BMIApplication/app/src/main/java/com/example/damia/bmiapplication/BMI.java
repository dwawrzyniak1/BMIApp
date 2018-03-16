package com.example.damia.bmiapplication;

/**
 * Created by damia on 07.03.2018.
 */

public abstract class BMI {

    public static final double BMI_ERR_INPUT = -1.0;
    protected static double BMI_MIN_HEIGHT = 0.5;
    protected static double BMI_MAX_HEIGHT = 3.0;
    protected static double BMI_MIN_MASS = 3;
    protected static double BMI_MAX_MASS = 300;

    protected double mHeight;
    protected double mMass;

    public BMI(double mHeight, double mMass) {
        setHeight(mHeight);
        setMass(mMass);
    }

    BMI(){
        this(0, 0);
    }

    public void setHeight(double height) {
        mHeight = height;
    }

    public void setMass(double mass) {
        mMass = mass;
    }

    abstract double calculateBMI();

    protected boolean isDataCorrect(){
        return isHeightCorrect(mHeight) && isMassCorrect(mMass);
    }

    private boolean isHeightCorrect(double height){
        return !(height > BMI_MAX_HEIGHT || height < BMI_MIN_HEIGHT);
    }

    private boolean isMassCorrect(double mass){
        return !(mass > BMI_MAX_MASS || mass < BMI_MIN_MASS);
    }

}
