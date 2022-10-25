package com.EagleEye.EagleEyeTeamServer.service;

import com.EagleEye.EagleEyeTeamServer.dto.VideoShowDto;
import com.EagleEye.EagleEyeTeamServer.dto.VideoUploadRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface VideoService {
    void uploadVideo(VideoUploadRequest request, MultipartFile multipartFile);

    List<VideoShowDto> videoShowAll();
}
