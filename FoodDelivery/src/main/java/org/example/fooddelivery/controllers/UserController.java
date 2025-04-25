package org.example.fooddelivery.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.fooddelivery.entities.dtos.UserDtos.CreateUserDto;
import org.example.fooddelivery.entities.dtos.UserDtos.UpdateUserDto;
import org.example.fooddelivery.entities.dtos.UserDtos.UserDto;
import org.example.fooddelivery.entities.dtos.UserDtos.UserDtoWithIdAndEmail;
import org.example.fooddelivery.services.contracts.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable int id) {
        return userService.getSingleUser(id);
    }

    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping
    public UserDtoWithIdAndEmail createUser(@RequestBody CreateUserDto createUserDto) {
        return userService.createUser(createUserDto);
    }

    @PutMapping("/{id}")
    public UserDto updateUser(@PathVariable int id, @RequestBody UpdateUserDto updateUserDto) {
        return userService.updateUser(updateUserDto, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deactivateUser(@Valid @PathVariable int id){
        userService.deactivateUser(id);
        return ResponseEntity.ok("User deactivated successfully!");
    }


}
