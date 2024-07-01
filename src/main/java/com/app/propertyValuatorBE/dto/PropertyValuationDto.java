package com.app.propertyValuatorBE.dto;

import java.util.Date;
import java.util.List;

import com.app.propertyValuatorBE.enums.EvaluationType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
public class PropertyValuationDto {

    private String id;
    private UserDto createdBy;
    private String fosreferenceNumber;
    private String type;
    private String mainBorrowerName;
    private String referanceNo;
    private Date createdDate;
    private Date modifiedDate;
    private FacilityDto facilityDto;
    private List<BorrowerDto> borrowersDto;
    private List<DocDto> documentsDto;
    private List<CommentsDto> commentsDto;

}