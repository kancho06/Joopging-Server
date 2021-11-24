package com.project.joopging.controller;

import com.project.joopging.dto.ResponseDto;
import com.project.joopging.dto.review.AllReviewResponseDto;
import com.project.joopging.dto.review.DetailReviewResponseDto;
import com.project.joopging.dto.review.ReviewRequestDto;
import com.project.joopging.exception.CustomErrorException;
import com.project.joopging.model.Review;
import com.project.joopging.model.User;
import com.project.joopging.security.UserDetailsImpl;
import com.project.joopging.service.ReviewService;
import com.project.joopging.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;


@RestController
@RequiredArgsConstructor
@Api(tags = "Review Controller Api V1")
public class ReviewController {

    private final ReviewService reviewService;
    
    //후기 작성
    @ApiOperation(value = "후기 작성")
    @PostMapping("/reviews")
    public ResponseDto createReview(@ApiParam(value = "후기 생성 정보") @RequestBody ReviewRequestDto requestDto, @ApiIgnore @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Review review = reviewService.createReview( requestDto, userDetails);
        return new ResponseDto(201L, "후기를 저장했습니다.", "");
    }

    //후기 수정
    @ApiOperation(value = "후기 수정")
    @PutMapping("/reviews/{review_id}")
    public ResponseDto editReview(@ApiParam(value = "후기 ID", required = true) @PathVariable("review_id") Long reviewId, @ApiParam(value = "후기 업데이트 정보", required = true) @RequestBody ReviewRequestDto requestDto) {
        Review review = reviewService.editReview(reviewId, requestDto);
        return new ResponseDto(200L, "후기를 수정했습니다.", "");
    }

    //후기 삭제
    @ApiOperation(value = "후기 삭제")
    @DeleteMapping("/reviews/{review_id}")
    public ResponseDto deleteReivew(@ApiParam(value = "후기 ID", required = true) @PathVariable("review_id") Long reviewId, @ApiIgnore @AuthenticationPrincipal UserDetailsImpl userDetails) {
        reviewService.deleteReview(reviewId, userDetails);
        return new ResponseDto(204L, "후기를 삭제했습니다.", "");
    }

    //전체 후기 보여주기
    @ApiOperation(value = "전체 후기 페이지")
    @GetMapping("/reviews")
    public ResponseDto showAllReview() {
        List<AllReviewResponseDto> reviewList = reviewService.showAllReview();
        return new ResponseDto(200L, "전체 후기를 불러왔습니다", reviewList);
    }

    //후기 상세보기
    @ApiOperation(value = "후기 상세보기")
    @GetMapping("/reviews/{review_id}")
    public ResponseDto showOneReview(@ApiParam(value = "후기 ID", required = true) @PathVariable("review_id") Long reviewId) {
        HashMap<String, Object> result = reviewService.showOneReview(reviewId);
        return new ResponseDto(200L, "후기 상세정보를 불러왔습니다.", result);
    }
}
