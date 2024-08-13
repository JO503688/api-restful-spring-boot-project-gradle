package net.javaguides.springboot.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

// Transfirir datos del usuario entre las capas de la app. Validamos los datos. Separamos entre entidades y modelos 
// De transferencia.
@Schema(
        description = "UserDto Model Information"
)
public record UserDto(
        Long id,

        @Schema(description = "User First Name")
        @NotEmpty(message = "User first name should not be null or empty OK")
        String firstName,

        @Schema(description = "User LastName Name")
        @NotEmpty(message = "User LastName name should not be null or empty OK")
        @NotBlank String lastName,

        @Schema(description = "User EmailAddress Name")
        @NotEmpty(message = "User EmailAddress should not be null or empty OK")
        @Email(message = "Email address should be valid OK")
        String email
) {}
