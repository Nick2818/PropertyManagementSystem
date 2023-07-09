package com.mn.propertymanagement.service;

import com.mn.propertymanagement.exception.BusinessException;
import com.mn.propertymanagement.modal.UserDTO;

import java.util.List;

public interface UserService {

    UserDTO register(UserDTO userDTO);

    UserDTO login(String email, String password) throws BusinessException;

    List<UserDTO> getUsers();

    void deleteUser(Long id);
}
