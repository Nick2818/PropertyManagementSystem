package com.mn.propertymanagement.controller;

import com.mn.propertymanagement.modal.PropertyDTO;
import com.mn.propertymanagement.service.PropertyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/properties")
public class PropertyController {

    private final PropertyService propertyService;

    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @PostMapping
    public ResponseEntity<PropertyDTO> saveProperty(@RequestBody PropertyDTO propertyDTO){

        propertyService.saveProperty(propertyDTO);

        return new ResponseEntity<>(propertyDTO, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PropertyDTO>> getAllProperties(){
        List<PropertyDTO> propertyList = propertyService.getAllProperties();

        return new ResponseEntity<>(propertyList, HttpStatus.OK);
    }

    @PutMapping("/{propertyId}")
    public ResponseEntity<PropertyDTO> updateProperty(@RequestBody PropertyDTO propertyDTO, @PathVariable Long propertyId){

        propertyDTO = propertyService.updateProperty(propertyDTO, propertyId);

        return new ResponseEntity<>(propertyDTO, HttpStatus.CREATED);

    }

    @PatchMapping("/{propertyId}")
    public ResponseEntity<PropertyDTO> patchPropertyPriceOrAndDescription(@RequestBody PropertyDTO propertyDTO, @PathVariable Long propertyId){
        propertyDTO = propertyService.patchPropertyPriceOrAndDescription(propertyDTO, propertyId);

        return new ResponseEntity<>(propertyDTO, HttpStatus.CREATED);
    }

    @DeleteMapping("/{propertyId}")
    public ResponseEntity<Object> deleteProperty(@PathVariable Long propertyId){
        propertyService.deleteProperty(propertyId);

        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
}
