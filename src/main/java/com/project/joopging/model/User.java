package com.project.joopging.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.joopging.dto.user.EditUserInfoDto;
import com.project.joopging.enums.Distance;
import com.project.joopging.enums.Location;
import com.project.joopging.enums.Type;
import com.project.joopging.enums.UserRoleEnum;
import com.project.joopging.util.Timestamped;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String nickname;

    @Column
    @JsonIgnore
    private String password;

    @Column
    @JsonIgnore
    private String email;

    @Column
    private String location;

    @Column
    private String type;

    @Column
    private String distance;

    @Column
    private String userImg;

    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;

    @OneToMany(mappedBy = "userJoin", orphanRemoval = true)
    @JsonIgnore
    private List<Party> join;

    @OneToMany(mappedBy = "writer")
    @JsonIgnore
    private List<Post> post;

    @OneToMany(mappedBy = "userReview")
    @JsonIgnore
    private List<Review> review;

    @OneToMany(mappedBy = "userComment")
    @JsonIgnore
    private List<Comment> comment;

    public User(String username, String password, String email, UserRoleEnum role, String enumLocation, String enumType, String enumDistance) {
        this.nickname = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.type = enumType;
        this.distance = enumDistance;
        this.location = enumLocation;
        this.userImg = null;
    }

    public void editUserInfo(String userImg, String password, String distanceName, String locationName, String typeName) {
        this.userImg = userImg;
        this.location = locationName;
        this.password = password;
        this.distance = distanceName;
        this.type = typeName;
    }
}
