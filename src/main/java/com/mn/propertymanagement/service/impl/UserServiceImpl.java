package com.mn.propertymanagement.service.impl;

import com.mn.propertymanagement.converter.UserConverter;
import com.mn.propertymanagement.entity.UserEntity;
import com.mn.propertymanagement.exception.BusinessException;
import com.mn.propertymanagement.exception.ErrorModel;
import com.mn.propertymanagement.modal.UserDTO;
import com.mn.propertymanagement.repository.UserRepository;
import com.mn.propertymanagement.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserConverter userConverter;

    public UserServiceImpl(UserRepository userRepository, UserConverter userConverter) {
        this.userRepository = userRepository;
        this.userConverter = userConverter;
    }

    @Override
    public UserDTO register(UserDTO userDTO) {

        Optional<UserEntity> optUe = userRepository.findUserByEmail(userDTO.getEmail());

        if(optUe.isPresent()){

            List<ErrorModel> errorModelList = new ArrayList<>();
            ErrorModel errorModel = new ErrorModel();
            errorModel.setCode("EMAIL_ALREADY_EXIST");
            errorModel.setMessage("The Email Which You Are Trying To Register Already Exist");
            errorModelList.add(errorModel);

            throw new BusinessException(errorModelList);

        }

        UserEntity userEntity = userConverter.convertDTOtoEntity(userDTO);
        userRepository.save(userEntity);

        userDTO = userConverter.convertEntityToDTO(userEntity);

        return userDTO;
    }


    @Override
    public UserDTO login(String email, String password) throws BusinessException {

        Optional<UserEntity> optionalUserEntity = userRepository.findUserByEmailAndPassword(email, password);

        UserDTO userDTO = null;

        if(optionalUserEntity.isPresent()) {
            userDTO = userConverter.convertEntityToDTO(optionalUserEntity.get());
        }else{
            List<ErrorModel> errorModelList = new ArrayList<>();
            ErrorModel errorModel = new ErrorModel();
            errorModel.setCode("INVALID_LOGIN");
            errorModel.setMessage("Incorrect Email or Password");
            errorModelList.add(errorModel);

            throw new BusinessException(errorModelList);
        }

        return userDTO;
    }

    @Override
    public List<UserDTO> getUsers() {

        List<UserEntity> userEntityList = (List<UserEntity>) userRepository.findAll();

        List<UserDTO> userDTOArrayList = new ArrayList<>();

        for(UserEntity user : userEntityList){
            userDTOArrayList.add(userConverter.convertEntityToDTO(user));
        }

        return userDTOArrayList;
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }


}
