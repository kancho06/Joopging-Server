package com.project.joopging.controller;


import com.project.joopging.dto.ResponseDto;
import com.project.joopging.dto.post.PostSearchesDto;
import com.project.joopging.model.Post;
import com.project.joopging.security.UserDetailsImpl;
import com.project.joopging.service.SerchService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Api(tags = "Search Controller Api V1")
public class SearchController {
    private final SerchService serchService;

    @ApiOperation(value = "검색")
    @GetMapping("/searches")
    public ResponseDto findUseByFilter(
            @ApiParam(value = "거리 카테고리") @RequestParam Integer distance,
            @ApiParam(value = "지형 카테고리") @RequestParam Integer type,
            @ApiParam(value = "지역 카테고리") @RequestParam Integer[] location,
            @ApiIgnore @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        List<PostSearchesDto> post = serchService.findUseByFilter(distance, type, location);

        return new ResponseDto(200L, "성공", post);
    }
}
