package com.doctime.identity.adapter.inbound.web;

import com.doctime.identity.adapter.inbound.web.DTO.CreateUserRequest;
import com.doctime.identity.adapter.inbound.web.DTO.CreateUserResponse;
import com.doctime.identity.port.inbound.CreateUser;
import com.doctime.identity.port.inbound.UserCommand;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    private final CreateUser createUser;

    public UserController(CreateUser createUser) {
        this.createUser = createUser;
    }

    @PostMapping("/register")
    public ResponseEntity<CreateUserResponse> createUser(@RequestBody CreateUserRequest user) {
        UserCommand userCommand = new UserCommand(user.email(), user.password(), user.firstName(), user.lastName(), user.role());
        createUser.createUser(userCommand);
        return ResponseEntity.status(HttpStatus.CREATED).body(new CreateUserResponse(userCommand.email(), userCommand.firstName(), userCommand.lastName()));
    }
}
