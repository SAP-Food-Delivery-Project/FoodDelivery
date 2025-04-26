package org.example.fooddelivery.services.contracts;

import org.example.fooddelivery.entities.User;
import org.example.fooddelivery.entities.dtos.UserDtos.CreateUserDto;
import org.example.fooddelivery.entities.dtos.UserDtos.UpdateUserDto;
import org.example.fooddelivery.entities.dtos.UserDtos.UserDto;
import org.example.fooddelivery.entities.dtos.UserDtos.UserDtoWithIdAndEmail;

import java.util.List;

public interface UserService {

    UserDto getSingleUser(int id);

    User getSingleUserEntity(int id);

    List<UserDto> getAllUsers();

    UserDtoWithIdAndEmail createUser(CreateUserDto createUserDto);

    UserDto updateUser(UpdateUserDto updateUserDto, int id);

    void deactivateUser(int id);
}
