package lv.java2.shopping_list.web.controllers;

import lv.java2.shopping_list.ServiceResponse;
import lv.java2.shopping_list.services.user.get.GetUserService;
import lv.java2.shopping_list.services.user.registration.UserRegistrationService;
import lv.java2.shopping_list.web.dto.UserDTO;
import lv.java2.shopping_list.web.dto.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
public class UserController {

    @Autowired
    private UserRegistrationService registrationService;

    @Autowired
    private GetUserService getUserService;

    @Autowired
    private UserMapper mapper;


    @PostMapping (value = "/register")
    public ResponseEntity<ServiceResponse> register(@Validated @RequestBody UserDTO userDTO) {
        ServiceResponse<UserDTO> response = registrationService.register(userDTO);
        URI location = buildLocationUri(response.getData());
        return ResponseEntity.status(HttpStatus.CREATED).location(location).body(response);
    }

    @GetMapping(value = "/users/{userId}")
    public ResponseEntity<UserDTO> getAccount(@PathVariable("userId") Long userId) {
        UserDTO userDTO = getUserService.findById(userId);
        return ResponseEntity.status(HttpStatus.OK).body(userDTO);
    }

    private URI buildLocationUri(UserDTO userDTO) {
        return UriComponentsBuilder.newInstance().scheme("http")
                .host("localhost")
                .port("8080")
                .path("/users/{id}").buildAndExpand(userDTO.getUserId()).toUri();
    }


    //TODO Delete User method

}
