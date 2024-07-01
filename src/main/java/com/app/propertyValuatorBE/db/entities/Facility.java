package com.app.propertyValuatorBE.db.entities;


import com.app.propertyValuatorBE.config.audit.Auditable;
import com.app.propertyValuatorBE.enums.Currency;
import com.app.propertyValuatorBE.enums.FacilityCategory;
import com.app.propertyValuatorBE.enums.PropertyValuationPurpose;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Facility extends Auditable<String> implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    private String id = UUID.randomUUID().toString();

    @Enumerated(EnumType.STRING)
    private FacilityCategory catagory;

    @Enumerated(EnumType.STRING)
    private PropertyValuationPurpose purpose;

    private Integer term;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    private Currency ccy;

    @Enumerated(EnumType.STRING)
    private FacilityCategory facilityCategory;

    private BigDecimal amount;

}