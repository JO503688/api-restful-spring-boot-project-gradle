package net.javaguides.springboot.dto;

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
