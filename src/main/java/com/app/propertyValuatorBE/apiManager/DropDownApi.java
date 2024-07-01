package com.app.propertyValuatorBE.apiManager;

import com.app.propertyValuatorBE.dto.DropDownResponseDto;
import com.app.propertyValuatorBE.service.DropDownService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/dropdown")
public class DropDownApi {

    @Autowired
    private DropDownService dropDownService;

    @GetMapping
    public ResponseEntity<List<DropDownResponseDto>> getDropListResponse(){
        return ResponseEntity.ok(dropDownService.getAllDropDowns());
    }
}
