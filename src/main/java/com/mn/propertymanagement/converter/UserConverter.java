package com.mn.propertymanagement.converter;

import com.mn.propertymanagement.entity.UserEntity;
import com.mn.propertymanagement.modal.UserDTO;
import org.springframework.stereotype.Component;


@Component
public class UserConverter {

    public UserEntity convertDTOtoEntity(UserDTO userDTO){

        UserEntity ue = new UserEntity();

        ue.setEmail(userDTO.getEmail());
        ue.setOwnerName(userDTO.getOwnerName());
        ue.setPassword(userDTO.getPassword());
        ue.setPhone(userDTO.getPhone());

        return ue;
    }

    public UserDTO convertEntityToDTO(UserEntity userEntity){

        UserDTO ud = new UserDTO();

        ud.setEmail(userEntity.getEmail());
        ud.setOwnerName(userEntity.getOwnerName());
        ud.setPassword(userEntity.getPassword());
        ud.setPhone(userEntity.getPhone());

        return ud;
    }
}
