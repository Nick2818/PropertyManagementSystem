package com.mn.propertyManagement.SERVICE.IMPL;

import com.mn.propertyManagement.REPOSITORY.PropertyRepository;
import com.mn.propertyManagement.CONVERTER.PropertyConverter;
import com.mn.propertyManagement.ENTITY.PropertyEntity;
import com.mn.propertyManagement.MODAL.PropertyDTO;
import com.mn.propertyManagement.SERVICE.PropertyService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PropertyServiceImpl implements PropertyService {

    private final PropertyRepository propertyRepository;

    private final PropertyConverter propertyConverter;

    public PropertyServiceImpl(PropertyRepository propertyRepository, PropertyConverter propertyConverter) {
        this.propertyRepository = propertyRepository;
        this.propertyConverter = propertyConverter;
    }

    @Override
    public PropertyDTO saveProperty(PropertyDTO propertyDTO) {

        PropertyEntity pe = propertyConverter.convertDTOtoEntity(propertyDTO);
        pe = propertyRepository.save(pe);

        return propertyConverter.convertEntityToDTO(pe);

    }

    @Override
    public List<PropertyDTO> getAllProperties() {

        List<PropertyEntity> listOfProps = (List<PropertyEntity>) propertyRepository.findAll();

        List<PropertyDTO> propList = new ArrayList<>();

        for(PropertyEntity pe: listOfProps){
            PropertyDTO dto = propertyConverter.convertEntityToDTO(pe);
            propList.add(dto);

        }

        return propList;
    }

    @Override
    public PropertyDTO updateProperty(PropertyDTO propertyDTO, Long propertyId) {

        Optional<PropertyEntity> optEn = propertyRepository.findById(propertyId);

        PropertyDTO dto = null;

        if(optEn.isPresent()){
            PropertyEntity pe = optEn.get();

            pe.setPropertyType(propertyDTO.getPropertyType());

            pe.setPropertyDescription(propertyDTO.getPropertyDescription());

            pe.setAddress(propertyDTO.getAddress());

            pe.setPrice(propertyDTO.getPrice());

            dto = propertyConverter.convertEntityToDTO(pe);

            propertyRepository.save(pe);
        }

        return dto;
    }

    @Override
    public PropertyDTO patchPropertyPriceOrAndDescription(PropertyDTO propertyDTO, Long propertyId) {

        Optional<PropertyEntity> optEn = propertyRepository.findById(propertyId);

        PropertyDTO dto = null;

        if(optEn.isPresent()){
            PropertyEntity pe = optEn.get();

            if(propertyDTO.getPrice() != null && propertyDTO.getPropertyDescription() == null) {
                pe.setPrice(propertyDTO.getPrice());
            }else if(propertyDTO.getPropertyDescription() != null && propertyDTO.getPrice() == null){
                pe.setPropertyDescription(propertyDTO.getPropertyDescription());
            }else{
                pe.setPrice(propertyDTO.getPrice());
                pe.setPropertyDescription(propertyDTO.getPropertyDescription());
            }

            dto = propertyConverter.convertEntityToDTO(pe);

            propertyRepository.save(pe);
        }

        return dto;
    }

    @Override
    public void deleteProperty(Long propertyId) {

        propertyRepository.deleteById(propertyId);
    }

}
