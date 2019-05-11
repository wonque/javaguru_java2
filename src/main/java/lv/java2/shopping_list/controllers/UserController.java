package lv.java2.shopping_list.controllers;

import lv.java2.shopping_list.controllers.validation.CreateEntity;
import lv.java2.shopping_list.dto.UserDTO;
import lv.java2.shopping_list.services.user.UserService;
import lv.java2.shopping_list.services.user.get.GetUserService;
import lv.java2.shopping_list.services.user.registration.UserRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> register(@Validated(CreateEntity.class) @RequestBody UserDTO userDTO) {

        UserDTO responseDto = userService.register(userDTO);
        URI locationHeader = buildLocationUri(responseDto.getUserId());

        return ResponseEntity.status(HttpStatus.CREATED).location(locationHeader)
                .body(responseDto);
    }

    @GetMapping(value = "/users/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> getUser(@PathVariable("userId") Long userId) {

        UserDTO userDTO = userService.findById(userId);

        return ResponseEntity.status(HttpStatus.OK).body(userDTO);
    }

    private URI buildLocationUri(Long userId) {
        return UriComponentsBuilder.newInstance().scheme("http")
                .host("localhost")
                .port("8080")
                .path("/users/{userId}").buildAndExpand(userId).toUri();
    }
}
