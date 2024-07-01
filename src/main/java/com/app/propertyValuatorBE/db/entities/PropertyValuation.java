package com.app.propertyValuatorBE.db.entities;

import java.io.Serializable;
import java.util.UUID;

import com.app.propertyValuatorBE.config.audit.Auditable;
import com.app.propertyValuatorBE.enums.EvaluationType;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PropertyValuation extends Auditable<String> implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    private String id = UUID.randomUUID().toString();

    private String fosReferenceNumber;

    @Enumerated(EnumType.STRING)
    private EvaluationType typeOfEvaluation;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "faciliy_id",nullable = false)
    private Facility facility;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String referanceNo;

}
