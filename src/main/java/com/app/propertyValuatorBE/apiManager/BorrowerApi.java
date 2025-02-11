package com.app.propertyValuatorBE.apiManager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.propertyValuatorBE.dto.BorrowerDto;
import com.app.propertyValuatorBE.service.BorrowerService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/borrower")
public class BorrowerApi {

	@Autowired
	private BorrowerService borrowerService;
	
    @GetMapping("/borrower/{userId}")
    public ResponseEntity<List<BorrowerDto>> getBorrowerData(@PathVariable Long userId) {
        return new ResponseEntity<>(borrowerService.getBorrowerData(userId), HttpStatus.OK);
    }
}
