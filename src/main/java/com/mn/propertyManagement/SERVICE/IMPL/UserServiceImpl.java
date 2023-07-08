package com.mn.propertyManagement.SERVICE.IMPL;

import com.mn.propertyManagement.CONVERTER.UserConverter;
import com.mn.propertyManagement.ENTITY.UserEntity;
import com.mn.propertyManagement.MODAL.UserDTO;
import com.mn.propertyManagement.REPOSITORY.UserRepository;
import com.mn.propertyManagement.SERVICE.UserService;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

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
        UserEntity userEntity = userConverter.convertDTOtoEntity(userDTO);
        userRepository.save(userEntity);

        userDTO = userConverter.convertEntityToDTO(userEntity);

        return userDTO;
    }


    @Override
    public UserDTO login(String email, String password) {
        return null;
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
}
