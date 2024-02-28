package com.simplon.GroupeUserService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GroupUserDTO {

    private Long idGroupUser;

    private Long idGroup;

    private Long idUser;

    private Long idMembers;

}
