package com.example.damia.bmiapplication;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }



    @Test
    public void for_valid_data_LbsInch_BMI_is_returned_correctly(){
        BMI bmiCalculator = new PoundInchBMI(1.80, 73.4);
        double bmi = bmiCalculator.calculateBMI();
        assertEquals(22.654, bmi, 0.001);
    }

    @Test
    void zero_lbs_mass_and_inch_height_return_ERR(){
        BMI bmiCalculator = new PoundInchBMI(0, 0);
        double bmi = bmiCalculator.calculateBMI();
        assertEquals(BMI.BMI_ERR_INPUT, bmi);
    }

}

