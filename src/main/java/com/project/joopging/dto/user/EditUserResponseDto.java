package com.project.joopging.dto.user;

import com.project.joopging.enums.UserRoleEnum;
import com.project.joopging.model.User;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EditUserResponseDto {
    private Long id;

    private String userImg;

    private String location;

    private String type;

    private String distance;

    private UserRoleEnum role;

    private String intro;

    public EditUserResponseDto(User user) {
        this.id = user.getId();
        this.userImg = user.getUserImg();
        this.location = user.getLocation();
        this.type = user.getType();
        this.distance = user.getDistance();
        this.role = user.getRole();
        this.intro = user.getIntro();
    }
}
