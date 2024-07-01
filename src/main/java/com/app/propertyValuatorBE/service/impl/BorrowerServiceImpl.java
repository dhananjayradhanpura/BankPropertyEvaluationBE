package com.app.propertyValuatorBE.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.app.propertyValuatorBE.dto.BorrowerDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.app.propertyValuatorBE.entity.Borrower;
import com.app.propertyValuatorBE.entity.User;
import com.app.propertyValuatorBE.repository.BorrowerRepository;
import com.app.propertyValuatorBE.repository.UserRepository;
import com.app.propertyValuatorBE.service.BorrowerService;

@Service
public class BorrowerServiceImpl implements BorrowerService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BorrowerRepository borrowerRepository;
	
	@Override
	public List<BorrowerDto> getBorrowerData(Long userId) {
		Optional<User> optuser = userRepository.findById(userId);
		if (optuser.isPresent()) {
			List<BorrowerDto> borrowerDtoList;
			Optional<List<Borrower>> borrowerList = borrowerRepository.findByUserId(userId);
			if (borrowerList.isPresent() && !CollectionUtils.isEmpty(borrowerList.get())) {
				borrowerDtoList = new ArrayList<>();
				borrowerList.get().forEach(borrower -> {
					BorrowerDto borrowerDto = new BorrowerDto();
					BeanUtils.copyProperties(borrower, borrowerDto);
					borrowerDtoList.add(borrowerDto);
				});
			} else {
				borrowerDtoList = null;
			}

			return borrowerDtoList;
		}
		return null;
	}

	
}
