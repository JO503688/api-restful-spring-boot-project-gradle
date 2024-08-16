/* package net.javaguides.springboot.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(
        description = "UserDto Model Information"
)
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Long id;
    // User first name should not be null or empty
    @Schema(
            description = "User First Name"
    )
    @NotEmpty(message = "User first name should not be null or empty OK")
    private String firstName;

    @Schema(
            description = "User LastName Name"
    )
    @NotEmpty(message = "User LastName name should not be null or empty OK")
    @NotBlank
    private String lastName;

    // Email address should be valid too
    @Schema(
            description = "User EmailAddress Name"
    )
    @NotEmpty(message = "User EmailAddress should not be null or empty OK")
    @Email(message = "Email address should be valid OK")
    private String email;
}
 */

package net.javaguides.springboot.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

@Schema(
        description = "UserDTO Model Information"
)


/* 
Un record es una clase especial en Java que está diseñada para 
contener datos inmutables. Es útil cuando necesitas una clase 
para agrupar un conjunto de valores de manera concisa y segura. 

Un record automáticamente genera:
--> Constructor para inicializar todos los campos.
--> Métodos equals, hashCode, y toString.
--> Getters (denominados "componentes") para los campos. En nuestro caso:
--> --> id()
--> --> firstName()
--> --> lastName()
--> --> email()
--> Los campos en un record son private y final por defecto.
*/


public record UserDto(
        Long id,

        @Schema(
                description = "User First Name"
        )
        @NotEmpty(message = "User first name should not be null or empty OK")
        String firstName,

        @Schema(
            description = "User LastName Name"
        )
        @NotEmpty(message = "User LastName name should not be null or empty OK")
        @NotBlank
        String lastName,

        @Schema(
            description = "User EmailAddress Name"
        )
        @NotEmpty(message = "User EmailAddress should not be null or empty OK")
        @Email(message = "Email address should be valid OK")
        String email

) {
}