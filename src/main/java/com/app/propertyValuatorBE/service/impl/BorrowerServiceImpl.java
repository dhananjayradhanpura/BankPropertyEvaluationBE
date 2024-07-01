package com.app.propertyValuatorBE.service.impl;

import com.app.propertyValuatorBE.db.entities.Borrower;
import com.app.propertyValuatorBE.db.entities.User;
import com.app.propertyValuatorBE.db.repository.BorrowerRepository;
import com.app.propertyValuatorBE.db.repository.UserRepository;
import com.app.propertyValuatorBE.dto.BorrowerDto;
import com.app.propertyValuatorBE.service.BorrowerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BorrowerServiceImpl implements BorrowerService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BorrowerRepository borrowerRepository;

    @Override
    public List<BorrowerDto> getBorrowerData(String userId) {
        Optional<User> optuser = userRepository.findById(userId);
        List<BorrowerDto> borrowerDtoList = new ArrayList<>();
        optuser.ifPresent(user -> {
            Optional<List<Borrower>> borrowerList = borrowerRepository.findByUserId(userId);
            borrowerList.ifPresent(borrowers -> {
                borrowerList.get().forEach(borrower -> {
                    BorrowerDto borrowerDto = new BorrowerDto();
                    BeanUtils.copyProperties(borrower, borrowerDto);
                    borrowerDtoList.add(borrowerDto);
                });
            });
        });
        return borrowerDtoList;
    }
}
