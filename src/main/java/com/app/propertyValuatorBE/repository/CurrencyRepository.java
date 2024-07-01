package com.app.propertyValuatorBE.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.app.propertyValuatorBE.entity.Currency;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency,Long> {
    
	Currency findByName(String name);
}
