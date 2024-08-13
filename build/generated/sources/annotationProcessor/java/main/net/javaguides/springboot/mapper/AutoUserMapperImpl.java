package net.javaguides.springboot.mapper;

import javax.annotation.processing.Generated;
import net.javaguides.springboot.dto.UserDto;
import net.javaguides.springboot.entity.User;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-12T12:34:05-0600",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.8.jar, environment: Java 17.0.11 (Oracle Corporation)"
)
public class AutoUserMapperImpl implements AutoUserMapper {

    @Override
    public UserDto mapToUserDto(User user) {
        if ( user == null ) {
            return null;
        }

        Long id = null;
        String firstName = null;
        String lastName = null;
        String email = null;

        id = user.getId();
        firstName = user.getFirstName();
        lastName = user.getLastName();
        email = user.getEmail();

        UserDto userDto = new UserDto( id, firstName, lastName, email );

        return userDto;
    }

    @Override
    public User mapToUser(UserDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        User user = new User();

        user.setId( userDto.id() );
        user.setFirstName( userDto.firstName() );
        user.setLastName( userDto.lastName() );
        user.setEmail( userDto.email() );

        return user;
    }
}
