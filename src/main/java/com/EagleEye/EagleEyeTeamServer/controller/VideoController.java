package com.EagleEye.EagleEyeTeamServer.controller;

import com.EagleEye.EagleEyeTeamServer.dto.VideoUploadRequest;
import com.EagleEye.EagleEyeTeamServer.entity.Video;
import com.EagleEye.EagleEyeTeamServer.service.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class VideoController {
    private final VideoService videoService;

    @PostMapping("/upload")
    public ResponseEntity<Video> uploadVideo(@RequestBody VideoUploadRequest videoUploadRequest, @RequestPart MultipartFile video) {
        videoService.uploadVideo(videoUploadRequest, video);
        return null;
    }

    @GetMapping("/connect-check")
    public String connectCheck() {
        return "okay";
    }
}
