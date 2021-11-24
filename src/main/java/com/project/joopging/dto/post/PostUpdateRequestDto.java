package com.project.joopging.dto.post;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class PostUpdateRequestDto {
    private final String title;
    private final String crewHeadIntro;
    private final String content;
    private final LocalDate endDate;
    private final String location;
    private final String type;
    private final String distance;
    private final String postImg;
}
