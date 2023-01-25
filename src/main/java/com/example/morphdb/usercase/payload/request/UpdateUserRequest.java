package com.example.morphdb.usercase.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserRequest {
    @Pattern(regexp = "^[A-Za-z|\\s]*$",message = "Invalid firstname")
    private String firstname;

    @Pattern(regexp = "^[A-Za-z|\\s]*$",message = "Invalid lastname")
    private String lastname;

    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,}$",
            message = "Minimum six characters, at least one letter and one number")
    private String password;
}
