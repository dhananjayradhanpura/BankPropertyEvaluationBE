package com.app.propertyValuatorBE.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import com.app.propertyValuatorBE.dto.CurrencyDto;
import com.app.propertyValuatorBE.entity.Currency;
import com.app.propertyValuatorBE.repository.CurrencyRepository;
import com.app.propertyValuatorBE.service.CurrencyService;

@Slf4j
@Service
public class CurrencyServiceImpl implements CurrencyService {

    @Autowired
    private CurrencyRepository currencyRepository;
    
	@Override
    public List<CurrencyDto> getCurrencies() {
		log.info("CurrencyServiceImpl getCurrencies method is called.");
        List<CurrencyDto> currencyResponseList = new ArrayList<>();
        try {
            List<Currency> currencyList = currencyRepository.findAll();
            currencyList.forEach(currency -> {
                CurrencyDto currencyResponse = new CurrencyDto();
                currencyResponse.setId(currency.getId());
                currencyResponse.setName(currency.getName());
                currencyResponseList.add(currencyResponse);
            });
        } catch (RuntimeException execption) {
        	log.info("RuntimeException cought : {}",execption.getMessage());
        } catch (Exception execption) {
        	log.info("Genric Exception cought : {}",execption.getMessage());
        }
        return currencyResponseList;
    }
	
}
