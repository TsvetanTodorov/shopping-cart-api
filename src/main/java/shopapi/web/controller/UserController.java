package shopapi.web.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shopapi.exception.DomainException;
import shopapi.user.User;
import shopapi.user.UserRepository;
import shopapi.user.UserService;
import shopapi.web.dto.UserRequest;
import shopapi.web.dto.UserResponse;
import shopapi.web.mapper.DtoMapper;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    @Autowired
    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody @Valid UserRequest request) {

        User user = userService.createUser(request);

        UserResponse response = DtoMapper.fromUser(user);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getUsers() {

        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/email")
    public ResponseEntity<UserResponse> getUserByEmail(@RequestParam String email) {

        User userByEmail = userService.getUserByEmail(email);

        UserResponse response = DtoMapper.fromUser(userByEmail);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @DeleteMapping("/email")
    public ResponseEntity<String> deleteUserByEmail(@RequestParam String email) {

        boolean isDeleted = userService.deleteUserByEmail(email);

        if(isDeleted){
            return ResponseEntity.status(HttpStatus.OK).body("User with email: " + email + " deleted successfully");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with email: " + email + " not found");
        }
    }

    @PutMapping("/update-user")
    public ResponseEntity<UserResponse> updateUser(@RequestBody @Valid UserRequest request){

        try {
            UserResponse response = userService.updateUser(request);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(response);
        }catch (DomainException e){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(null);
        }
    }
}
