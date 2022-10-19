package com.EagleEye.EagleEyeTeamServer.service;

import com.EagleEye.EagleEyeTeamServer.dto.VideoUploadRequest;
import com.EagleEye.EagleEyeTeamServer.entity.Video;
import com.EagleEye.EagleEyeTeamServer.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class VideoServiceImpl implements VideoService {
    private final VideoRepository videoRepository;

    @Override
    public void uploadVideo(VideoUploadRequest request, MultipartFile multipartFile) {

        Video video = Video.builder()
                .videoName(request.getTitleName())
                .videoUrl(multipartFile.getOriginalFilename())
                .createAt(request.getLocalDateTime())
                .build();

        videoRepository.save(video);
    }
}
