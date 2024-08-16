package net.javaguides.springboot.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import net.javaguides.springboot.dto.UserDto;
import net.javaguides.springboot.entity.User;
import net.javaguides.springboot.exception.ErrorDetails;
import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.List;

@Tag(
        name ="CRUD REST APIs for User Resource",
        description = "Create User, Update User, Get User, Get All Users, Delete User"
)
@RestController
@AllArgsConstructor
@RequestMapping("api/users")
public class UserController {

    private UserService userService;

    //Build create User REST API
    @Operation(
            summary="Create user REST API",
            description = "Create User REST API is used to save user in BD"
    )
    @ApiResponse(
            responseCode ="201",
            description = "HTTP Status 201 CREATED"
    )
    @PostMapping
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto user){
        UserDto savedUser= userService.createUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);

    }

    //Build get user by ID REST API
    //http://localhost:8080/api/users/1
    @Operation(
            summary="Get User By ID REST API",
            description = "Create User REST API is used to save user in BD"
    )
    @ApiResponse(
            responseCode ="201",
            description = "HTTP Status 200 SUCCESS"
    )
    @GetMapping("{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long userId){
        UserDto user = userService.getUserByID(userId);
        //----->System.out.println("User:" + user.getId());
        System.out.println("User:" + user.id());
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    // Build Get All Users REST API
    //http://localhost:8080/api/users
    @Operation(
            summary="Get All users REST API",
            description = "Get All Users REST API is used to get all users from BD"
    )
    @ApiResponse(
            responseCode ="200",
            description = "HTTP Status 200 SUCCESS"
    )
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers(){
        System.out.println("Invocated getAllusers");
       List<UserDto> users = userService.getAllUsers();
       return new ResponseEntity<>(users,HttpStatus.OK);

    }

    //Build Update User REST API
    //http://localhost:8080/api/users/1
    @Operation(
            summary="Update User REST API",
            description = "Update User REST API is used to update a particular user in the BD"
    )
    @ApiResponse(
            responseCode ="200",
            description = "HTTP Status 200 SUCCESS"
    )
    @PutMapping("{id}")
    public ResponseEntity<UserDto> updateUser( @PathVariable("id") Long userId,
                                           @RequestBody @Valid UserDto user){
        //----->user.setId(userId);

        // Crear un nuevo objeto UserDto pasandole id y los nuevos datos
        //Es necesario pasar en el body y en la URL el id?
        UserDto updatedUserDto = new UserDto(
                userId,                // Se establece el id (userId)
                user.firstName(),      // Obtenemos los valores del objeto UserDTO user
                user.lastName(),
                user.email()
        );

        UserDto updatedUser=userService.updateUser(updatedUserDto);
        return new ResponseEntity<>(updatedUser,HttpStatus.OK);
    }

    //Build Delete User REST API
    @Operation(
            summary="Delete User REST API",
            description = "Delete User REST API is used to delete a particular user in the BD"
    )
    @ApiResponse(
            responseCode ="200",
            description = "HTTP Status 200 SUCCESS"
    )
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long userId){
        userService.deleteUser(userId);
        return new ResponseEntity<>("User successfully deleted!", HttpStatus.OK);
    }

   /* @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails>
    handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest webRequest){
        ErrorDetails errorDetails = new ErrorDetails(
            LocalDateTime.now(),
                exception.getMessage(),
                webRequest.getDescription(false),"USER_NOT_FOUND"
        );
        return  new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }*/

}
