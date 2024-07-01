package com.app.propertyValuatorBE.service;

import com.app.propertyValuatorBE.dto.BorrowerDto;

import java.util.List;


public interface BorrowerService {

	List<BorrowerDto> getBorrowerData(Long userId);

}
