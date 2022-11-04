package com.EagleEye.EagleEyeTeamServer.service;

import com.EagleEye.EagleEyeTeamServer.dto.VideoShowDto;
import com.EagleEye.EagleEyeTeamServer.dto.VideoUploadRequest;
import com.EagleEye.EagleEyeTeamServer.entity.Video;
import com.EagleEye.EagleEyeTeamServer.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.client.reactive.ClientHttpRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VideoServiceImpl implements VideoService {
    private final VideoRepository videoRepository;
    private final WebClient webClient;

    @Override
    public void uploadVideo(VideoUploadRequest request,
                            MultipartFile multipartFile) {

        // 파이썬 api 호출하여 영상처리 된 영상 받아오기

        // 받아온 영상 주소 데이터베이스에 저장하기

        Video video = Video.builder()
                .videoName(request.getTitleName())
                .videoUrl("c:/EagleEyeVideo/" + multipartFile.getOriginalFilename())
                .createAt(request.getLocalDateTime())
                .build();

        webClient.post()
                .uri("localhost:8000/uploadfiles")
                .body(Mono.just(multipartFile),MultipartFile.class)
                .retrieve()
                .bodyToMono(MultipartFile.class);

//        try {
//            FileOutputStream fos = new FileOutputStream("c:/EagleEyeVideo/" + multipartFile.getOriginalFilename());
//            InputStream is = multipartFile.getInputStream();
//
//            int readCount = 0;
//            byte[] buffer = new byte[2024];
//
//            while ((readCount = is.read(buffer)) != -1) {
//                //  파일에서 가져온 fileInputStream을 설정한 크기 (1024byte) 만큼 읽고
//
//                fos.write(buffer, 0, readCount);
//                // 위에서 생성한 fileOutputStream 객체에 출력하기를 반복한다
//            }
//
//        } catch (Exception e) {
//            throw new RuntimeException("file save Error");
//        }

        // 영상처리 머신러닝 적용하고 비디오를 적용하기
        videoRepository.save(video);
    }

    @Override
    public List<VideoShowDto> videoShowAll() {
        List<Video> videos = videoRepository.findAll();
        List<VideoShowDto> collect = videos.stream()
                .map(v -> new VideoShowDto(v))
                .collect(Collectors.toList());

        return collect;
    }
}
