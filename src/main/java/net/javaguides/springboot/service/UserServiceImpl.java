package net.javaguides.springboot.service;

import lombok.AllArgsConstructor;
import net.javaguides.springboot.dto.UserDto;
import net.javaguides.springboot.entity.User;
import net.javaguides.springboot.exception.EmailAlreadyExistsException;
import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.mapper.AutoUserMapper;
import net.javaguides.springboot.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {
        // Verificar si el correo ya existe
        Optional<User> optionalUser = userRepository.findByEmail(userDto.email());
        if (optionalUser.isPresent()) {
            throw new EmailAlreadyExistsException("Email Already exists for User");
        }

        // Convertir UserDto en User y guardarlo
        User user = AutoUserMapper.MAPPER.mapToUser(userDto);
        User savedUser = userRepository.save(user);

        // Convertir User guardado en UserDto
        return AutoUserMapper.MAPPER.mapToUserDto(savedUser);
    }

    @Override
    public UserDto getUserByID(Long userId) {
        // Buscar usuario por ID, o lanzar una excepción si no existe
        User user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("user", "id", userId)
        );

        // Convertir User en UserDto
        return AutoUserMapper.MAPPER.mapToUserDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();

        // Convertir lista de Users en lista de UserDtos
        return users.stream()
                .map(AutoUserMapper.MAPPER::mapToUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        // Buscar usuario existente por ID, o lanzar una excepción si no existe
        User existingUser = userRepository.findById(userDto.id()).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", userDto.id())
        );

        // Actualizar los campos del usuario existente
        existingUser.setFirstName(userDto.firstName());
        existingUser.setLastName(userDto.lastName());
        existingUser.setEmail(userDto.email());

        // Guardar el usuario actualizado y convertirlo a UserDto
        User updatedUser = userRepository.save(existingUser);
        return AutoUserMapper.MAPPER.mapToUserDto(updatedUser);
    }

    @Override
    public void deleteUser(Long userId) {
        // Buscar usuario por ID, o lanzar una excepción si no existe
        User existingUser = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", userId)
        );

        // Eliminar usuario
        userRepository.deleteById(userId);
    }
}
