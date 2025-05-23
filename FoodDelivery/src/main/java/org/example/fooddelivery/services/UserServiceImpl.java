package org.example.fooddelivery.services;

import lombok.RequiredArgsConstructor;
import org.example.fooddelivery.entities.User;
import org.example.fooddelivery.entities.dtos.UserDtos.UpdateUserDto;
import org.example.fooddelivery.entities.dtos.UserDtos.UserDto;
import org.example.fooddelivery.exceptions.EntityNotFoundException;
import org.example.fooddelivery.repositories.UserRepository;
import org.example.fooddelivery.services.contracts.UserService;
import org.example.fooddelivery.util.MapperUtil;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final MapperUtil mapperUtil;

    @Override
    public UserDto getSingleUser(int id) {
        return mapperUtil.getModelMapper()
                .map(userRepository.findUserByIdAndIsActiveTrue(id), UserDto.class);
    }

    @Override
    public User getSingleUserEntity(int id) {
        return userRepository.findUserByIdAndIsActiveTrue(id).orElseThrow(
                () -> new EntityNotFoundException("User with id " + id + " not found")
        );
    }

    @Override
    public List<UserDto> getAllUsers() {
        return mapperUtil.mapList(userRepository.findAllByIsActiveTrue(), UserDto.class);
    }

//    @Override
//    public UserDtoWithIdAndEmail createUser(CreateUserDto createUserDto) {
//
//        User user = User.builder()
//                .firstName(createUserDto.getFirstName())
//                .lastName(createUserDto.getLastName())
//                .email(createUserDto.getEmail())
//                .password(createUserDto.getPassword())
//                .phoneNumber(createUserDto.getPhoneNumber())
//                .birthDate(createUserDto.getBirthDate())
//                .build();
//
//        userRepository.save(user);
//
//        return mapperUtil.getModelMapper().map(user, UserDtoWithIdAndEmail.class);
//    }

    @Override
    public UserDto updateUser(UpdateUserDto updateUserDto, int id) {

        User user = userRepository.findUserByIdAndIsActiveTrue(id)
                .orElseThrow(() -> new EntityNotFoundException("User", id));


        user.setEmail(updateUserDto.getEmail());
        user.setPassword(updateUserDto.getPassword());
        user.setPhoneNumber(updateUserDto.getPhoneNumber());

        userRepository.save(user);

        return mapperUtil.getModelMapper().map(user, UserDto.class);
    }

    @Override
    public void deactivateUser(int id) {

        User user = userRepository.findUserByIdAndIsActiveTrue(id)
                .orElseThrow(() -> new EntityNotFoundException("User", id));

        user.setActive(false);
        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return this.userRepository.findUserByEmailAndIsActiveTrue(email)
                .orElseThrow(() -> new EntityNotFoundException("User", "email", email));
    }
}
