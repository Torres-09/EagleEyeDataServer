package com.EagleEye.EagleEyeTeamServer.controller;

import com.EagleEye.EagleEyeTeamServer.dto.ApiResponse;
import com.EagleEye.EagleEyeTeamServer.dto.VideoShowDto;
import com.EagleEye.EagleEyeTeamServer.dto.VideoUploadRequest;
import com.EagleEye.EagleEyeTeamServer.service.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class VideoController {
    private final VideoService videoService;

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
}
