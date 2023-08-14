package com.mn.propertymanagement.service.impl;

import com.mn.propertymanagement.entity.UserEntity;
import com.mn.propertymanagement.exception.BusinessException;
import com.mn.propertymanagement.exception.ErrorModel;
import com.mn.propertymanagement.repository.PropertyRepository;
import com.mn.propertymanagement.converter.PropertyConverter;
import com.mn.propertymanagement.entity.PropertyEntity;
import com.mn.propertymanagement.modal.PropertyDTO;
import com.mn.propertymanagement.repository.UserRepository;
import com.mn.propertymanagement.service.PropertyService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class PropertyServiceImpl implements PropertyService {

    private final PropertyRepository propertyRepository;

    private final PropertyConverter propertyConverter;

    private final UserRepository userRepository;

    public PropertyServiceImpl(PropertyRepository propertyRepository, PropertyConverter propertyConverter, UserRepository userRepository) {
        this.propertyRepository = propertyRepository;
        this.propertyConverter = propertyConverter;
        this.userRepository = userRepository;
    }


    @Override
    public PropertyDTO saveProperty(PropertyDTO propertyDTO) {

        Optional<UserEntity> optUe = userRepository.findById(propertyDTO.getUserId());

        if(optUe.isPresent()) {

            PropertyEntity pe = propertyConverter.convertDTOtoEntity(propertyDTO);
            pe.setUserEntity(optUe.get());
            pe = propertyRepository.save(pe);
            return propertyConverter.convertEntityToDTO(pe);

        }else{

            List<ErrorModel> errorModelList = new ArrayList<>();
            ErrorModel errorModel = new ErrorModel();
            errorModel.setCode("USER_ID_NOT_EXISTS");
            errorModel.setMessage("User does not exists");
            errorModelList.add(errorModel);

            throw new BusinessException(errorModelList);
        }

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
    public List<PropertyDTO> getAllPropertiesForUser(Long userId){

        List<PropertyEntity> listOfProps = (List<PropertyEntity>) propertyRepository.findAllByUserEntityId(userId);

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
