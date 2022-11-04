package com.EagleEye.EagleEyeTeamServer.controller;

import com.EagleEye.EagleEyeTeamServer.dto.ApiResponse;
import com.EagleEye.EagleEyeTeamServer.dto.VideoShowDto;
import com.EagleEye.EagleEyeTeamServer.dto.VideoUploadRequest;
import com.EagleEye.EagleEyeTeamServer.service.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequiredArgsConstructor
public class VideoController {
    private final VideoService videoService;
    private final WebClient webClient;

    @PostMapping("/upload")
    public ApiResponse<String> uploadVideo(
            @RequestBody VideoUploadRequest videoUploadRequest,
            @RequestPart MultipartFile video) {
        videoService.uploadVideo(videoUploadRequest, video);
        return new ApiResponse<>("ok");
    }

    @GetMapping("/showAllVideo")
    public ApiResponse<List<VideoShowDto>> showAllVideo() {
        List<VideoShowDto> result = videoService.videoShowAll();
        return new ApiResponse<>(result);
    }

    @GetMapping("/connect-check")
    public ApiResponse<String> connectCheck() {

        return new ApiResponse<>("ok");
    }

    @GetMapping("/connect-check2")
    public ApiResponse<List<String>> connectCheck2() {
        List<String> list = new ArrayList<>();
        list.add("ok");
        list.add("https://www.gstatic.com/youtube/img/promos/growth/a0518425715a2ee589c7eec7e54ce956556f9c191f1dfb2f7ca4e38f273c4872_244x112.png");
        return new ApiResponse<>(list);
    }

    @GetMapping("fastapi-test")
    public ApiResponse<Mono<String>> fastapiTest() {

        return new ApiResponse<>(webClient.get()
                .uri("localhost:8000/fastapi-test")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .block()
                .bodyToMono(String.class));
    }
}
