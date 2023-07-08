package com.mn.propertyManagement.CONVERTER;

import com.mn.propertyManagement.ENTITY.UserEntity;
import com.mn.propertyManagement.MODAL.UserDTO;
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
