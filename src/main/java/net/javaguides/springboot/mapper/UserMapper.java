package net.javaguides.springboot.mapper;

import net.javaguides.springboot.dto.UserDto;
import net.javaguides.springboot.entity.User;

public class UserMapper {

    // Convert User JPA Entity into UserDto
    public static UserDto mapToUserDto(User user) {
        UserDto userDto = new UserDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail()

        );
        return userDto;
    }

    //Convert UserDto into User JPA Entity

    public static User mapToUser(UserDto userDto){
        /* getters utilizados por lombok
        User user = new User(
                userDto.getId(),
                userDto.getEmail(),
                userDto.getFirstName(),
                userDto.getLastName()
        );
        */
        //Se deben usar los getter utilizados por Records
        User user = new User(
                userDto.id(),
                userDto.email(),
                userDto.firstName(),
                userDto.lastName()
        );
        return user;
    }

}
