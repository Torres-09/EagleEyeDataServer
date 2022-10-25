package com.EagleEye.EagleEyeTeamServer.service;

import com.EagleEye.EagleEyeTeamServer.dto.VideoUploadRequest;
import com.EagleEye.EagleEyeTeamServer.entity.Video;
import com.EagleEye.EagleEyeTeamServer.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.InputStream;

@Service
@RequiredArgsConstructor
public class VideoServiceImpl implements VideoService {
    private final VideoRepository videoRepository;

    @Override
    public void uploadVideo(VideoUploadRequest request,
                            MultipartFile multipartFile) {

        Video video = Video.builder()
                .videoName(request.getTitleName())
                .videoUrl("c:/EagleEyeVideo/" + multipartFile.getOriginalFilename())
                .createAt(request.getLocalDateTime())
                .build();

        try {
            FileOutputStream fos = new FileOutputStream("c:/EagleEyeVideo/" + multipartFile.getOriginalFilename());
            InputStream is = multipartFile.getInputStream();

            int readCount = 0;
            byte[] buffer = new byte[2024];

            while ((readCount = is.read(buffer)) != -1) {
                //  파일에서 가져온 fileInputStream을 설정한 크기 (1024byte) 만큼 읽고

                fos.write(buffer, 0, readCount);
                // 위에서 생성한 fileOutputStream 객체에 출력하기를 반복한다
            }

        } catch (Exception e) {
            throw new RuntimeException("file save Error");
        }

        // 영상처리 머신러닝 적용하고 비디오를 적용하기
        videoRepository.save(video);
    }
}
