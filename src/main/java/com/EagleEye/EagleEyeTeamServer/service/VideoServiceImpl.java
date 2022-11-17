package com.EagleEye.EagleEyeTeamServer.service;

import com.EagleEye.EagleEyeTeamServer.dto.VideoShowDto;
import com.EagleEye.EagleEyeTeamServer.entity.Video;
import com.EagleEye.EagleEyeTeamServer.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class VideoServiceImpl implements VideoService {
    private final VideoRepository videoRepository;
    private final WebClient webClient;

    @Override
    @Transactional
    public void uploadVideo(String titleName,
                            MultipartFile multipartFile) throws IOException {

        // 저장 될 영상 주소 데이터베이스에 저장하기
        Video video = Video.builder()
                .videoName(titleName)
                .originalFileName(multipartFile.getOriginalFilename())
                .changedFileName(null)
                .createAt(LocalDateTime.now())
                .build();

        System.out.println("비디오의 이름은 : " + video.getVideoName());
        System.out.println("비디오의 이름은 : " + video.getOriginalFileName());
        System.out.println("비디오의 이름은 : " + multipartFile.getOriginalFilename());
        System.out.println("비디오의 이름은 : " + multipartFile.getName());

        String fullPath = video.getCreateAt() + video.getOriginalFileName();
        System.out.println("파일저장");
        Path path = Paths.get(fullPath);
        System.out.println(path);

        // 이게 진짜 fullpath
        System.out.println(path.toUri());

        FileCopyUtils.copy(multipartFile.getInputStream(), new FileOutputStream(path.toFile()));


        // 영상 처리 api 호출하기
//        webClient.post()
//                .uri("localhost:8000/change")
//                .bodyValue(multipartFile)
//                .retrieve()
//                .bodyToMono(MultipartFile.class)
//                .block();

        // 영상처리 머신러닝 적용하고 비디오를 적용하기
        videoRepository.save(video);
    }

    @Override
    @Transactional
    public List<VideoShowDto> videoShowAll() {
        List<Video> videos = videoRepository.findAll();
        List<VideoShowDto> collect = videos.stream()
                .map(v -> new VideoShowDto(v))
                .collect(Collectors.toList());

        return collect;
    }
}
