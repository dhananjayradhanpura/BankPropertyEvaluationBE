package com.app.propertyValuatorBE.service.impl;

import com.app.propertyValuatorBE.dto.DropDownResponseDto;
import com.app.propertyValuatorBE.enums.*;
import com.app.propertyValuatorBE.enums.Currency;
import com.app.propertyValuatorBE.service.DropDownService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DropDownServiceImpl implements DropDownService {

    @Override
    public List<DropDownResponseDto> getAllDropDowns(){
        List<DropDownResponseDto> DropDownResponseDtoList = new ArrayList<>();

        DropDownResponseDto dropListCurrency = new DropDownResponseDto();
        List<String> currency = new ArrayList<>();
        Map<String, List<String>> currencyMap = new HashMap<>();
        Arrays.stream(Currency.values()).forEach(ccy -> {
            currency.add(ccy.toString());
        });
        currencyMap.put("currency", currency);
        dropListCurrency.setDropDownOptions(currencyMap);
        DropDownResponseDtoList.add(dropListCurrency);

        DropDownResponseDto dropListDocType = new DropDownResponseDto();
        Map<String, List<String>> docTypeMap = new HashMap<>();
        List<String> docType = new ArrayList<>();
        Arrays.stream(DocType.values()).forEach(dt -> docType.add(dt.getDocType()));
        docTypeMap.put("docType", docType);
        dropListDocType.setDropDownOptions(docTypeMap);
        DropDownResponseDtoList.add(dropListDocType);

        DropDownResponseDto dropListEvaluationType = new DropDownResponseDto();
        Map<String, List<String>> evaluationTypeMap = new HashMap<>();
        List<String> evaluationType = new ArrayList<>();
        Arrays.stream(EvaluationType.values()).forEach(et -> evaluationType.add(et.getEvaluationType()));
        evaluationTypeMap.put("evaluationType", evaluationType);
        dropListEvaluationType.setDropDownOptions(evaluationTypeMap);
        DropDownResponseDtoList.add(dropListEvaluationType);

        DropDownResponseDto dropListFacility = new DropDownResponseDto();
        Map<String, List<String>> facilityMap = new HashMap<>();
        List<String> facility = new ArrayList<>();
        Arrays.stream(Facility.values()).forEach(fclt -> facility.add(fclt.getFacility()));
        facilityMap.put("facility", facility);
        dropListFacility.setDropDownOptions(facilityMap);
        DropDownResponseDtoList.add(dropListFacility);

        DropDownResponseDto dropListFacilityCategory = new DropDownResponseDto();
        Map<String, List<String>> facilityCategoryMap = new HashMap<>();
        List<String> facilityCategory = new ArrayList<>();
        Arrays.stream(FacilityCategory.values()).forEach(category -> facilityCategory.add(category.getFacilityCategory()));
        facilityCategoryMap.put("facilityCategory", facilityCategory);
        dropListFacilityCategory.setDropDownOptions(facilityCategoryMap);
        DropDownResponseDtoList.add(dropListFacilityCategory);

        DropDownResponseDto dropListPropertyValuationPurpose = new DropDownResponseDto();
        Map<String, List<String>> propertyValuationPurposeMap = new HashMap<>();
        List<String> propertyValuationPurpose = new ArrayList<>();
        Arrays.stream(PropertyValuationPurpose.values()).forEach(purpose -> propertyValuationPurpose.add(purpose.getPropertyEvaluationPurpose()));
        propertyValuationPurposeMap.put("propertyValuationPurpose", propertyValuationPurpose);
        dropListFacilityCategory.setDropDownOptions(propertyValuationPurposeMap);
        DropDownResponseDtoList.add(dropListPropertyValuationPurpose);

        return DropDownResponseDtoList;
    }
}
