package com.mn.propertyManagement.SERVICE;

import com.mn.propertyManagement.MODAL.PropertyDTO;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface PropertyService {

    PropertyDTO saveProperty(PropertyDTO propertyDTO);

    List<PropertyDTO> getAllProperties();

    PropertyDTO updateProperty(@RequestBody PropertyDTO propertyDTO, Long propertyId);

    PropertyDTO patchPropertyPriceOrAndDescription(@RequestBody PropertyDTO propertyDTO, Long propertyId);

    void deleteProperty(Long propertyId);

}
