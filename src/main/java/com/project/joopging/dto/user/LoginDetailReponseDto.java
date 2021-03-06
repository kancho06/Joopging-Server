package com.project.joopging.dto.user;

import com.project.joopging.enums.UserRoleEnum;
import io.swagger.annotations.ApiModel;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginDetailReponseDto {
    private Long id;

    private String nickname;

    private String email;

    private String location;

    private String type;

    private String distance;

    private String userImg;

    private UserRoleEnum role;

    private String intro;
}
