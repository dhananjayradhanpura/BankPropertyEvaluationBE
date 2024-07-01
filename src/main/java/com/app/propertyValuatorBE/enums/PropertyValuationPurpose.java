package com.app.propertyValuatorBE.enums;

import lombok.Getter;

public enum PropertyValuationPurpose {
    REPARATION("1-Reparation"),
    INHERITANCE("2-Inheritance"),
    CONSTRUCTION("3-Construction");

    @Getter
    private final String propertyEvaluationPurpose;

    PropertyValuationPurpose(String propertyEvaluationPurpose){
        this.propertyEvaluationPurpose = propertyEvaluationPurpose;
    }
}
