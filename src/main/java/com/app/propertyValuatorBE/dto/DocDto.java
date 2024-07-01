package com.app.propertyValuatorBE.dto;

import java.util.Date;

import com.app.propertyValuatorBE.enums.DocType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class DocDto {
    private Long id;
    private String type;
    private byte[] document;
    private String fileName;
    private Long fileSize;
    private Date createdDate;
    private String updatedBy;
    private String documentType;

}
