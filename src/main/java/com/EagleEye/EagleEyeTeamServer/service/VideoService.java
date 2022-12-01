package com.EagleEye.EagleEyeTeamServer.service;

import com.EagleEye.EagleEyeTeamServer.dto.VideoShowDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface VideoService {
    void uploadVideo(String titleName, MultipartFile multipartFile) throws IOException;
    void uploadVideo2(MultipartFile multipartFile) throws IOException;

    List<VideoShowDto> videoShowAll();
}
