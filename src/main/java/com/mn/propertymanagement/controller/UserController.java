package com.mn.propertymanagement.controller;

import com.mn.propertymanagement.modal.UserDTO;
import com.mn.propertymanagement.service.impl.UserServiceImpl;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
public class UserController {


    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getUsers(){
            return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
    }


    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@Valid @RequestBody UserDTO userDTO){
        return new ResponseEntity<>(userService.register(userDTO), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<UserDTO> login(@Valid @RequestBody UserDTO userDTO){
        return new ResponseEntity<>(userService.login(userDTO.getEmail(), userDTO.getPassword()), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<Object> delete(@PathVariable Long userId){
        userService.deleteUser(userId);

        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
}
