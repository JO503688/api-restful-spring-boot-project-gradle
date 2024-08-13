package net.javaguides.springboot.mapper;

import net.javaguides.springboot.dto.UserDto;
import net.javaguides.springboot.entity.User;

public class UserMapper {

    // Convert User JPA Entity into UserDto
    public static UserDto mapToUserDto(User user) {
        // Usamos el constructor del record directamente
        return new UserDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail()
        );
    }

    // Convert UserDto into User JPA Entity
    public static User mapToUser(UserDto userDto) {
        // Constructor de la entidad User con los valores del record
        return new User(
                userDto.id(),  // Acceso directo sin getId()
                userDto.firstName(),
                userDto.lastName(),
                userDto.email()
        );
    }
}
