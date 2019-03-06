package lv.java2.shopping_list.web;

import lv.java2.shopping_list.ServiceResponse;
import lv.java2.shopping_list.user.services.get.GetUserService;
import lv.java2.shopping_list.user.services.registration.UserRegistrationService;
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


    @PostMapping
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<ServiceResponse> register(@Validated @RequestBody UserDTO userDTO) {
        ServiceResponse<UserDTO> response = registrationService.register(userDTO);
        URI location = UriComponentsBuilder.newInstance().scheme("http")
                .host("localhost")
                .port("8080")
                .path("/users/{id}").buildAndExpand(userDTO.getUserId()).toUri();
        return ResponseEntity.status(HttpStatus.CREATED).location(location).body(response);
    }

    @RequestMapping(value = "/users/{userId}", method = RequestMethod.GET)
    public ResponseEntity<ServiceResponse> getAccount(@PathVariable("userId") Long userId) {
        ServiceResponse response = getUserService.findById(userId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
