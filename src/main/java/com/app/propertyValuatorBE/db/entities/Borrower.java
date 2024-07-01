package com.app.propertyValuatorBE.db.entities;

import com.app.propertyValuatorBE.config.audit.Auditable;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Borrower extends Auditable<String> implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    private String id = UUID.randomUUID().toString();

    @Size(max = 50)
    @Column(nullable = false)
    private String name;

    @Size(max = 50)
    @Column(nullable = false)
    private String customerNumber;

    @Size(max = 20)
    @Column(nullable = false)
    private String contactNumber;

    @Size(max = 50)
    @Column(nullable = false)
    private String email;

    @Size(max = 200)
    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    @Builder.Default
    private Boolean isMainBorrower=Boolean.FALSE;

    @ManyToOne
    @JoinColumn(name="user_id",nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name="property_valuation",nullable = false , referencedColumnName = "id")
    private PropertyValuation propertyValuation;

    @ManyToOne
    @JoinColumn(name = "faciliy_id",nullable = false)
    private Facility facility;

}