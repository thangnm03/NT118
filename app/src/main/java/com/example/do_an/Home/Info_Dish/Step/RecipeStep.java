package com.example.do_an.Home.Info_Dish.Step;

public class RecipeStep {
    private int stepNumber;
    private String stepDescription;

    public RecipeStep(int stepNumber, String stepDescription) {
        this.stepNumber = stepNumber;
        this.stepDescription = stepDescription;
    }

    public int getStepNumber() {
        return stepNumber;
    }

    public void setStepNumber(int stepNumber) {
        this.stepNumber = stepNumber;
    }

    public String getStepDescription() {
        return stepDescription;
    }

    public void setStepDescription(String stepDescription) {
        this.stepDescription = stepDescription;
    }
}
