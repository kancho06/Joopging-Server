package com.project.joopging.controller;

import com.project.joopging.dto.ResponseDto;
import com.project.joopging.dto.post.PostMainPageResponseDto;
import com.project.joopging.dto.review.AllReviewResponseDto;
import com.project.joopging.dto.user.MainPageResponseUserDto;
import com.project.joopging.security.UserDetailsImpl;
import com.project.joopging.service.MainPageService;
import com.project.joopging.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.HashMap;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Api(tags = "Main Controller Api V1")
public class MainController {

    private final MainPageService mainPageService;
    private final UserService userService;

    //메인페이지 리스트 보내주는 api
    @ApiOperation(value = "메인페이지 리스트")
    @GetMapping("/main")
    public ResponseDto mainPage(@ApiIgnore @AuthenticationPrincipal UserDetailsImpl userDetails) {
        HashMap<String, Object> resultList = new HashMap<>();

        //비로그인 로그인 공통 : 조회수 top 10
        List<PostMainPageResponseDto> hotPlaceList = mainPageService.getByHotPlace(userDetails);
        resultList.put("hot", hotPlaceList);

        //비로그인 로그인 공통 : Dday 기준 top 10
        List<PostMainPageResponseDto> closeSoonList = mainPageService.getByCloseSoon(userDetails);
        resultList.put("close", closeSoonList);

        //비로그인 로그인 공통 : 최신작성 리뷰 10개
        List<AllReviewResponseDto> reviewList = mainPageService.getReviews();
        resultList.put("reviews", reviewList);
        
        if (userDetails == null) { //비로그인 시
            //작성일 최신순
            List<PostMainPageResponseDto> recentPost = mainPageService.getByRecentPost(userDetails);
            resultList.put("recent", recentPost);
            return new ResponseDto(200L, "ok", resultList);
        } else { //로그인 시

            MainPageResponseUserDto mainPageResponseUserDto = userService.getUserInfo(userDetails);
            resultList.put("userInfo", mainPageResponseUserDto);

            //유저 지역기준 최신순 5개
            List<PostMainPageResponseDto> locationList = mainPageService.getByUserLocation(userDetails);
            resultList.put("location", locationList);

            //유저 거리기준 최신순 5개
            List<PostMainPageResponseDto> distanceList = mainPageService.getByUserDistance(userDetails);
            resultList.put("distance", distanceList);

            //유저 타입기준 최신순 5개
            List<PostMainPageResponseDto> typeList = mainPageService.getByUserType(userDetails);
            resultList.put("type", typeList);

            return new ResponseDto(200L, "ok", resultList);
        }
    }
}
