package com.example.springbootlibrary.web.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
@Builder
public class UserDTO {

//    @NotNull(message = "id must be not null.")
    private Long id;

    @NotNull(message = "name must be not mull")
    @Length(max = 255, message = "Name length must be shorter than 255 symbols")
    private String name;

    @NotNull(message = "username must be not mull")
    @Length(max = 255, message = "Username length must be shorter than 255 symbols")
    private String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull(message = "Password must be not null.")
    private String password;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull(message = "Password confirmation must be not null.")
    private String passwordConfirmation;
}
