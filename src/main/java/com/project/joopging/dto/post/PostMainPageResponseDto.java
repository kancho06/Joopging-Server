package com.project.joopging.dto.post;

import com.project.joopging.model.Post;
import com.project.joopging.model.User;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Getter
@NoArgsConstructor
@ApiModel(value = "메인페이지 정보 응답", description = "메인페이지 정보 응답 DTO")
public class PostMainPageResponseDto {
    private Long postId;
    private String title;
    private String content;
    private LocalDateTime runningDate;
    private Long dDay;
    private String location;
    private String type;
    private String distance;
    private int limitPeople;
    private int nowPeople;
    private String postImg;
    private Integer viewCount;
    private Long userId;
    private String nickname;
    private String userImg;

    public PostMainPageResponseDto(Post post, User writer) {
        this.postId = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.runningDate = post.getRunningDate();
        this.dDay = ChronoUnit.DAYS.between(post.getStartDate(), post.getEndDate());
        this.location = post.getLocation().getName();
        this.type = post.getType().getName();
        this.distance = post.getDistance().getName();
        this.limitPeople = post.getLimitPeople();
        this.nowPeople = post.getNowPeople();
        this.postImg = post.getPostImg();
        this.viewCount = post.getViewCount();
        this.userId = writer.getId();
        this.nickname = writer.getNickname();
        this.userImg = writer.getUserImg();
    }
}
