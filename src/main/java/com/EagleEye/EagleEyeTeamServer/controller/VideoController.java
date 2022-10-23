package com.EagleEye.EagleEyeTeamServer.controller;

import com.EagleEye.EagleEyeTeamServer.dto.ApiResponse;
import com.EagleEye.EagleEyeTeamServer.dto.VideoShwoDto;
import com.EagleEye.EagleEyeTeamServer.dto.VideoUploadRequest;
import com.EagleEye.EagleEyeTeamServer.service.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


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
    public ApiResponse<VideoShwoDto> showAllVideo() {
        return null;
    }

    @GetMapping("/connect-check")
    public ApiResponse<String> connectCheck() {

        return new ApiResponse<>("ok");
    }
}
