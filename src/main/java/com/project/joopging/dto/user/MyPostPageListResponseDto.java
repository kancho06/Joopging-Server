package com.project.joopging.dto.user;



import com.project.joopging.model.Post;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@ApiModel(value = "마이페이지 내가 만든 포스트 정보", description = "마이페이지 내가 만든 포스트 정보 응답 DTO")
public class MyPostPageListResponseDto {

    //Post 정보
    private final Long postId;
    private final String title;
    private final String content;
    private final LocalDateTime runningDate;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final Long dDay;
    private final String location;
    private final String type;
    private final String distance;
    private final int limitPeople;
    private final int nowPeople;
    private final String postImg;
    private final Integer viewCount;

    //작성자 정보
    private final String writerName;
    private final String userImg;
    private final String intro;


}
