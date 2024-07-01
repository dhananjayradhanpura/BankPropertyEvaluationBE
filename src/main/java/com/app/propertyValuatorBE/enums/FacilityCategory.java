package com.app.propertyValuatorBE.enums;

import lombok.Getter;

public enum FacilityCategory {
    APARTMENT_25000("25000 - Apartment"),
    PBWM_HOUSING_25010("25010 – PBWM Housing");

    @Getter
    private final String facilityCategory;

    FacilityCategory(String facilityCategory) {
        this.facilityCategory = facilityCategory;
    }
}
