package com.app.propertyValuatorBE.db.entities;

import java.time.Instant;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

/**
 * The Class RefreshToken.
 */

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RefreshJwtToken {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    private String id = UUID.randomUUID().toString();

    @OneToOne
    private User user;

    @Column(nullable = false, unique = true)
    private String jwtToken;

    @Column(nullable = false)
    private Instant expiryDate;

}
