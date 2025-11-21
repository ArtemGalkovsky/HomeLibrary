package me.artemgalkovsky.home_library.auth.dtos.user_related;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class RoleDto {

    private String roleName;
}
