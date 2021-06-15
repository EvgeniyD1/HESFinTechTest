package com.example.hes_fin_tech_test.domain;

import lombok.Data;

import javax.validation.constraints.Pattern;
import java.util.Date;

@Data
public class UserAccountDTO {

    private Long id;

    private String username;

    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{3,16}$",
            message = "The password must have only Latin characters and numbers.\n" +
                    "At least one character. At least one digit. \n" +
                    "the number of characters is from 3 to 16")
    private String password;

    @Pattern(regexp = "[A-Za-z]{1,16}",
            message = "The first name must contain only Latin letters, the number of characters is from 1 to 16")
    private String firstName;

    @Pattern(regexp = "[A-Za-z]{1,16}",
            message = "The last name must contain only Latin letters, the number of characters is from 1 to 16")
    private String lastName;

    private Role role;

    private Status status;

    private Date createdAt;
}
