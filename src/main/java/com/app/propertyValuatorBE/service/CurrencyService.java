package com.app.propertyValuatorBE.service;

import com.app.propertyValuatorBE.dto.CurrencyDto;

import java.util.List;


public interface CurrencyService {

	List<CurrencyDto> getCurrencies();
}
