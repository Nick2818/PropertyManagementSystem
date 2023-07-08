package com.mn.propertyManagement.SERVICE;

import com.mn.propertyManagement.MODAL.UserDTO;

import javax.xml.crypto.URIDereferencer;
import java.util.List;

public interface UserService {

    UserDTO register(UserDTO userDTO);

    UserDTO login(String email, String password);

    List<UserDTO> getUsers();
}
