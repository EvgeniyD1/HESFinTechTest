package com.example.hes_fin_tech_test.domain;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

@Data
@Entity
@Table(name = "m_user_account")
public class UserAccount implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @Pattern(regexp = "[A-Za-z]{3,16}",
            message = "The username must contain only Latin letters, the number of characters is from 3 to 16")
    private String username;

    @Column
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{3,16}$",
            message = "The password must have only Latin characters and numbers.\n" +
                    "At least one character. At least one digit. \n" +
                    "the number of characters is from 3 to 16")
    private String password;

    @Column(name = "first_name")
    @Pattern(regexp = "[A-Za-z]{1,16}",
            message = "The first name must contain only Latin letters, the number of characters is from 1 to 16")
    private String firstName;

    @Column(name = "last_name")
    @Pattern(regexp = "[A-Za-z]{1,16}",
            message = "The last name must contain only Latin letters, the number of characters is from 1 to 16")
    private String lastName;

    @Column
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "created_at")
    private Date createdAt;

    public boolean isAdmin() {
        return role.equals(Role.ADMIN);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(role);
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return status.equals(Status.ACTIVE);
    }
}
