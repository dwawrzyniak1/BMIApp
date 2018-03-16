package com.example.damia.bmiapplication;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by damia on 15.03.2018.
 */

public class KilogramMeterBMITest {

    private BMI bmiCounter = new KilogramMeterBMI();
    private double bmi;

    @Test
    public void for_valid_data_KgM_BMI_is_returned_correctly(){
        setValues(1.7, 60.0);
        bmi = bmiCounter.calculateBMI();
        assertEquals(20.76124567, bmi, 0.001);
    }

    @Test // dla opcji z rzucaniem wyjatku @Test(expected = IllegalArgumentException.class)
    public void zero_kg_mass_and_m_height_return_ERR(){
        setValues(0.0, 0.0);
        bmi = bmiCounter.calculateBMI();
        assertEquals(BMI.BMI_ERR_INPUT, bmi);
    }

    @Test
    public void invalid_kg_mass_correct_m_height_return_ERR(){
        setValues(1.8, 0.0);
        bmi = bmiCounter.calculateBMI();
        assertEquals(BMI.BMI_ERR_INPUT, bmi);
    }

    @Test
    public void min_values_for_mass_and_height_is_correct(){
        setValues(BMI.BMI_MIN_HEIGHT, BMI.BMI_MIN_MASS);
        bmi = bmiCounter.calculateBMI();
        double expected = BMI.BMI_MIN_MASS/(KilogramMeterBMI.BMI_MIN_HEIGHT*KilogramMeterBMI.BMI_MIN_HEIGHT);
        assertEquals(expected, bmi, 0.001);
    }

    @Test
    public void max_values_for_mass_and_height_is_correct(){
        setValues(BMI.BMI_MAX_HEIGHT, BMI.BMI_MAX_MASS);
        bmi = bmiCounter.calculateBMI();
        double expected = BMI.BMI_MAX_MASS/(KilogramMeterBMI.BMI_MAX_HEIGHT*KilogramMeterBMI.BMI_MAX_HEIGHT);
        assertEquals(expected, bmi, 0.001);
    }

    private void setValues(double height, double mass) {
        bmiCounter.setMass(mass);
        bmiCounter.setHeight(height);
    }
}
