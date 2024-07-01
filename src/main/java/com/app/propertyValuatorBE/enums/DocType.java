package com.app.propertyValuatorBE.enums;

import lombok.Getter;

public enum DocType {

    TITLE_DEED("Title Deed"),
    NATIONAL_ID("National Identity Card"),
    BUILDING_PERMITS("Building Permits"),
    BIRTH_CERTIFICATE("Birth Certificate"),
    MARRIAGE_CERTIFICATE("Marriage Certificate"),
    QUOTATION("Quotation"),
    LOI("Letter of Intent");

    @Getter
    private final String docType;

    DocType(String docType){
        this.docType = docType;
    }
}
