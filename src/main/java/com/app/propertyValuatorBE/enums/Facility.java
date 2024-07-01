package com.app.propertyValuatorBE.enums;

import lombok.Getter;

public enum Facility {
    REVOLVING("Revolving"),
    NONREVOLVING("Non Revolving");

    @Getter
    private final String facility;

    Facility(String facility){
        this.facility = facility;
    }
}
