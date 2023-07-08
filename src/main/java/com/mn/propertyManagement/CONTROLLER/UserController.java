package com.mn.propertyManagement.CONTROLLER;

import com.mn.propertyManagement.MODAL.UserDTO;
import com.mn.propertyManagement.SERVICE.IMPL.UserServiceImpl;
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
    public ResponseEntity<UserDTO> register(@RequestBody UserDTO userDTO){

        userDTO = userService.register(userDTO);

        return new ResponseEntity<>(userDTO, HttpStatus.CREATED);

    }
}
