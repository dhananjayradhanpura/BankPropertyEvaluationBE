package com.app.propertyValuatorBE.db.entities;

import java.io.Serializable;
import java.util.UUID;

import com.app.propertyValuatorBE.config.audit.Auditable;
import com.app.propertyValuatorBE.enums.DocType;
import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Doc extends Auditable<String> implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    private String id = UUID.randomUUID().toString();


    private Facility facility;
    private String type;

    @ManyToOne
    @JoinColumn(name="user_id",nullable = false)
    private User user;

    @Lob
    @Column(name="document", length=100000)
    private byte[] document;

    private String fileName;

    private Long fileSize;
}
