package com.app.propertyValuatorBE.dto;

import java.math.BigDecimal;

import com.app.propertyValuatorBE.enums.Currency;
import com.app.propertyValuatorBE.enums.Facility;
import com.app.propertyValuatorBE.enums.FacilityCategory;
import com.app.propertyValuatorBE.enums.PropertyValuationPurpose;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FacilityDto {
    private String id;
    private Facility facility;
    private FacilityCategory facilityCategory;
    private String purpose;
    private Integer term;
    private String ccy;
    private BigDecimal amount;
}