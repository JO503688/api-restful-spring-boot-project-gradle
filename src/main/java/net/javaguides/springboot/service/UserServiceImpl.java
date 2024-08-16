package net.javaguides.springboot.service;

import lombok.AllArgsConstructor;
import net.javaguides.springboot.dto.UserDto;
import net.javaguides.springboot.entity.User;
import net.javaguides.springboot.exception.EmailAlreadyExistsException;
import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.mapper.AutoUserMapper;
import net.javaguides.springboot.mapper.UserMapper;
import net.javaguides.springboot.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private ModelMapper modelMapper;
    @Override
    public UserDto createUser(UserDto userDto) {
        // Convert UserDto into User JPA ENTITY
        //User user= UserMapper.mapToUser(userDto);
        //User user= modelMapper.map(userDto,User.class);

        //Optional<User> optionalUser = userRepository.findByEmail(userDto.getEmail());
        Optional<User> optionalUser = userRepository.findByEmail(userDto.email());
        if(optionalUser.isPresent()){
            throw new EmailAlreadyExistsException("Email Already exists for User");

        }
        User user= AutoUserMapper.MAPPER.mapToUser(userDto);

        User savedUser= userRepository.save(user);
        // Convert User JPA entity to UserDto
        //UserDto savedUserDto = UserMapper.mapToUserDto(savedUser);
        //UserDto savedUserDto = modelMapper.map(savedUser,UserDto.class);
        UserDto savedUserDto = AutoUserMapper.MAPPER.mapToUserDto(savedUser);

        return savedUserDto;
    }

    @Override
    public UserDto getUserByID(Long userId) {
        //Optional<User> optionalUser = userRepository.findById(userId);
        User user= userRepository.findById(userId).orElseThrow(
                ()-> new ResourceNotFoundException("user", "id", userId)
        );
       // User user= optionalUser.get();
        //return UserMapper.mapToUserDto(user);
        //return modelMapper.map(user,UserDto.class);
        return AutoUserMapper.MAPPER.mapToUserDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        //return users.stream().map(UserMapper::mapToUserDto).collect(Collectors.toList());
        //return users.stream().map((user) -> modelMapper.map(user,UserDto.class)).collect(Collectors.toList());
        return users.stream().map(AutoUserMapper.MAPPER::mapToUserDto).collect(Collectors.toList());

    }

    @Override
    public UserDto updateUser(UserDto user) {

        //User existingUser = userRepository.findById(user.getId()).get();

        //User existingUser = userRepository.findById(user.getId()).orElseThrow(
        //        () -> new ResourceNotFoundException("User", "id", user.getId())
        //);
        User existingUser = userRepository.findById(user.id()).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", user.id())
        );

        //existingUser.setFirstName(user.getFirstName());
        //existingUser.setLastName(user.getLastName());
        //existingUser.setEmail(user.getEmail());

        existingUser.setFirstName(user.firstName());
        existingUser.setLastName(user.lastName());
        existingUser.setEmail(user.email());
        User updatedUser=userRepository.save(existingUser);
        //return UserMapper.mapToUserDto(updatedUser);
        //return modelMapper.map(updatedUser, UserDto.class);
        return AutoUserMapper.MAPPER.mapToUserDto(updatedUser);
    }

    @Override
    public void deleteUser(Long userId) {

        User existingUser = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", userId)
        );
        userRepository.deleteById(userId);

    }
}
