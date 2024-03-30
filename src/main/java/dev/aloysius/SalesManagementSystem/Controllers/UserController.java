package dev.aloysius.SalesManagementSystem.Controllers;

import dev.aloysius.SalesManagementSystem.CustomExceptions.UserAlreadyExistsException;
import dev.aloysius.SalesManagementSystem.Domains.ApplicationUsers;
import dev.aloysius.SalesManagementSystem.Domains.UserAuthentication;
import dev.aloysius.SalesManagementSystem.Services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    @PostMapping("/create")
    public void registerUser(@RequestBody ApplicationUsers users) throws UserAlreadyExistsException {
        System.out.println(users.toString());
        userService.registerUsers(users);

    }
    @PostMapping("/auth")
    public String authenticate(@RequestBody UserAuthentication userAuthentication){
        System.out.println(userAuthentication);
       return userService.authenticateUser(userAuthentication);

    }
    @PutMapping("/role")

    public void grantRoles(ApplicationUsers users){

    }

    @ExceptionHandler
    public ResponseEntity<?> handleException(Exception ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
