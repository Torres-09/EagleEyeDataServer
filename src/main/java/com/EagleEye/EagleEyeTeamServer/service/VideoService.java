package com.EagleEye.EagleEyeTeamServer.service;

import com.EagleEye.EagleEyeTeamServer.dto.VideoUploadRequest;
import org.springframework.web.multipart.MultipartFile;

public interface VideoService {
    void uploadVideo(VideoUploadRequest request, MultipartFile multipartFile);
}
