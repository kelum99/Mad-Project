package com.example.progym.user;

import android.graphics.Color;

public class Analysis {
    public String getAnalysis(float bmi){
        String analysis;

        if (bmi < 15) {
            analysis = "Very Severely Underweight\n Please Maintain Your Weight Properly !";
        } else if (bmi >=15 && bmi <= 16) {
            analysis = "Severely Underweight\n Please Maintain Your Weight Properly !";
        } else if (bmi >16 && bmi <= 18.5) {
            analysis = "Underweight \n Please Maintain Your Weight Properly !";
        } else if (bmi >18.5 && bmi <= 25) {
            analysis = "Normal (Healthy Weight) \n Keep Up !";
        } else if (bmi >25 && bmi <= 30) {
            analysis = "Overweight \n Please Reduce Your Weight! !";
        } else if (bmi >30 && bmi <= 35) {
            analysis = "Moderate Obesity \n Please Reduce Your Weight!";
        } else if (bmi >35 && bmi <= 40) {
            analysis = "Severe Obesity \n Please Reduce Your Weight!";
        } else {
            analysis ="Very Severe Obesity \n Please Reduce Your Weight!";
        }
        return analysis;
    }
   public int getColor(float bmi){
       int color;

       if (bmi < 15) {
           color = Color.parseColor("#A52A2A");
       } else if (bmi >=15 && bmi <= 16) {
           color = Color.parseColor("#A52A2A");
       } else if (bmi >16 && bmi <= 18.5) {
           color = Color.parseColor("#FF6666");
       } else if (bmi >18.5 && bmi <= 25) {
           color = Color.parseColor("#00FF00");
       } else if (bmi >25 && bmi <= 30) {
           color = Color.parseColor("#FF6666");
       } else if (bmi >30 && bmi <= 35) {
           color = Color.parseColor("#FF6666");
       } else if (bmi >35 && bmi <= 40) {
           color = Color.parseColor("##A52A2A");
       } else {
           color = Color.parseColor("#A52A2A");
       }
       return color;
   }
}
