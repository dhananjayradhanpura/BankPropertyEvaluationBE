package com.app.propertyValuatorBE.enums;

import lombok.Getter;

public enum EvaluationType {

    NEW("New"),
    EXISTING("Existing");

    @Getter
    private final String evaluationType;

    private EvaluationType(String evaluationType){
        this.evaluationType = evaluationType;
    }


}
