package com.mn.propertymanagement.converter;

import com.mn.propertymanagement.entity.PropertyEntity;
import com.mn.propertymanagement.modal.PropertyDTO;
import org.springframework.stereotype.Component;

@Component
public class PropertyConverter {

    public PropertyEntity convertDTOtoEntity(PropertyDTO propertyDTO){

        PropertyEntity pe = new PropertyEntity();

        pe.setId(propertyDTO.getId());

        pe.setPropertyType(propertyDTO.getPropertyType());

        pe.setPropertyDescription(propertyDTO.getPropertyDescription());

        pe.setAddress(propertyDTO.getAddress());

        pe.setPrice(propertyDTO.getPrice());

        return pe;
    }

    public PropertyDTO convertEntityToDTO(PropertyEntity propertyEntity){

        PropertyDTO propertyDTO = new PropertyDTO();

        propertyDTO.setId(propertyEntity.getId());

        propertyDTO.setAddress(propertyEntity.getAddress());

        propertyDTO.setPropertyType(propertyEntity.getPropertyType());

        propertyDTO.setPrice(propertyEntity.getPrice());

        propertyDTO.setPropertyDescription(propertyEntity.getPropertyDescription());

        propertyDTO.setUserId(propertyEntity.getUserEntity().getId());

        return propertyDTO;
    }
}
