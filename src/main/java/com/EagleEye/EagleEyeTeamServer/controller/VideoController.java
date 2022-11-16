package com.EagleEye.EagleEyeTeamServer.controller;

import com.EagleEye.EagleEyeTeamServer.common.ApiResponse;
import com.EagleEye.EagleEyeTeamServer.dto.VideoShowDto;
import com.EagleEye.EagleEyeTeamServer.service.VideoService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Description;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequiredArgsConstructor
public class VideoController {
    private final VideoService videoService;
    private final WebClient webClient;

    @PostMapping("/upload")
    @Description("사진 혹은 비디오 파일을 업로드하는 api" +
            "Requset는 titleName : application/json과 " +
            "video : multipart/form-data 가 필요합니다")
    @ApiOperation("파일 업로드")
    public ApiResponse<String> uploadVideo(
            @RequestParam String titleName,
            @RequestPart MultipartFile video) {
        videoService.uploadVideo(titleName, video);
        return ApiResponse.success("ok");
    }

    @GetMapping("/showAllVideo")
    @Description("업로드했던 사진 혹은 비디오 파일을 업로드하는 api")
    @ApiOperation("파일 조회")
    public ApiResponse<List<VideoShowDto>> showAllVideo() {
        List<VideoShowDto> result = videoService.videoShowAll();
        return ApiResponse.success(result);
    }

    @GetMapping("/connect-check")
    @Description("반환타입 Data : String" +
            "단순 문자열 ok가 반환됩니다.")
    public ApiResponse<String> connectCheck() {
        return ApiResponse.success("ok");
    }

    @GetMapping("/connect-check2")
    @Description("반환타입 Data : {list}" +
            "list의 첫 번재에는 ok 문자열" +
            "list의 두 번째에는 사진의 링크가 반환됩니다.")
    public ApiResponse<List<String>> connectCheck2() {
        List<String> list = new ArrayList<>();
        list.add("ok");
        list.add("https://www.gstatic.com/youtube/img/promos/growth/a0518425715a2ee589c7eec7e54ce956556f9c191f1dfb2f7ca4e38f273c4872_244x112.png");
        return ApiResponse.success(list);
    }

    @GetMapping("fastapi-test")
    public ApiResponse<?> fastapiTest() {

        Flux<String> data = webClient.get()
                .uri("localhost:8000/fastapi-test")
                .retrieve()
                .bodyToFlux(String.class);

        return  ApiResponse.success(data.blockFirst());
    }
}
