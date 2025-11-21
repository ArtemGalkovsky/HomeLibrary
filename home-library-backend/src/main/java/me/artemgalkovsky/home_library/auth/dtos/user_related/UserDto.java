package me.artemgalkovsky.home_library.auth.dtos.user_related;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Builder
@Data
@ToString
public class UserDto {

    private String email;
    private String username;
    private Date registrationDate;
    private Boolean isActive;
    private Boolean isEmailVerified;

    private RoleDto roleDao;
}
