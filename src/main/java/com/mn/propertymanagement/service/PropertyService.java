package com.mn.propertymanagement.service;

import com.mn.propertymanagement.modal.PropertyDTO;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface PropertyService {

    PropertyDTO saveProperty(PropertyDTO propertyDTO);

    List<PropertyDTO> getAllProperties();

    List<PropertyDTO> getAllPropertiesForUser(Long userId);

    PropertyDTO updateProperty(@RequestBody PropertyDTO propertyDTO, Long propertyId);

    PropertyDTO patchPropertyPriceOrAndDescription(@RequestBody PropertyDTO propertyDTO, Long propertyId);

    void deleteProperty(Long propertyId);

}
